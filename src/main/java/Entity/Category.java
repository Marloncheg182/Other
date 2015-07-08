package Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Category.GET_BY_NAME, query = "SELECT c FROM Category c WHERE c.type = :dname "),
        @NamedQuery(name = Category.GET_ALL, query = "SELECT c FROM Category c")
})
@XmlRootElement
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 50)
    private String type;

    @NotNull
    @Column(nullable = false)
    private String about;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    @XmlTransient
    private List<Device> devices;

    public static final String GET_BY_NAME = "Categories.getByName";
    public static final String GET_ALL = "Categories.getAll";

    public Category() {
    }

    public Category(String type, String about) {
        this.type = type;
        this.about = about;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        if (devices == null)
            devices = new ArrayList<Device>();
        devices.add(device);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;

        Category category = (Category) obj;

        return type.equals(category.type);

    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return "Category{" +
                "type='" + type + '\'' +
                ", about='" + about + '\'' +
                ", devices=" + devices +
                '}';
    }
}

