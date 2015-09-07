package com.github.mkopylec.passwordreset.application.email;

import com.github.mkopylec.passwordreset.api.dto.ResetData;
import com.github.mkopylec.passwordreset.api.dto.ResetEmail;
import com.github.mkopylec.passwordreset.domain.history.EmailHistory;
import com.github.mkopylec.passwordreset.domain.history.EmailHistoryFactory;
import com.github.mkopylec.passwordreset.domain.history.EmailHistoryRepository;
import com.github.mkopylec.passwordreset.domain.user.User;
import com.github.mkopylec.passwordreset.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.mkopylec.passwordreset.application.check.Preconditions.badRequestIfFalse;
import static com.github.mkopylec.passwordreset.application.check.Preconditions.notFoundIfNull;
import static java.util.stream.Collectors.toList;

@Service
public class EmailService {

    private final UserRepository userRepository;
    private final EmailSender emailSender;
    private final EmailHistoryFactory historyFactory;
    private final EmailHistoryRepository historyRepository;

    @Autowired
    public EmailService(UserRepository userRepository, EmailSender emailSender, EmailHistoryFactory historyFactory, EmailHistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.historyFactory = historyFactory;
        this.historyRepository = historyRepository;
    }

    public void sendPasswordResetEmail(long id, ResetData resetData) {
        User user = userRepository.findById(id);
        notFoundIfNull(user, "User with id: '" + id + "' does not exist");
        String maidenName = resetData.getMaidenName();
        badRequestIfFalse(user.hasMaidenName(maidenName), "User with id: '" + id + "' has maiden name not equal to: " + maidenName);

        emailSender.sendPasswordResetEmail(user, resetData.getResetUrl());
        EmailHistory history = historyFactory.createHistory(id, user.getUsername(), user.getEmail());
        historyRepository.save(history);
    }

    public List<ResetEmail> getEmailHistory(long id) {
        EmailHistory history = historyRepository.findOne(id);
        notFoundIfNull(history, "History for user with id: '" + id + "' does not exist");

        return history.getEntries()
                .stream()
                .map(entry -> {
                    ResetEmail resetEmail = new ResetEmail();
                    resetEmail.setUsername(entry.getUsername());
                    resetEmail.setEmail(entry.getEmail());
                    resetEmail.setSendDate(entry.getSendDate());
                    return resetEmail;
                })
                .collect(toList());
    }
}
