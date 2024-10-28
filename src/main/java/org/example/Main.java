package org.example;

public class Main {

    public static long getFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не должно быть отрицательным");
        }
        long result = 1L;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getFactorial(0));
        System.out.println(getFactorial(1));
        System.out.println(getFactorial(5));
        System.out.println(getFactorial(21));
        System.out.println(getFactorial(-5));
    }
}