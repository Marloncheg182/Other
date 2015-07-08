package Service;

import Entity.*;
import Exceptions.ValidationException;
import Utils.Loggson;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 * Order services methods
 */

@Stateless
@Loggson
public class OrderService implements Serializable {

    @Inject
    private EntityManager manager;

    public Order createOrder(final Client client, final List<Cart> carts, final Payment payment) {

        if (carts == null || carts.size() == 0)
            throw new ValidationException("Cart is empty");

        Order order = new Order(manager.merge(client), payment, client.getContacts());

        List<PreOrder> preOrders = new ArrayList<PreOrder>();

        for (Cart cart : carts) {
            preOrders.add(new PreOrder(cart.getQuantity(), manager.merge(cart.getModel())));
        }
        order.setPreOrders(preOrders);

        manager.persist(order);
        return order;
    }

    public Order findOrderById(Long orderId){
        if (orderId == null)
            throw new ValidationException("Wrong id");

        return manager.find(Order.class, orderId);
    }

    public List<Order> findAll(){
        TypedQuery<Order> query = manager.createNamedQuery(Order.GET_ALL, Order.class);
        return query.getResultList();
    }

    public void removeOrder(Order order){
        if (order == null)
            throw new ValidationException("Empty");

        manager.remove(manager.merge(order));
    }

}
