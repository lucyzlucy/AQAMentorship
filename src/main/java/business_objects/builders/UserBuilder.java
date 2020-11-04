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
}
