package com.github.mkopylec.passwordreset.domain.history;

import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class HistoryEntry {

    private final String username;
    private final String email;
    private final Date sendDate;

    HistoryEntry(String username, String email, Date sendDate) {
        checkArgument(hasUsernameOrEmail(username, email), "Username and e-mail address cannot be empty");
        checkNotNull(sendDate, "Send date cannot be empty");
        this.username = username;
        this.email = email;
        this.sendDate = sendDate;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getSendDate() {
        return sendDate;
    }

    private boolean hasUsernameOrEmail(String username, String email) {
        return isNotBlank(username) || isNotBlank(email);
    }
}
