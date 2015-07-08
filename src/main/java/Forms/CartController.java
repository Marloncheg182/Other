package Forms;

import Entity.*;
import Service.CatalogService;
import Service.OrderService;
import Utils.Loggson;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 * Cart Controller class
 */

@Named
@ConversationScoped
@Loggson
@CatchException
public class CartController extends Controller implements Serializable {

    @Inject
    @LoggedIn
    private Instance<Client> loggedIn;

    @Inject
    private CatalogService catalogBeans;

    @Inject
    private OrderService orderBeans;

    @Inject
    private Conversation conversation;

    private List<Cart> carts;
    private Payment payment = new Payment();
    private Order order;

    public Client getClient(){
        return loggedIn.get();
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public PaymentType[] getPaymentType(){
        return PaymentType.values();
    }

    public String addToCart() {
        Model model = catalogBeans.findModel(getParameterId("modelId"));

        if (conversation.isTransient()) {
            carts = new ArrayList<Cart>();
            conversation.begin();
        }

        boolean modelFound = false;
        for (Cart cart : carts) {
            if (cart.getModel().equals(model)) {
                cart.setQuantity(cart.getQuantity() + 1);
                modelFound = true;
            }
        }
        if (!modelFound)
            carts.add(new Cart(model, 1));

        return "showcart.faces";
    }

    public String removeFromCart() {
        Model model = catalogBeans.findModel(getParameterId("modelId"));

        for (Cart cart : carts) {
            if (cart.getModel().equals(model)) {
                carts.remove(cart);
                return null;
            }
        }
        return null;
    }

    public String updateAmount() {
        return null;
    }

    public String chacker() {
        return "confirmorder.faces";
    }

    public String confirmOrder(){
        order = orderBeans.createOrder(getClient(), getCarts(),payment);
        carts.clear();

        if (!conversation.isTransient()){
            conversation.end();
        }
        return "orderconfirmed.faces";
    }

    public List<Cart> getCarts(){
        return carts;
    }

    public boolean cartIsEmpty(){
        return getCarts() == null || getCarts().size()==0;
    }

    public  Float getTotal(){
        if (carts == null || carts.isEmpty())
            return 0f;

        Float total= 0f;

        for (Cart cart : carts) {
            total +=(cart.getTotalModelPrice());
        }
        return total;
    }
}
