package com.github.mkopylec.passwordreset.domain.history;

import org.springframework.stereotype.Component;

import java.util.Date;

import static java.util.Collections.singletonList;

@Component
public class EmailHistoryFactory {

    public EmailHistory createHistory(long userId, String username, String email) {
        HistoryEntry entry = new HistoryEntry(username, email, new Date());
        return new EmailHistory(userId, singletonList(entry));
    }
}
