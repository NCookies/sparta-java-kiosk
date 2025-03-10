package xyz.ncookie.kiosk;

import xyz.ncookie.data.KioskMenu;
import xyz.ncookie.data.KioskMenuCategory;
import xyz.ncookie.menu.Menu;
import xyz.ncookie.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            // ===========================================
            // 메뉴판(Menu) 선택
            // ===========================================
            Optional<KioskMenuCategory> selectedMenu = selectMenu();

            // 올바르지 않은 메뉴 선택할 시 다시 입력
            if (selectedMenu.isEmpty()) {
                continue;
            }

            // 메뉴 선택에서 0이 입력되면 루프 종료
            if (selectedMenu.get() == KioskMenuCategory.NONE) {
                break;
            }

            printSeparateLine();

            // ===========================================
            // 상세 메뉴(MenuItem) 선택
            // ===========================================
            Optional<MenuItem> optionalMenuItem = selectMenuItem(selectedMenu.get());
            if (optionalMenuItem.isPresent()) {
                MenuItem selectedMenuItem = optionalMenuItem.get();
                System.out.printf("선택한 메뉴: %s(W %.2f, %s)\n", selectedMenuItem.name(), selectedMenuItem.price(), selectedMenuItem.desc());
            }

            printSeparateLine();
        }
    }

    // 올바르지 않은 입력(0 ~ menu size 숫자 데이터가 아닌 입력)이 들어오면 null 반환
    public Optional<KioskMenuCategory> selectMenu() {
        // 메뉴 리스트 출력
        printMenuList();
        System.out.println("0. 종료      | 종료");
        
        // 사용자로부터 메뉴 입력 받음
        reader.read(menuList.size());

        if (reader.isValid()) {
            return Optional.of(KioskMenuCategory.fromValue(reader.getValue()));
        } else {
            // 숫자 형식이 아닌 데이터가 입력됨
            System.out.println("올바르지 않은 입력입니다.");
            printSeparateLine();
            return Optional.empty();
        }
    }

    // 0이 입력(메인 화면으로 돌아가기)되면 null 반환
    // 그 외에는 MenuItem 반환
    public Optional<MenuItem> selectMenuItem(KioskMenuCategory menuCategory) {
        Menu selectedMenu = menuList.get(menuCategory.getIndex() - 1);

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
                printSeparateLine();
                continue;
            }
            
            // 메인 화면으로 복귀
            if (reader.getValue() == 0) {
                System.out.println("메인 화면으로 돌아갑니다.");
                return Optional.empty();
            }

            // 정상적으로 메뉴 아이템 선택
            MenuItem selectedMenuItem = selectedMenu.getMenuItems().get(reader.getValue() - 1);
            return Optional.of(selectedMenuItem);
        }
    }

    private void printMenuList() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menuList.get(i).getCategory());
        }
    }

    private void printSeparateLine() {
        System.out.println("===========================================\n");
    }
}
