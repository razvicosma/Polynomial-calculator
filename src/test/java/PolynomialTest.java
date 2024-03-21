import com.example.polynomial.model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolynomialTest {
    @Test
    void testAdd() {
        Polynomial a = new Polynomial("8x^5 - 3x^2 + x");
        Polynomial b = new Polynomial("6x^4 + 5x^2 - x");

        Polynomial result = a.add(b);

        assertEquals("8x^5 + 6x^4 + 2x^2 ",result.toString());
    }
    @Test
    void testSubtract() {
        Polynomial a = new Polynomial("8x^5 - 3x^2 + x");
        Polynomial b = new Polynomial("6x^4 + 5x^2 - x");

        Polynomial result = a.subtract(b);

        assertEquals("8x^5 - 6x^4 - 8x^2 + 2x ",result.toString());
    }
    @Test
    void testMultiply() {
        Polynomial a = new Polynomial("8x^5 - 3x^2 + x");
        Polynomial b = new Polynomial("6x^4 + 5x^2 - x");

        Polynomial result = a.product(b);

        assertEquals("48x^9 + 40x^7 - 26x^6 + 6x^5 - 15x^4 + 8x^3 - x^2 ",result.toString());
    }
    @Test
    void testDivide() {
        Polynomial a = new Polynomial("8x^5 - 3x^2 + x");
        Polynomial b = new Polynomial("6x^4 + 5x^2 - x");

        Polynomial[] result = a.div(b);

        assertEquals("1,33x ",result[0].toString());
        assertEquals("-6,67x^3 - 1,67x^2 + x ",result[1].toString());
    }
    @Test
    void testDerivate() {
        Polynomial a = new Polynomial("8x^5 - 3x^2 + x");

        Polynomial result = a.derivate();

        assertEquals("40x^4 - 6x + 1",result.toString());
    }
    @Test
    void testIntegrate() {
        Polynomial a = new Polynomial("8x^5 - 3x^2 + x");

        Polynomial result = a.integrate();

        assertEquals("1,33x^6 - x^3 + 0,5x^2 ",result.toString());
    }
}
