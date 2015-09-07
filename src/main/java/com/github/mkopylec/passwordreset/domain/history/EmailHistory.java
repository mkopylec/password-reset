package com.github.mkopylec.passwordreset.domain.history;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "email_history")
public class EmailHistory {

    @Id
    private long userId;
    private List<HistoryEntry> entries;

    EmailHistory(long userId, List<HistoryEntry> entries) {
        this.userId = userId;
        this.entries = entries;
    }
}
