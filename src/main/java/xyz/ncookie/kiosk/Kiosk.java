package xyz.ncookie.kiosk;

import xyz.ncookie.menu.Menu;
import xyz.ncookie.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Kiosk {
    private final List<Menu> menuList;

    private final InputReader reader = new InputReader();

    public Kiosk() {
        menuList = new ArrayList<>(List.of(
                new Menu("Burgers", KioskMenu.BURGERS),
                new Menu("Drinks", KioskMenu.DRINKS),
                new Menu("Desserts", KioskMenu.DESSERTS)
        ));
    }

    public void start() {
        while (true) {
            // 메뉴판 선택
            int inputMenu = selectMenu();
            
            // 메뉴 선택에서 0이 입력되면 루프 종료
            if (inputMenu == 0) {
                break;
            }

            // 올바르지 않은 메뉴 선택할 시 다시 입력
            if (inputMenu == -1) {
                continue;
            }

            // MenuItem 입력
            selectMenuItem(inputMenu);
        }
    }

    public int selectMenu() {
        // 메뉴 리스트 출력
        printMenuList();
        System.out.println("0. 종료      | 종료");
        
        // 사용자로부터 메뉴 입력 받음
        reader.read(menuList.size());

        if (reader.isValid()) {
            return reader.getValue();
        } else {
            // 숫자 형식이 아닌 데이터가 입력됨
            System.out.println("올바르지 않은 입력입니다.");
            return -1;
        }
    }

    public void selectMenuItem(int selectedMenuIndex) {
        Menu selectedMenu = menuList.get(selectedMenuIndex - 1);

        while (true) {
            // 해당 카테고리의 MenuItem 출력
            System.out.printf("[ %s ] MENU\n", selectedMenu.getCategory());

            // MenuItem 리스트 출력
            selectedMenu.printMenuItems();
            System.out.println("0. 뒤로가기");

            reader.read(selectedMenu.getMenuItems().size());

            // 유효한 값을 입력받을 때까지 루프
            if (!reader.isValid()) {
                System.out.println("올바르지 않은 입력입니다!");
                continue;
            }

            if (reader.getValue() == 0) {
                System.out.println("메인 화면으로 돌아갑니다.");
                break;
            }

            // 정상적으로 메뉴 아이템 선택
            MenuItem orderedItem = selectedMenu.getMenuItems().get(reader.getValue() - 1);
            System.out.printf("선택한 메뉴: %s(W %.2f, %s)\n", orderedItem.getName(), orderedItem.getPrice(), orderedItem.getDesc());
            break;
        }
    }

    public void printMenuList() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menuList.get(i).getCategory());
        }
    }
}
