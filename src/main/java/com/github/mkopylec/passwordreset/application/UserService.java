package com.github.mkopylec.passwordreset.application;

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
class UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    public void saveUser(UserData userData) {
        User user = createNewUser(userData);
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

    private User createNewUser(UserData userData) {
        return userFactory.createUser(userData.getId(), userData.getHashedPassword())
                .withUsername(userData.getUsername())
                .withEmail(userData.getEmail())
                .withMaidenName(userData.getMaidenName())
                .withFullName(userData.getFirstName(), userData.getLastName())
                .create();
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
