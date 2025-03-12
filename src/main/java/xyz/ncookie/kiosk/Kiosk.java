package xyz.ncookie.kiosk;

import xyz.ncookie.data.DiscountType;
import xyz.ncookie.data.KioskMenu;
import xyz.ncookie.data.KioskMenuSelect;
import xyz.ncookie.io.ConsolePrinter;
import xyz.ncookie.io.Printer;
import xyz.ncookie.io.Reader;
import xyz.ncookie.menu.Menu;
import xyz.ncookie.menu.MenuItem;
import xyz.ncookie.order.Order;
import xyz.ncookie.order.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Kiosk {

    private final List<Menu> menuList;

    private final Reader reader = new Reader();
    private final Printer printer = new ConsolePrinter();

    private final ShoppingCart shoppingCart = new ShoppingCart();


    public Kiosk() {
        menuList = new ArrayList<>(List.of(
                new Menu(KioskMenuSelect.BURGERS, KioskMenu.BURGERS),
                new Menu(KioskMenuSelect.DRINKS, KioskMenu.DRINKS),
                new Menu(KioskMenuSelect.DESSERTS, KioskMenu.DESSERTS)
        ));
    }

    public void start() {
        // 메뉴 선택 단계에서 사용자가 0(KioskMenuSelect.NONE)을 입력할 때에만 루프와 함께 프로그램을 종료한다.
        while (true) {
            // ===========================================
            // 메뉴판(Menu) 선택
            // ===========================================
            KioskMenuSelect selectedMenu = selectMenu();

            // ===========================================
            // 메뉴 선택에서 0이 입력되면 루프 종료
            // ===========================================
            if (selectedMenu == KioskMenuSelect.NONE) {
                break;
            }
            
            // 장바구니에 상품이 들어있는 상태에서
            if (shoppingCart.isCartNotEmpty()) {
                // ===========================================
                // 주문하기 또는 취소 선택
                // ===========================================
                if (selectedMenu == KioskMenuSelect.ORDER) {    // 주문하기
                    printer.printShoppingCartList(shoppingCart);

                    if (selectOrder()) {
                        // 주문 객체 생성
                        Order order = new Order(shoppingCart.getTotalPrice());

                        // ===========================================
                        // 할인 적용 및 주문 완료
                        // ===========================================
                        order.applyDiscount(selectDiscount());
                        order.finishOrder();
                    }

                    printer.print("메인 화면으로 돌아갑니다.");
                    printer.printSeparateLine();
                    continue;
                } else if (selectedMenu == KioskMenuSelect.CANCEL) {  // 주문 취소하기
                    // 초기 상태로 복구 (장바구니 비우기)
                    printer.print("주문을 취소합니다. 감사합니다.");

                    shoppingCart.clearShoppingCart();
                    printer.print("(장바구니 초기화 완료)");
                    printer.printSeparateLine();

                    continue;
                }
            }

            printer.printSeparateLine();

            // ===========================================
            // 상세 메뉴(MenuItem) 선택
            // ===========================================
            Optional<MenuItem> optionalMenuItem = selectMenuItem(selectedMenu);
            if (optionalMenuItem.isPresent()) {
                MenuItem selectedMenuItem = optionalMenuItem.get();
                printer.print(String.format("선택한 메뉴: %s(W %.2f, %s)", selectedMenuItem.name(), selectedMenuItem.price(), selectedMenuItem.desc()));
                printer.printSeparateLine();

                // ===========================================
                // 장바구니 선택
                // ===========================================
                if (selectAddShoppingCartItem()) {
                    printer.print(String.format("%s 이(가) 장바구니에 추가되었습니다.", selectedMenuItem.name()));
                    shoppingCart.addCartItem(selectedMenu, selectedMenuItem);
                } else {
                    printer.print("장바구니 담기를 취소합니다.");
                }
            }

            printer.printSeparateLine();
        }

        printer.print("프로그램을 종료합니다.");
    }

    // 올바르지 않은 입력(0 ~ menu size 숫자 데이터가 아닌 입력)이 들어오면 null 반환
    private KioskMenuSelect selectMenu() {
        // 메뉴 리스트 출력
        printer.printMenuList(menuList);
        printer.print("0. 종료 \t | 종료");

        // 장바구니가 비어있지 않다면 내용 출력
        if (shoppingCart.isCartNotEmpty()) {
            printer.printOrderSelect();

            // 사용자로부터 메뉴 입력 받음. 장바구니에 상품이 들어있다면 선택지 2개 추가
            reader.readValidInput(menuList.size() + 2);
        } else {
            // 사용자로부터 메뉴 입력 받음
            reader.readValidInput(menuList.size());
        }

        return KioskMenuSelect.fromValue(reader.getValue());
    }

    // 0이 입력(메인 화면으로 돌아가기)되면 null 반환
    // 그 외에는 MenuItem 반환
    private Optional<MenuItem> selectMenuItem(KioskMenuSelect menuCategory) {
        Menu selectedMenu = menuList.get(menuCategory.getIndex() - 1);

        // 해당 카테고리의 MenuItem 출력
        printer.print(String.format("[ %s MENU ]", selectedMenu.getCategory().getDesc()));

        // MenuItem 리스트 출력
        printer.printMenuItemList(selectedMenu);
        printer.print("0. 뒤로가기");

        reader.readValidInput(selectedMenu.getMenuItems().size());

        // 메인 화면으로 복귀
        if (reader.getValue() == 0) {
            printer.print("메인 화면으로 돌아갑니다.");
            return Optional.empty();
        }

        // 정상적으로 메뉴 아이템 선택
        MenuItem selectedMenuItem = selectedMenu.getMenuItems().get(reader.getValue() - 1);
        return Optional.of(selectedMenuItem);
    }

    private boolean selectAddShoppingCartItem() {
        printer.print("위 메뉴를 장바구니에 추가하시겠습니까?");
        printer.print("1. 확인 \t 2. 취소");

        reader.readBooleanChoice();

        return reader.getValue() == 1;
    }

    private boolean selectOrder() {
        printer.print("1. 주문");
        printer.print("2. 메뉴판");

        reader.readBooleanChoice();

        return reader.getValue() == 1;
    }

    private DiscountType selectDiscount() {
        printer.print("할인 정보를 입력해주세요.");
        printer.printDiscountRate();

        reader.readValidInput(4);

        return DiscountType.fromIndex(reader.getValue());
    }

}
