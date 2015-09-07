package com.github.mkopylec.passwordreset.domain.history;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static java.util.Collections.unmodifiableList;

@Document(collection = "email_history")
public class EmailHistory {

    @Id
    private long userId;
    private List<HistoryEntry> entries;

    EmailHistory(long userId, List<HistoryEntry> entries) {
        this.userId = userId;
        this.entries = entries;
    }

    public long getUserId() {
        return userId;
    }

    public List<HistoryEntry> getEntries() {
        return unmodifiableList(entries);
    }
}
