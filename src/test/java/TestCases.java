import org.example.Main;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases {
    @Test
    public void testZero() {
        Assert.assertEquals(Main.getFactorial(0),1);
    }

    @Test
    public void testOne() {
        Assert.assertEquals(Main.getFactorial(1),1);
    }

    @Test
    public void testFive() {
        Assert.assertEquals(Main.getFactorial(5),120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegative() {
        Main.getFactorial(-1);
    }
}
