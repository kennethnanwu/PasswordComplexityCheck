import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nan on 2016-12-28.
 */
public class PasswordComplexityCheckerTest {

    @Test
    public void charSubstructionTest() {
        String testString = "ace";
        char aChar = testString.charAt(0);
        char cChar = testString.charAt(1);
        char eChar = testString.charAt(2);
        System.out.print(cChar - aChar);
        assertEquals(cChar - aChar, eChar - cChar);
    }
}
