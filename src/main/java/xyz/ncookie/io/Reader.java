package xyz.ncookie.io;

import java.util.Scanner;

public class Reader {
    
    private Integer value;

    private final Printer printer = new ConsolePrinter();
    private final Scanner sc = new Scanner(System.in);

    // 유효한 입력값을 받을 때까지 루프. 주어진 선택지의 입력값만 받음
    public void readValidInput(int listSize, boolean includeZero) {
        init();
        
        // includeZero 옵션에 따라 valid 범위에 0을 포함
        int validRangeFirstValue = includeZero ? 0 : 1;

        while (true) {
            try {
                printer.printWithOutNewLine("입력: ");
                value = Integer.parseInt(sc.nextLine());
                if (value >= validRangeFirstValue && value <= listSize) {
                    break;
                } else {
                    noticeInvalidInput();
                }
            } catch (NumberFormatException e) {
                noticeInvalidInput();
            }
        }
    }

    // 두 가지 선택지만 유효할 때 사용
    public void readBooleanChoice() {
        init();

        while (true) {
            try {
                printer.printWithOutNewLine("입력: ");
                value = Integer.parseInt(sc.nextLine());
                if (value == 1 || value == 2) {
                    break;
                } else {
                    noticeInvalidInput();
                }
            } catch (NumberFormatException e) {
                noticeInvalidInput();
            }
        }
    }

    private void init() {
        value = Integer.MIN_VALUE;
    }

    public int getValue() {
        return value;
    }
    
    // 유효하지 않은 입력값이 들어왔을 때 출력
    private void noticeInvalidInput() {
        printer.print("유효하지 않은 입력입니다.");
        printer.printSeparateLine();
    }
    
}
