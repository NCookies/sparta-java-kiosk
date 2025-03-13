package xyz.ncookie.menu;

import xyz.ncookie.data.KioskMenuSelect;

import java.util.List;

public class Menu {

    private final KioskMenuSelect category;
    private final List<MenuItem> menuItems;

    public Menu(KioskMenuSelect category, List<MenuItem> menuItems) {
        this.category = category;
        this.menuItems = menuItems;
    }

    /*
     * Getter & Setter
     */
    public KioskMenuSelect getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

}
