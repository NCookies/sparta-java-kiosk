package xyz.ncookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

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

        System.out.println("프로그램을 종료합니다.");
    }
}
