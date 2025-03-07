package xyz.ncookie.kiosk;

import xyz.ncookie.menu.Menu;
import xyz.ncookie.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menuList;
    private final Scanner sc = new Scanner(System.in);

    public Kiosk(List<MenuItem> burgers, List<MenuItem> drinks, List<MenuItem> desserts) {
        menuList = new ArrayList<>(List.of(
                new Menu("Burgers", burgers),
                new Menu("Drinks", drinks),
                new Menu("Desserts", desserts)
        ));
    }

    public void start() {
        int inputMenu = -1;
        int inputMenuItem = -1;

        // 마지막에 "0"이 입력되기 전까지 루프
        while (true) {
            inputMenu = selectMenu();
            if (inputMenu == 0) {
                System.out.println("종료 선택됨");
                break;
            }

            // 정상적으로 메뉴 선택
            if (inputMenu > 0 && inputMenu <= menuList.size()) {
                Menu selectedMenu = menuList.get(inputMenu - 1);

                // 해당 카테고리의 MenuItem 출력
                System.out.printf("[ %s ] MENU\n", selectedMenu.getCategory());

                selectedMenu.printMenuItems();
                System.out.println("0. 뒤로가기");

                inputMenuItem = sc.nextInt();
                
                // 뒤로 가기
                if (inputMenuItem == 0) {
                    System.out.println("메인 화면으로 돌아갑니다.");
                    continue;
                }
                
                // 정상적으로 메뉴 아이템 선택
                if (inputMenuItem > 0 && inputMenuItem <= selectedMenu.getMenuItems().size()) {
                    MenuItem orderedItem = selectedMenu.getMenuItems().get(inputMenuItem - 1);
                    System.out.printf("선택한 메뉴: %s(W %.2f, %s)\n", orderedItem.getName(), orderedItem.getPrice(), orderedItem.getDesc());
                } else {
                    System.out.println("올바르지 않은 입력입니다!");    
                }
            } else {
                System.out.println("올바르지 않은 입력입니다!");
            }
        }
    }

    public int selectMenu() {
        int inputMenu = -1;

        printMenuList();
        System.out.println("0. 종료      | 종료");

        inputMenu = sc.nextInt();
        return inputMenu;
    }

    public void printMenuList() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menuList.get(i).getCategory());
        }
    }
}
