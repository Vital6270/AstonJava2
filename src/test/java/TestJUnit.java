import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestJUnit {

    @Test
    public void testZero() {
        assertEquals(1, Main.getFactorial(0));
    }

    @Test
    public void testOne() {
        assertEquals(1, Main.getFactorial(1));
    }

    @Test
    public void testFive() {
        assertEquals(120, Main.getFactorial(5));
    }

    @Test
    public void testNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Main.getFactorial(-1);
        });

        assertEquals("Число не должно быть отрицательным", exception.getMessage());
    }

}