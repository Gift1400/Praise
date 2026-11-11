package za.ac.factory;

import za.ac.domain.AppUser;
import za.ac.domain.Enums.Role;
import za.ac.util.Helper;


public class AppUserFactory {

    public static AppUser createAppUser(String userId, String email, String password, Role role, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password) || role == null) {
            return null;
        }

        return new AppUser.Builder()
                .setUserId(userId)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }
}
