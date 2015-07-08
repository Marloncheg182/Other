package Entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = Order.GET_ALL, query = "SELECT o FROM Order o")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_fk", nullable = false)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "order_pre_order", joinColumns = {@JoinColumn(name = "order_fk")},
            inverseJoinColumns = {@JoinColumn(name = "pre_order_fk")})
    private List<PreOrder> preOrders;

    @Embedded
    private Contacts contacts;

    @Embedded
    private Payment payment = new Payment();

    public static final String GET_ALL = "Order.getAll";

    public Order() {
    }

    public Order(Client client, Payment payment, Contacts contacts) {
        this.client = client;
        this.payment = payment;
        this.contacts = contacts;
    }

    @PrePersist
    private void setDefault() {
        orderDate = new Date();
    }

    public Float getAll() {
        if (preOrders == null || preOrders.isEmpty())
            return 0f;
        Float all = 0f;

        for (PreOrder preOrder : preOrders) {
            all += (preOrder.getTotalModelPrice());
        }
        return all;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<PreOrder> getPreOrders() {
        return preOrders;
    }

    public void setPreOrders(List<PreOrder> preOrders) {
        this.preOrders = preOrders;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!orderDate.equals(order.orderDate)) return false;
        return client.equals(order.client);

    }

    @Override
    public int hashCode() {
        int result = orderDate!=null ? orderDate.hashCode():0;
        result = 31 * result + client.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", client=" + client +
                ", preOrders=" + preOrders +
                ", contacts=" + contacts +
                ", payment=" + payment +
                '}';
    }
}

