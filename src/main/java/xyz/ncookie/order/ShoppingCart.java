package xyz.ncookie.order;

import xyz.ncookie.data.KioskMenuCategory;
import xyz.ncookie.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    private final List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

    public void addCartItem(KioskMenuCategory category, MenuItem menuItem) {
        // 이미 장바구니에 담겨있던 상품이라면 수량 1 증가
        // 해당 키오스크에서는 메뉴는 한 번에 1개만 담을 수 있다.
        for (ShoppingCartItem item : shoppingCartItemList) {
            if (Objects.equals(item.getMenuItem().name(), menuItem.name())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }

        // 새로 장바구니에 추가
        shoppingCartItemList.add(new ShoppingCartItem(category, menuItem, 1));
    }

    public List<ShoppingCartItem> getShoppingCartList() {
        return shoppingCartItemList;
    }

    public List<ShoppingCartItem> getShoppingCartListByCategory() {
        return null;
    }

    public boolean isCartEmpty() {
        return shoppingCartItemList.isEmpty();
    }

}
