import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.List;

public class SubnetCalculatorTest {
    @Test
    void testcheckDots() {
        Assertions.assertFalse(SubnetCalculator.checkDots(".."));
        Assertions.assertFalse(SubnetCalculator.checkDots("...."));
        Assertions.assertTrue(SubnetCalculator.checkDots("..."));
    }

    @Test
    void testIpLength() {
        Assertions.assertFalse(SubnetCalculator.isLenghtRight("1.1.1"));
        Assertions.assertFalse(SubnetCalculator.isLenghtRight("111111.1.1.111111"));
        Assertions.assertTrue(SubnetCalculator.isLenghtRight("1.1.1.1"));
        Assertions.assertTrue(SubnetCalculator.isLenghtRight("111.111.111.111"));
    }

    @Test
    void testSplitIp() {
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.splitIP("..1.1"));
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.splitIP("1.1.a.1"));
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.splitIP("0.1.1.1"));
        Assertions.assertDoesNotThrow(() -> SubnetCalculator.splitIP("1.1.1.1"));
    }

    @Test
    void testipSequenzLength (){
        Assertions.assertThrows(InputMismatchException.class, () -> SubnetCalculator.ipSequentLength(256));
        Assertions.assertDoesNotThrow(() -> SubnetCalculator.ipSequentLength(0));
    }
    @Test
    void testSplitSNM() {
        Assertions.assertFalse(SubnetCalculator.splitSNM("..1.1"));
        Assertions.assertFalse(SubnetCalculator.splitSNM("a.1.a.1"));
        Assertions.assertFalse(SubnetCalculator.splitSNM("254.1.1.1"));
        Assertions.assertTrue (SubnetCalculator.splitSNM("255.0.0.0"));
        Assertions.assertTrue (SubnetCalculator.splitSNM("255.255.255.255"));
    }
    @Test
    void testsnmSequenzLength (){
        List<Integer> testList = List.of(256, 256, 256, 256);
        Assertions.assertFalse(SubnetCalculator.snmValidation(testList));
        testList = List.of(255, 0, 255, 0);
        Assertions.assertFalse(SubnetCalculator.snmValidation(testList));
        testList = List.of(255, 0, 0, 0);
        Assertions.assertTrue(SubnetCalculator.snmValidation(testList));



    }
}
