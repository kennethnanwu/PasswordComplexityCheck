import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nan on 2016-12-27.
 */
public class CommonPasswordsTest {

    @Test
    public void commonPasswordTest() {
        String password = "password";
        String notIn = "dakl123JKHKL./";

        assertTrue(CommonPasswords.getCommonPwds().contains(password));
        assertFalse(CommonPasswords.getCommonPwds().contains(notIn));
    }

    @Test
    public void checkTest() throws InvalidArgumentException {
        String tooShortPwd = "k2f.";
        String tooSimplePwd = "asdfghj";
        String reversePwd = "qazzaq";
        String strongPwd = "Str0ng";
        PasswordComplexityChecker checker = new PasswordComplexityChecker();

        assertEquals(PasswordComplexity.TooSimple, checker.check(tooShortPwd).getComplexity());
        assertEquals(PasswordComplexity.Simple, checker.check(tooSimplePwd).getComplexity());
        assertEquals(PasswordComplexity.Simple, checker.check(reversePwd).getComplexity());
        assertEquals(PasswordComplexity.Strong, checker.check(strongPwd).getComplexity());
    }
}
