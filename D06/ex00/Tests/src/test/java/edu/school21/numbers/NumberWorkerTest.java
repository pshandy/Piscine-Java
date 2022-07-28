package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    @ParameterizedTest(name = "{index}: isPrime({0})")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 2147483647})
    public void isPrimeForPrimes(int prime) {
        assertTrue(new NumberWorker().isPrime(prime));
    }

    @ParameterizedTest(name = "{index}: isNotPrime({0})")
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 94, 95, 96, 98, 99})
    public void isPrimeForNotPrimes(int notPrime) {
        assertFalse(new NumberWorker().isPrime(notPrime));
    }

    @ParameterizedTest(name = "{index}: isPrimeForIncorrectNumbers({0})")
    @ValueSource(ints = {Integer.MAX_VALUE + 1, -1234, 0, 1, -11111111, Integer.MIN_VALUE})
    public void isPrimeForIncorrectNumbers(int num) {
        assertThrows(NumberWorker.IllegalNumberException.class, () -> new NumberWorker().isPrime(num));
    }

    @ParameterizedTest(name = "{index}: digitSumCheck({0}, {1})")
    @CsvFileSource(resources = "/data.csv")
    public void digitSumCheck(int input, int expected) {
        assertEquals(new NumberWorker().digitsSum(input), expected);
    }

}
