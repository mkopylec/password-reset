package com.github.mkopylec.passwordreset.application.user;

import com.github.mkopylec.passwordreset.api.dto.Password;
import com.github.mkopylec.passwordreset.api.dto.ResetMethod;
import com.github.mkopylec.passwordreset.api.dto.UserData;
import com.github.mkopylec.passwordreset.domain.user.User;
import com.github.mkopylec.passwordreset.domain.user.UserFactory;
import com.github.mkopylec.passwordreset.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.FULL;
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.NOT_AVAILABLE;
import static com.github.mkopylec.passwordreset.api.dto.ResetMethod.SIMPLE;
import static com.github.mkopylec.passwordreset.application.check.Preconditions.notFoundIfNull;

@Service
public class UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Autowired
    public UserService(UserFactory userFactory, UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public void saveUser(UserData userData) {
        User user = userRepository.findById(userData.getId());
        if (user == null) {
            user = createNewUser(userData);
        } else {
            updateUser(user, userData);
        }
        userRepository.save(user);
    }

    public long getUserId(String loginOrEmail) {
        User user = userRepository.findByLoginOrEmail(loginOrEmail);
        notFoundIfNull(user, "User with login or e-mail: '" + loginOrEmail + "' does not exist");
        return user.getId();
    }

    public ResetMethod getPasswordResetMethod(long id) {
        User user = userRepository.findById(id);
        notFoundIfNull(user, "User with id: '" + id + "' does not exist");
        return resolveResetMethod(user);
    }

    public void changePassword(long id, Password password) {
        User user = userRepository.findById(id);
        notFoundIfNull(user, "User with id: '" + id + "' does not exist");
        String hashedPassword = passwordHasher.hash(password.getText());
        user.changeHashedPassword(hashedPassword);
        userRepository.save(user);
    }

    private User createNewUser(UserData userData) {
        return userFactory.createUser(userData.getId(), userData.getHashedPassword())
                .withUsername(userData.getUsername())
                .withEmail(userData.getEmail())
                .withMaidenName(userData.getMaidenName())
                .withFullName(userData.getFirstName(), userData.getLastName())
                .create();
    }

    private void updateUser(User user, UserData userData) {
        user.changeUsername(userData.getUsername());
        user.changeEmail(userData.getEmail());
        user.changeMaidenName(userData.getMaidenName());
        user.rename(userData.getFirstName(), userData.getLastName());
    }

    private ResetMethod resolveResetMethod(User user) {
        if (user.isFullyNamedAndHasMaidenName()) {
            return FULL;
        }
        if (user.isFullyNamed()) {
            return SIMPLE;
        }
        return NOT_AVAILABLE;
    }
}
