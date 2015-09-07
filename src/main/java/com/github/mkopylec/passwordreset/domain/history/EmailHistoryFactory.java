package com.github.mkopylec.passwordreset.domain.history;

import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;
import static java.util.Collections.singletonList;

@Component
public class EmailHistoryFactory {

    public EmailHistory createHistory(long userId, String username, String email) {
        HistoryEntry entry = new HistoryEntry(username, email, now());
        return new EmailHistory(userId, singletonList(entry));
    }
}
