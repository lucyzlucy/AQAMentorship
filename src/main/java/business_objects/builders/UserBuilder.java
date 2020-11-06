package business_objects.builders;

import business_objects.entities.User;

public class UserBuilder {
    private User user = new User();

    public User make() {
        return user;
    }


    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return this;
    }


    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public UserBuilder setAddress(String address) {
        user.setAddress(address);
        return this;
    }

    public UserBuilder setCity(String city) {
        user.setCity(city);
        return this;
    }

    public UserBuilder setState(String state) {
        user.setState(state);
        return this;
    }

    public UserBuilder setCountry(String country) {
        user.setCountry(country);
        return this;
    }

    public UserBuilder setZip(String zip) {
        user.setZip(zip);
        return this;
    }

    public UserBuilder setPhone(String phone) {
        user.setPhone(phone);
        return this;
    }
}
