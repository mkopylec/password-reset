package com.github.mkopylec.passwordreset.api;

public class ResetData {

    private String resetMethod;
    private String maidenName;
    private String resetUrl;

    public String getResetMethod() {
        return resetMethod;
    }

    public void setResetMethod(String resetMethod) {
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
