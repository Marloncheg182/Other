package Entity;

import Annotations.Cost;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Romanenchuk Oleg
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Tablet.GET_BY_DEVICE_ID, query = "SELECT t FROM Tablet t WHERE t.device.id= :deviceId"),
        @NamedQuery(name = Tablet.SEARCH, query = "SELECT t FROM Tablet t WHERE(t.model) LIKE :keyword OR UPPER(t.device.model) LIKE :keyword ORDER BY t.device.category.type, t.device.model"),
        @NamedQuery(name = Tablet.GET_ALL, query = "SELECT t FROM Table t")
})
@XmlRootElement
public class Tablet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String model;

    @Column(length = 5000)
    private String characters;

    @Column(nullable = false)
    @Cost
    private Float cost;
    @NotNull
    private String photo;
    @ManyToOne
    @JoinColumn(name = "device_fk", nullable = false)
    @XmlTransient
    private Device device;

    public static final String GET_BY_DEVICE_ID = "Tablet.getByDeviceId";

    public static final String SEARCH = "Tablet.search";

    public static final String GET_ALL = "Tablet.getAll";

    public Tablet() {
    }

    public Tablet(String model, String characters, Float cost, String photo, Device device) {
        this.model = model;
        this.characters = characters;
        this.cost = cost;
        this.photo = photo;
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tablet)) return false;

        Tablet tablet = (Tablet) o;

        if (!model.equals(tablet.model)) return false;
        if (!photo.equals(tablet.photo)) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + photo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", characters='" + characters + '\'' +
                ", cost=" + cost +
                ", photo='" + photo + '\'' +
                ", device=" + device +
                '}';
    }
}
