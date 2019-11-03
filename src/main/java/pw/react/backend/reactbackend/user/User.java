package pw.react.backend.reactbackend.user;
import javax.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pw.backend.lab.backlab.utils.JsonDateDeserializer;
import pw.backend.lab.backlab.utils.JsonDateSerializer;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "dateOfBirth")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate dateOfBirth;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public User(){}

    public User(String login, String first_name, String last_name,LocalDate birth, Boolean active) {
        this.login = login;
        this.firstName = first_name;
        this.lastName = last_name;
        this.dateOfBirth = birth;
        this.active = active;
    }

    public String getUserInfo(){
        String info = "User Info: " + this.getId() +", "+ this.getLogin() +", " + this.getFirstName() +", " +this.getLastName()+", " +
                this.getDateOfBirth()+ ", " + this.active();
        return info;
    }
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean active() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setFields(String login, String firstName, String lastName, LocalDate dateOfBirth, Boolean active) {
        setLogin(login);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setActive(active);
    }
}