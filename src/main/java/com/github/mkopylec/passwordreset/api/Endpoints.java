package com.github.mkopylec.passwordreset.api;

public final class Endpoints {

    /**
     * Response:
     * <pre>
     * <code>{
     *     "method": "FULL"/"SIMPLE"/"NOT_AVAILABLE"
     * }
     * </code>
     * </pre>
     */
    public static final String GET_PASSWORD_RESET_METHOD = "users/{loginOrEmail}/passwordResetMethod";

    /**
     * Request:
     * <pre>
     * <code>{
     *     "passwordResetMethod": "PASSWORD_RESET_METHOD",
     *     "maidenName": "mothers maiden name",
     *     "resetUrl": "http://reset.url/"
     * }
     * </code>
     * </pre>
     */
    public static final String SEND_PASSWORD_RESET_EMAIL = "users/{loginOrEmail}/passwordResetData";

    /**
     * Request:
     * <pre>
     * <code>{
     *     "text": "password"
     * }
     * </code>
     * </pre>
     */
    public static final String CHANGE_PASSWORD = "users/{loginOrEmail}/password";
}
