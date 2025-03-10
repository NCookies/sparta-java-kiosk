package xyz.ncookie.data;

// 키오스크 메뉴 이름 및 카테고리 저장
public enum KioskMenuItem {
    // BURGERS
    SHACK_BURGER("ShackBurger", KioskMenuCategory.BURGERS),
    SMOKE_SHACK("SmokeShack", KioskMenuCategory.BURGERS),
    CHESS_BURGER("Cheeseburger", KioskMenuCategory.BURGERS),
    HAMBURGER("Hamburger", KioskMenuCategory.BURGERS),

    // DRINKS
    COCACOLA("Coca-Cola", KioskMenuCategory.DRINKS),
    COCACOLA_ZERO("Coca-Cola Zero", KioskMenuCategory.DRINKS),
    SPRITE("Sprite", KioskMenuCategory.DRINKS),
    FANTA_ORANGE("Fanta Orange", KioskMenuCategory.DRINKS),

    // DESSERTS
    VANILLA_CUSTARD("Vanilla Custard", KioskMenuCategory.DESSERTS),
    CHOCOLATE_BROWNIE("Chocolate Brownie", KioskMenuCategory.DESSERTS),
    STRAWBERRY_CHEESECAKE("Strawberry Cheesecake", KioskMenuCategory.DESSERTS),
    APPLE_PIE("Apple Pie", KioskMenuCategory.DESSERTS);

    private final String name;
    private final KioskMenuCategory kioskMenuCategory;

    KioskMenuItem(String name, KioskMenuCategory kioskMenuCategory) {
        this.name = name;
        this.kioskMenuCategory = kioskMenuCategory;
    }

    public String getName() {
        return name;
    }

    public KioskMenuCategory getMenuCategory() {
        return kioskMenuCategory;
    }
}
