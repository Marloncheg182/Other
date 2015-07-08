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
        @NamedQuery(name = Device.GET_BY_CATEGORY, query = "SELECT d FROM Device d WHERE d.category.model = :dmodel"),
        @NamedQuery(name = Device.GET_ALL, query = "SELECT d FROM Device d")
})
@XmlRootElement
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    private String model;

    @Column(nullable = false)
    private String characteristic;

    @ManyToOne
    @JoinColumn(name = "category_fk", nullable = false)
    @XmlTransient
    private Category category;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    @OrderBy("model ASC")
    @XmlTransient
    private List<Model> models;

    public static final String GET_BY_CATEGORY = "Device.getByCategory";
    public static final String GET_ALL = "Device.getAll";

    public Device() {
    }

    public Device(String model, String characteristic, Category category) {
        this.model = model;
        this.characteristic = characteristic;
        this.category = category;


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

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Model> getModels() {
        return models;
    }

    public void addModel(Model model) {
        if (models == null)
            models = new ArrayList<Model>();
        models.add(model);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;

        Device device = (Device) o;

        return model.equals(device.model);

    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", characteristic='" + characteristic + '\'' +
                '}';
    }
}


