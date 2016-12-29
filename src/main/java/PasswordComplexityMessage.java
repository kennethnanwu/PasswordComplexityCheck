/**
 * Created by nan on 2016-12-28.
 */
public class PasswordComplexityMessage {
    private final PasswordComplexity complexity;
    private String message;

    public PasswordComplexityMessage(PasswordComplexity complexity) {
        this(complexity, "");
    }

    public PasswordComplexityMessage(PasswordComplexity complexity, String message) {
        this.complexity = complexity;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PasswordComplexity getComplexity() {
        return complexity;
    }

    public String getMessage() {
        return message;
    }
}
