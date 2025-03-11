package xyz.ncookie.io;

import xyz.ncookie.data.DiscountRate;
import xyz.ncookie.data.KioskMenu;
import xyz.ncookie.data.KioskMenuSelect;
import xyz.ncookie.menu.Menu;
import xyz.ncookie.order.ShoppingCart;

import java.util.List;

public class Printer {

    public void printMenuList(List<Menu> menuList) {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menuList.get(i).getCategory());
        }
    }

    public void printOrderSelect() {
        System.out.println("[ ORDER MENU ]");
        System.out.println("4. Orders \t | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. Cancel \t | 진행 중인 주문을 취소합니다.");
    }

    public void printShoppingCartList(ShoppingCart shoppingCart) {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ Orders ]");

        for (KioskMenuSelect category : KioskMenu.MENU_CATEGORIES) {
            System.out.printf("<< %s >> \n", category);
            shoppingCart.getShoppingCartListByCategory(category)
                    .forEach(s ->
                            System.out.printf("%d EA * %s \t | W %.2f\t | %s\n",
                                    s.getQuantity(),
                                    s.getMenuItem().name(),
                                    s.getMenuItem().price(),
                                    s.getMenuItem().desc()
                            )
                    );
        }

        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + shoppingCart.getTotalPrice());
        System.out.println();
    }

    public void printSeparateLine() {
        System.out.println("===========================================\n");
    }

}
