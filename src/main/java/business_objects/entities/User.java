package business_objects.entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String state;
    private String zip;
    private String phone;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
