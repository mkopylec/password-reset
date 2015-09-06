package com.github.mkopylec.passwordreset.infrastructure.persistance;

import com.github.mkopylec.passwordreset.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

interface UserDocumentDao extends MongoRepository<User, Long> {

}
