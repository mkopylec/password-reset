package com.github.mkopylec.passwordreset.api.dto;

public class ResetData {

    private ResetMethod resetMethod;
    private String maidenName;
    private String resetUrl;

    public ResetMethod getResetMethod() {
        return resetMethod;
    }

    public void setResetMethod(ResetMethod resetMethod) {
        this.resetMethod = resetMethod;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getResetUrl() {
        return resetUrl;
    }

    public void setResetUrl(String resetUrl) {
        this.resetUrl = resetUrl;
    }
}
