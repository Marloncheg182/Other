package Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author Romanenchuk Oleg
 */


public class Cart {

    @NotNull   // purchase
    private Model model;

    @NotNull
    @Min(10)  // min 10 UAH
    private Integer price;

    @NotNull
    @Min(1)   // min 1 amount of purchase
    private Integer quantity;

    public Cart() {
    }

    public Cart(Model model, Integer price, Integer quantity) {
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }

    public Cart(Model model, Integer quantity) {
        this.model = model;
        this.quantity = quantity;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculate the cost sum of your purchases in cart
     */

    public Float getTotalModelPrice() {
        return model.getCost() * quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        if (!model.equals(cart.model)) return false;
        if (!price.equals(cart.price)) return false;
        return quantity.equals(cart.quantity);

    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "phone=" + model +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}























