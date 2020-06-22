import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest {
    @Test
    public void testIsSameNumber() {
        int a = 128;
        int b = 128;
        int c = 500;
        boolean actual1 = Flik.isSameNumber(a, b);
        boolean actual2 = Flik.isSameNumber(a, c);

        assertTrue(actual1);
        assertFalse(actual2);

    }
}
