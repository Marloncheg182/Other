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
        @NamedQuery(name = Model.GET_BY_DEVICE_ID, query = "SELECT m FROM Model m WHERE m.device.id= :deviceId"),
        @NamedQuery(name = Model.SEARCH, query = "SELECT m FROM Model m WHERE(m.model) LIKE :keyword OR UPPER(m.device.model) LIKE :keyword ORDER BY m.device.category.type, m.device.model"),
        @NamedQuery(name = Model.GET_ALL, query = "SELECT m FROM Model m")
})
@XmlRootElement
public class Model {

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


    public static final String GET_BY_DEVICE_ID = "Phone.getByDeviceId";

    public static final String SEARCH = "Phone.search";

    public static final String GET_ALL = "Phone.getAll";

    public Model() {
    }

    public Model(String model, String characters, Float cost, String photo, Device device) {
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
        if (!(o instanceof Model)) return false;

        Model model = (Model) o;

        if (!this.model.equals(model.model)) return false;
        return photo.equals(model.photo);

    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + photo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", characters='" + characters + '\'' +
                ", cost=" + cost +
                ", photo='" + photo + '\'' +
                ", device=" + device + '\'' +
                '}';
    }
}
