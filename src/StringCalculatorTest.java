import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void addsEmptyStringTo0() {
        assertEquals(StringCalculator.add(""), (0));
    }

    @Test
    public void addsSingleNumberToItself() {
        assertEquals(StringCalculator.add("1"), (1));
        assertEquals(StringCalculator.add("121"), (121));
    }

    @Test
    public void addsTwoNumbersSeperatedByComma() {
        assertEquals(StringCalculator.add("1,2"), (3));
        assertEquals(StringCalculator.add("121,144"), (265));
    }

    @Test
    public void addsUnknownAmountOfNumbersSeperatedByComma() {
        assertEquals(StringCalculator.add("1,2,3"), (6));
        assertEquals(StringCalculator.add("10,20,30,40"), (100));
    }

    @Test
    public void addsNumbersSeperatedByNewLine() {
        assertEquals(StringCalculator.add("1\\n2,3"), (6));
        assertEquals(StringCalculator.add("10\\n20,30\\n40"), (100));
    }

    @Test
    public void usesDifferentDelimiters() {
        assertEquals(StringCalculator.add("//;\\n1;2"), (3));
    }


    @Test
    public void throwsOnNegativeNumber() {
        try {
            StringCalculator.add("-3");
        } catch (IllegalArgumentException ex) {
            assertEquals("Negative numbers: [-3]", ex.getMessage());
        }
    }

    @Test
    public void throwsOnNegativeNumbersWithAllowedNumbers() {
        try {
            StringCalculator.add("-3,1,-5,15,-7,1");
        } catch (IllegalArgumentException ex) {
            assertEquals("Negative numbers: [-3, -5, -7]", ex.getMessage());
        }
    }

    @Test
    public void ignoresNumbsAbove1000() {
        assertEquals(StringCalculator.add("2,1001"), (2));
        assertEquals(StringCalculator.add("2,1000"), (1002));
    }

    @Test
    public void acceptsDelimiterOfArbitraryLength() {
        assertEquals(StringCalculator.add("//[***]\\n1***2***3"), (6));
    }

    @Test
    public void acceptsMultipleDelimiters() {
        assertEquals(StringCalculator.add("//[++][+]\\n1++2+3"), (6));
        assertEquals(StringCalculator.add("//[{}][{123}][\\n123]\\n123,2{}123\\n1233{123}123"), (374));
    }
}