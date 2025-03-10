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

    public void printMenuItems() {
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.printf("%d. %s\t | W %.2f\t | %s\n", i + 1, item.name(), item.price(), item.desc());
        }
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
