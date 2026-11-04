package za.ac.factory;

import za.ac.domain.User;
import za.ac.domain.Enums.Roles;
import za.ac.util.Helper;

import java.util.UUID;

public class UserFactory {

    public static User createAppUser(UUID userId, String email, String password, Roles role, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password) || role == null) {
            return null;
        }

        return new User.Builder()
                .setUserId(userId)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }
}
