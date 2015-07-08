package Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Romanenchuk Oleg
 */

@Embeddable
public class Contacts {

    @Column(nullable = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String street;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    private String country;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    private String city;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    private String region;

    @Column(name = "index", nullable = false, length = 6)
    @NotNull
    @Size(min = 4, max = 6)
    private String index;     //post index, for exa,ple 61*** for Kharkiv

    public Contacts() {
    }

    public Contacts(String street, String country, String city, String region, String index) {
        this.street = street;
        this.country = country;
        this.city = city;
        this.region = region;
        this.index = index;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts)) return false;

        Contacts contacts = (Contacts) o;

        if (!street.equals(contacts.street)) return false;
        if (!country.equals(contacts.country)) return false;
        if (!city.equals(contacts.city)) return false;
        if (region.equals(contacts.region)) return false;
        return index.equals(contacts.index);

    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + index.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
