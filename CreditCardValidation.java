package progexec;

import java.util.Scanner;

public class CreditCardValidation {

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        int length = getSize(number);
        return (length >= 13 && length <= 16) &&
               (prefixMatched(number, 4) || prefixMatched(number, 5) ||
                prefixMatched(number, 37) || prefixMatched(number, 6)) &&
               (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0;
    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        boolean isEvenPlace = false;
        while (number > 0) {
            int digit = (int)(number % 10);
            if (isEvenPlace) {
                sum += getDigit(digit * 2);
            }
            isEvenPlace = !isEvenPlace;
            number /= 10;
        }
        return sum;
    }

    /** Return this number if it is a single digit, otherwise,
     * return the sum of the two digits */
    public static int getDigit(int number) {
        return (number < 10) ? number : (number / 10 + number % 10);
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        boolean isOddPlace = true;
        while (number > 0) {
            int digit = (int)(number % 10);
            if (isOddPlace) {
                sum += digit;
            }
            isOddPlace = !isOddPlace;
            number /= 10;
        }
        return sum;
    }

    /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        int prefixSize = getSize(d);
        return getPrefix(number, prefixSize) == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);
        if (numStr.length() < k) {
            return number;
        }
        return Long.parseLong(numStr.substring(0, k));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number as a long integer: ");
        long number = input.nextLong();

        if (isValid(number)) {
            System.out.println(number + " is valid");
        } else {
            System.out.println(number + " is invalid");
        }
    }
}
