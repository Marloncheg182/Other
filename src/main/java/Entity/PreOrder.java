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
    @JoinColumn(name = "model_fk", nullable = false)
    private Model model;

    public PreOrder() {
    }

    public PreOrder(Integer quantity, Model model) {
        this.quantity = quantity;
        this.model = model;


    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Model getModel() {
        return model;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setModel(Model model) {
        this.model = model;
    }


    public Float getTotalModelPrice() {
        return model.getCost() * quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PreOrder)) return false;

        PreOrder preOrder = (PreOrder) o;

        if (!id.equals(preOrder.id)) return false;
        if (!quantity.equals(preOrder.quantity)) return false;
        if (!model.equals(preOrder.model)) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + quantity.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PreOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", phone=" + model +
                '}';
    }
}

