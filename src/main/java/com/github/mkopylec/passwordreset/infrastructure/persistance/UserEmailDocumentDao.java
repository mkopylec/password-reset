package com.github.mkopylec.passwordreset.infrastructure.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;

interface UserEmailDocumentDao extends MongoRepository<UserEmailDocument, String> {

}
