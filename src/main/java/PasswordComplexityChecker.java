import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordComplexityChecker {
    private final static int defaultMinimumLength = 6;
    private final int minimumLength;

    private final static Pattern numberPattern = Pattern.compile("[0-9]");
    private final static Pattern lowerCasePattern = Pattern.compile("[a-z]");
    private final static Pattern upperCasePattern = Pattern.compile("[A-Z]");
    private final static Pattern othersPattern = Pattern.compile("[^0-9a-zA-Z]");

    private Matcher numberMtch = numberPattern.matcher("");
    private Matcher lowerCaseMtch = lowerCasePattern.matcher("");
    private Matcher upperCaseMtch = upperCasePattern.matcher("");
    private Matcher othersMtch = othersPattern.matcher("");

    public PasswordComplexityChecker() {
        this(defaultMinimumLength);
    }

    public PasswordComplexityChecker(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    private boolean isRegular(String input) {
        String reverse = new StringBuilder(input).reverse().toString();
        List<String> consecution = CommonPasswords.getConsecutionPwds();
        for (String s : consecution) {
            if (s.contains(input)) {
                return true;
            }
        }
        return input.equals(reverse);
    }

    private boolean isShort(String input) {
        return input.length() < minimumLength;
    }

    private boolean isIncrementalByStep(String input) {
        if (isShort(input) || input.length() <= 2) {
            return false;
        }

        char firstChar = input.charAt(0);
        char secondChar = input.charAt(1);
        int delta = secondChar - firstChar;

        int i;
        for (i = 2; i < input.length(); i++) {
            if ((input.charAt(i) - input.charAt(i - 1)) != delta) {
                return false;
            }
        }
        return true;
    }

    private boolean isCommon(String input) {
        return CommonPasswords.getCommonPwds().contains(input);
    }

    public PasswordComplexityMessage check(String input) throws InvalidArgumentException {
        if (isShort(input)) {
            return new PasswordComplexityMessage(PasswordComplexity.TooSimple, "Your password is too short.");
        }
        if (isIncrementalByStep(input) || isRegular(input)) {
            return new PasswordComplexityMessage(PasswordComplexity.Simple, "Your password is formed by consecutive characters.");
        }
        if (isCommon(input)) {
            return new PasswordComplexityMessage(PasswordComplexity.Simple, "Your password is very common.");
        }

        int types = 0;

        if (numberMtch.reset(input).find()) {
            types++;
        } if (lowerCaseMtch.reset(input).find()) {
            types++;
        } if (upperCaseMtch.reset(input).find()) {
            types++;
        } if (othersMtch.reset(input).find()) {
            types++;
        }

        switch (types) {
            case 1:
                return new PasswordComplexityMessage(PasswordComplexity.Simple, "Only one type of characters is involved.");
            case 2:
                return new PasswordComplexityMessage(PasswordComplexity.Medium);
            case 3:
            case 4:
                return new PasswordComplexityMessage(PasswordComplexity.Strong);
            default:
                throw new InvalidArgumentException(new String[]{"Invalid Characters Found."});
        }
    }

}
