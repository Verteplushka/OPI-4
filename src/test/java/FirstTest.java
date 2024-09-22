import org.junit.Test;
import static org.junit.Assert.*;

public class FirstTest {

    @Test
    public void testAddition() {
        int a = 5;
        int b = 3;
        int result = a + b;
        assertEquals(8, result);
    }

    @Test
    public void testSubtraction() {
        int a = 5;
        int b = 3;
        int result = a - b;
        assertEquals(2, result);
    }
}