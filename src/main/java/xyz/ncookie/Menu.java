package xyz.ncookie;

import java.util.List;

public class Menu {
    private final String category;
    private final List<MenuItem> menuItems;

    public Menu(String category, List<MenuItem> menuItems) {
        this.category = category;
        this.menuItems = menuItems;
    }

    public void printMenuItems() {
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.printf("%d. %s\t | W %.2f\t | %s\n", i + 1, item.getName(), item.getPrice(), item.getDesc());
        }
    }

    /*
     * Getter & Setter
     */
    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
