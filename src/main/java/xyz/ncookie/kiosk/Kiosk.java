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


public class Kiosk {

    private final List<Menu> menuList;

    private final Reader reader = new Reader();
    private final Printer printer = new ConsolePrinter();

    private final ShoppingCart shoppingCart = new ShoppingCart();

    private ScreenStatus screenStatus;
    
    private KioskMenuSelect selectedMenu;
    private MenuItem selectedMenuItem;

    public Kiosk() {
        menuList = new ArrayList<>(List.of(
                new Menu(KioskMenuSelect.BURGERS, KioskMenu.BURGERS),
                new Menu(KioskMenuSelect.DRINKS, KioskMenu.DRINKS),
                new Menu(KioskMenuSelect.DESSERTS, KioskMenu.DESSERTS)
        ));

        screenStatus = ScreenStatus.SCREEN_MAIN;
    }

    // 코드 검색하기 쉽게 하기 위해 setter 메소드 사용
    public void setScreenStatus(ScreenStatus screenStatus) {
        this.screenStatus = screenStatus;
    }

    public void start() {

        // 메뉴 선택 단계에서 사용자가 0(KioskMenuSelect.NONE)을 입력할 때에만 루프와 함께 프로그램을 종료한다.
        while (selectedMenu != KioskMenuSelect.NONE) {
            switch (screenStatus) {
                case SCREEN_MAIN:
                    // ===========================================
                    // 메뉴판(Menu) 선택 화면
                    // ===========================================
                    if (shoppingCart.isCartNotEmpty()) {
                        selectMenuWhenShoppingCartNotEmpty();
                    } else {
                        selectMenu();
                    }
                    break;
                case SELECT_MENU_ITEM:
                    // ===========================================
                    // 상세 메뉴(MenuItem) 선택 화면
                    // ===========================================
                    selectMenuItem();
                    break;
                case SELECT_ADD_CART:
                    // ===========================================
                    // 장바구니 담기
                    // ===========================================
                    processAddMenuItemToShoppingCart();
                    break;
                case SELECT_CONFIRM_ORDER:
                    // ===========================================
                    // 주문 확인
                    // ===========================================
                    processOrder();
                    break;
                case CLEAR_CART:
                    // ===========================================
                    // 장바구니 비우기
                    // ===========================================
                    processClearShoppingCart();
                    break;
                default:
                    break;
            }

        }

        printer.print("프로그램을 종료합니다.");
    }

    // 장바구니가 비어있을 때의 메인화면
    private void selectMenu() {
        // 메뉴 리스트 출력
        printer.printMenuList(menuList);
        printer.print("0. 종료 \t | 종료");

        // 사용자로부터 메뉴 입력 받음
        reader.readValidInput(menuList.size(), true);

        selectedMenu = KioskMenuSelect.fromValue(reader.getValue());
        if (selectedMenu == KioskMenuSelect.NONE) {
            printer.print("종료 선택됨");
        } else {
            setScreenStatus(ScreenStatus.SELECT_MENU_ITEM);
        }
        
        printer.printSeparateLine();
    }

    // 장바구니가 비어있지 않을 때의 메인화면
    private void selectMenuWhenShoppingCartNotEmpty() {
        // 메뉴 리스트 출력
        printer.printMenuList(menuList);
        printer.print("0. 종료 \t | 종료");
        printer.printOrderSelect();             // 주문하기, 장바구니 초기화 메뉴

        // 사용자로부터 메뉴 입력 받음
        reader.readValidInput(menuList.size() + 2, true);

        // 사용자 입력에 따라 screenStatus 설정
        selectedMenu = KioskMenuSelect.fromValue(reader.getValue());
        switch (selectedMenu) {
            case NONE:
                printer.print("종료 선택됨");
                break;
            case BURGERS:
            case DRINKS:
            case DESSERTS:
                setScreenStatus(ScreenStatus.SELECT_MENU_ITEM);
                break;
            case ORDER:
                setScreenStatus(ScreenStatus.SELECT_CONFIRM_ORDER);
                break;
            case CLEAR:
                setScreenStatus(ScreenStatus.CLEAR_CART);
                break;
            default:
                break;
        }

        printer.printSeparateLine();
    }

    // 상세 메뉴 선택
    private void selectMenuItem() {
        Menu menu = menuList.get(selectedMenu.getIndex() - 1);

        // 해당 카테고리의 MenuItem 출력
        printer.print(String.format("[ %s MENU ]", menu.getCategory().getDesc()));

        // MenuItem 리스트 출력
        printer.printMenuItemList(menu);
        printer.print("0. 뒤로가기");

        reader.readValidInput(menu.getMenuItems().size(), true);

        // 메인 화면으로 복귀
        if (reader.getValue() == 0) {
            printer.print("메인 화면으로 돌아갑니다.");
            setScreenStatus(ScreenStatus.SCREEN_MAIN);
            return;
        }

        // 정상적으로 메뉴 아이템 선택
        selectedMenuItem = menu.getMenuItems().get(reader.getValue() - 1);
        setScreenStatus(ScreenStatus.SELECT_ADD_CART);
    }

    // 상품을 장바구니에 담을지 선택
    private boolean selectAddShoppingCartItem() {
        printer.print("위 메뉴를 장바구니에 추가하시겠습니까?");
        printer.print("1. 확인 \t 2. 취소");

        reader.readBooleanChoice();

        return reader.getValue() == 1;
    }

    // 주문하기 또는 메뉴판으로 돌아가기 선택
    private boolean selectOrderOrCancel() {
        printer.print("1. 주문");
        printer.print("2. 메뉴판");

        reader.readBooleanChoice();

        return reader.getValue() == 1;
    }

    // 할인 타입 선택
    private DiscountType selectDiscount() {
        printer.print("할인 정보를 입력해주세요.");
        printer.printDiscountRate();

        reader.readValidInput(DiscountType.values().length, false);

        return DiscountType.fromIndex(reader.getValue());
    }

    // 선택한 상품을 장바구니에 담는 작업 수행
    private void processAddMenuItemToShoppingCart() {
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
        printer.printSeparateLine();

        setScreenStatus(ScreenStatus.SCREEN_MAIN);
    }

    private void processOrder() {
        printer.printShoppingCartList(shoppingCart);

        if (selectOrderOrCancel()) {
            // 주문 객체 생성
            Order order = new Order(shoppingCart.getTotalPrice());

            // ===========================================
            // 할인 적용 및 주문 완료
            // ===========================================
            order.applyDiscount(selectDiscount());
            order.finishOrder();

            shoppingCart.clearShoppingCart();
        }

        printer.print("메인 화면으로 돌아갑니다.");
        printer.printSeparateLine();

        setScreenStatus(ScreenStatus.SCREEN_MAIN);
    }

    private void processClearShoppingCart() {
        printer.print("장바구니를 초기화합니다. 감사합니다.");

        shoppingCart.clearShoppingCart();
        printer.print("(장바구니 초기화 완료)");
        printer.printSeparateLine();

        setScreenStatus(ScreenStatus.SCREEN_MAIN);
    }

}
