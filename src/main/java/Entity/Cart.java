package Entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Romanenchuk Oleg
 */


public class Cart {

    @NotNull   // type of purchase
    private Phone phone;

    @NotNull   // type of purchase
    private Tablet tablet;

    @NotNull
    @Min(10)  // min 10 UAH
    private Integer price;

    @NotNull
    @Min(1)   // min 1 amount of purchase
    private Integer quantity;

    public Cart() {
    }

    public Cart(Phone phone, Tablet tablet, Integer price, Integer quantity) {
        this.phone = phone;
        this.tablet = tablet;
        this.price = price;
        this.quantity = quantity;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public void setTablet(Tablet tablet) {
        this.tablet = tablet;
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

    public Float getTotalPhonePrice() {
        return phone.getCost() * quantity;
    }

    public Float getTotalTabletPrice() {
        return tablet.getCost() * quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart = (Cart) o;

        if (!phone.equals(cart.phone)) return false;
        if (!tablet.equals(cart.tablet)) return false;
        if (!price.equals(cart.price)) return false;
        return quantity.equals(cart.quantity);

    }

    @Override
    public int hashCode() {
        int result = phone.hashCode();
        result = 31 * result + tablet.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "phone=" + phone +
                ", tablet=" + tablet +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}























