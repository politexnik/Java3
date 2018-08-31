import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test1 {
    private int[] arrIn;
    private int[] arrOut;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {new Object[]{1,4,6},new Object[]{6}},
            {new Object[]{1,4,6},new Object[]{6}}
        });
    }



    public Test1(int[] arrIn, int[] arrOut) {
        this.arrIn = arrIn;
        this.arrOut = arrOut;
    }

    Tusk tusk;

    @Before
    public void init(){
         tusk = new Tusk();
    }

    @Test
    public void test1() {
        Assert.assertEquals( tusk.arrOutAfter4(arrIn), arrOut);
    }

}
