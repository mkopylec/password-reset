package com.github.mkopylec.passwordreset.api.dto;

import org.hibernate.validator.constraints.NotBlank;

public class Password {

    @NotBlank(message = "Empty password")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
