package Entity;

import Annotations.Email;
import Annotations.Login;
import Exceptions.ValidationException;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Romanenchuk Oleg
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Client.GET_BY_LOGIN, query = "SELECT c FROM Client c WHERE c.login = :login"),
        @NamedQuery(name = Client.GET_BY_LOGIN_AND_PASSWORD, query = "SELECT c FROM Client c WHERE c.login = :login AND c.password = :password"),
        @NamedQuery(name = Client.GET_ALL, query = "SELECT c FROM Client c")
})
@XmlRootElement
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    @Login
    private String login;

    @Column(nullable = false, length = 16)
    @NotNull
    @Size(min = 4, max = 16)
    private String password;

    @Column(nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String firstname;

    @Column(nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;
    private String phonenumber;

    @Email
    private String email;

    @Embedded
    @Valid
    private Contacts contacts = new Contacts();

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Transient
    private Integer age;

    public static final String GET_BY_LOGIN = "Client.getByLogin";

    public static final String GET_BY_LOGIN_AND_PASSWORD = "Client.getByLoginAndPassword";

    public static final String GET_ALL = "Client.getAll";

    public Client() {
    }

    public Client(String login, String password, String firstname, String lastname, String email, Contacts contacts) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contacts = contacts;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    /**
     * Writing of method , which will evaluate the age of client by his birth date and current date
     */

    @PostLoad
    @PostPersist
    @PostUpdate
    public void clientAge() {
        if (birthDate == null) {
            age = null;
            return;
        }

        Calendar birth = new GregorianCalendar();
        birth.setTime(birthDate);
        Calendar current = new GregorianCalendar();
        current.setTime(new Date());
        int indicate = 0;
        if (current.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
            indicate = -1;
        }
        age = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + indicate;
    }

    /**
     * Password matching
     * @param pass Password
     * @throws ValidationException if our password is wrong
     */
      public void checkPassword(String pass){
          if(pass == null || "".equals(pass))
              throw new ValidationException("Wrong password!!!");  // Wrong
          if(!pass.equals(password))
              throw new ValidationException("Forgot your password?");  // Forgot

      }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return true;

    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", contacts=" + contacts +
                ", birthDate=" + birthDate +
                ", age=" + age +
                '}';
    }
}
