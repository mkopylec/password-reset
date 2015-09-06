package com.github.mkopylec.passwordreset.infrastructure.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;

interface UserLoginDocumentDao extends MongoRepository<UserLoginDocument, String> {

}
