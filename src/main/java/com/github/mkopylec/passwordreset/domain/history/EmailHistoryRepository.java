package com.github.mkopylec.passwordreset.domain.history;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailHistoryRepository extends MongoRepository<EmailHistory, Long> {

}
