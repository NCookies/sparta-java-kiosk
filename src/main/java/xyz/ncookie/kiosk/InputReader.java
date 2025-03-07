package xyz.ncookie.kiosk;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {
    private boolean isValid;
    private int value;
    private final Scanner sc;

    public InputReader() {
        sc = new Scanner(System.in);
    }

    public void read(int listSize) {
        isValid = false;
        value = -1;

        try {
            value = sc.nextInt();
            if (value > 0 && value <= listSize) {
                isValid = true;
            }
        } catch (InputMismatchException e) {
            isValid = false;
            sc.next();
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public int getValue() {
        return value;
    }
}
