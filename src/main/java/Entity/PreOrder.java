package Entity;

import javax.persistence.*;

/**
 * @author Romanenchuk Oleg
 */
@Entity
public class PreOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "phone_fk", nullable = false)
    private Phone phone;

    @OneToOne
    @JoinColumn(name = "phone_fk", nullable = false)
    private Tablet tablet;

    public PreOrder() {
    }

    public PreOrder(Integer quantity, Phone phone, Tablet tablet) {
        this.quantity = quantity;
        this.phone = phone;
        this.tablet = tablet;


    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Phone getPhone() {
        return phone;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setTablet(Tablet tablet) {
        this.tablet = tablet;
    }

    public Tablet getTablet() {
        return tablet;

    }

    public Float getTotalPhonePrice() {
        return phone.getCost() * quantity;
    }

    public Float getTotalTabletPrice() {
        return tablet.getCost() * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreOrder)) return false;

        PreOrder preOrder = (PreOrder) o;

        if (!id.equals(preOrder.id)) return false;
        if (!quantity.equals(preOrder.quantity)) return false;
        if (!phone.equals(preOrder.phone)) return false;
        if (!tablet.equals(preOrder.tablet)) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + quantity.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + tablet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PreOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", phone=" + phone +
                ", tablet=" + tablet +
                '}';
    }
}

