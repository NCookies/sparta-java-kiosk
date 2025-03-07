package xyz.ncookie;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems;

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int input = -1;

        // 마지막에 "0"이 입력되기 전까지 루프
        while (input != 0) {
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menuItems.get(i);
                System.out.printf("%d. %s\t | W %.2f\t | %s\n", i + 1, item.name, item.price, item.desc);
            }
            System.out.println("0. 종료      | 종료");

            input = sc.nextInt();

            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                    MenuItem orderedItem = menuItems.get(input - 1);
                    System.out.printf("%s(W %.2f, %s) 구매!\n", orderedItem.name, orderedItem.price, orderedItem.desc);
                    break;
                case 0:
                    System.out.println("종료!");
                    break;
                default:
                    System.out.println("올바르지 않은 입력입니다!");
                    break;
            }
        }
    }
}
