package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {

        long longNumber = number;

        if (number <= 1)
            throw new IllegalNumberException("IllegalNumberException");

        for (long i = 2; i * i <= longNumber; i++) {
            if (longNumber % i == 0) {
                return (false);
            }
        }

        return (true);

    }

    public int digitsSum(int num) {

        long number = num;

        int sum = 0;

        if (number < 0) {
            number = -number;
        }

        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }

        return (sum);

    }

    class IllegalNumberException extends RuntimeException {
        public IllegalNumberException(String msg) {
            super(msg);
        }
    }
}
