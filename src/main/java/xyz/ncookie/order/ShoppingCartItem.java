package xyz.ncookie.order;

import xyz.ncookie.data.KioskMenuCategory;
import xyz.ncookie.menu.MenuItem;

public class ShoppingCartItem {
    private final KioskMenuCategory category;     // 메뉴 카테고리
    private final MenuItem menuItem;              // 메뉴 정보
    private int quantity;                         // 수량

    public ShoppingCartItem(KioskMenuCategory category, MenuItem menuItem, int quantity) {
        this.category = category;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public KioskMenuCategory getCategory() {
        return category;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
