package xyz.ncookie.order;

import xyz.ncookie.data.KioskMenuSelect;
import xyz.ncookie.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    private final List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
    private double totalPrice;      // 장바구니 총 가격

    public void addCartItem(KioskMenuSelect category, MenuItem menuItem) {
        // 이미 장바구니에 담겨있던 상품이라면 수량 1 증가
        // 해당 키오스크에서는 메뉴는 한 번에 1개만 담을 수 있다.
        for (ShoppingCartItem item : shoppingCartItemList) {
            if (Objects.equals(item.getMenuItem().name(), menuItem.name())) {
                item.setQuantity(item.getQuantity() + 1);
                totalPrice += menuItem.price();
                return;
            }
        }

        // 새로 장바구니에 추가
        shoppingCartItemList.add(new ShoppingCartItem(category, menuItem, 1));
        totalPrice += menuItem.price();
    }

    public List<ShoppingCartItem> getShoppingCartListByCategory(KioskMenuSelect kioskMenuSelect) {
        return shoppingCartItemList.stream()
                .filter(s -> s.getCategory() == kioskMenuSelect)
                .toList();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void clearShoppingCart() {
        // stream 활용해서 특정 메뉴 장바구니에서 제거
        shoppingCartItemList.stream()
                .filter(s -> Objects.equals(s.getMenuItem().name(), "SmokeShack"))
                .toList()
                        .forEach(
                                shoppingCartItemList::remove
                        );

        shoppingCartItemList.clear();
        totalPrice = 0;
    }

    public boolean isCartNotEmpty() {
        return !shoppingCartItemList.isEmpty();
    }

}
