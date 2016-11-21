package pl.eightbit;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by menchester5 on 28.09.2016.
 */
public class TestTest {

    @Test
    public void test() {


        float v1 = 0.001F;


        float v = ((78678888 * v1));
        new BigDecimal(v).toString();
        System.out.println(v);
    }
}
