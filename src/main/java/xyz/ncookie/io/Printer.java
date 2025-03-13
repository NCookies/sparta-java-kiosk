package xyz.ncookie.io;

import xyz.ncookie.menu.Menu;
import xyz.ncookie.order.ShoppingCart;

import java.util.List;

// 추후 콘솔 외에 파일, 로그, HTTP 응답 등 다양한 방식의 출력을 구현하기 위해 사용
public interface Printer {

    void printWithOutNewLine(String s);     // 개행 포함되지 않고 출력할 때 사용
    void print(String s);
    void printMenuList(List<Menu> menuList);
    void printMenuItemList(Menu menu);
    void printOrderSelect();
    void printShoppingCartList(ShoppingCart shoppingCart);
    void printSeparateLine();
    void printDiscountRate();

}
