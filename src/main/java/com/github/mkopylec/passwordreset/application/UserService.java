package com.github.mkopylec.passwordreset.application;

import com.github.mkopylec.passwordreset.api.dto.UserData;
import com.github.mkopylec.passwordreset.domain.user.User;
import com.github.mkopylec.passwordreset.domain.user.UserFactory;
import com.github.mkopylec.passwordreset.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

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
        if (user == null) {
            throw new NotFoundException("User with login or e-mail: '" + loginOrEmail + "' does not exist");
        }
        return user.getId();
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
}
