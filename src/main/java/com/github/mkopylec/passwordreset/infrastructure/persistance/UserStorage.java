package com.github.mkopylec.passwordreset.infrastructure.persistance;

import com.github.mkopylec.passwordreset.domain.user.User;
import com.github.mkopylec.passwordreset.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class UserStorage implements UserRepository {

    private final UserDocumentDao userDao;
    private final UserLoginDocumentDao userLoginDao;
    private final UserEmailDocumentDao userEmailDao;
    private final DocumentFactory documentFactory;

    @Autowired
    public UserStorage(UserDocumentDao userDao, UserLoginDocumentDao userLoginDao, UserEmailDocumentDao userEmailDao, DocumentFactory documentFactory) {
        this.userDao = userDao;
        this.userLoginDao = userLoginDao;
        this.userEmailDao = userEmailDao;
        this.documentFactory = documentFactory;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
        userLoginDao.save(documentFactory.createUserLoginDocument(user));
        userEmailDao.save(documentFactory.createUserEmailDocument(user));
    }

    @Override
    public User findById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public User findByLoginOrEmail(String loginOrEmail) {
        UserLoginDocument userLogin = userLoginDao.findOne(loginOrEmail);
        if (userLogin != null) {
            return findById(userLogin.getId());
        }
        UserEmailDocument userEmail = userEmailDao.findOne(loginOrEmail);
        if (userEmail != null) {
            return findById(userEmail.getId());
        }
        return null;
    }
}
