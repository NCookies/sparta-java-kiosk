package xyz.ncookie.data;

import xyz.ncookie.menu.MenuItem;

import java.util.List;

// 키오스크에서 사용하는 상세 메뉴 정보
public class KioskMenu {

    public static final List<MenuItem> BURGERS = List.of(
        new MenuItem(KioskMenuItem.SHACK_BURGER.getName(), 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
        new MenuItem(KioskMenuItem.SMOKE_SHACK.getName(), 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
        new MenuItem(KioskMenuItem.CHESS_BURGER.getName(), 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
        new MenuItem(KioskMenuItem.HAMBURGER.getName(), 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
    );

    public static final List<MenuItem> DRINKS = List.of(
        new MenuItem(KioskMenuItem.COCACOLA.getName(), 2.5, "전통적인 탄산 콜라"),
        new MenuItem(KioskMenuItem.COCACOLA_ZERO.getName(), 2.5, "설탕 없이도 콜라의 맛을 즐길 수 있는 제로 칼로리 탄산음료"),
        new MenuItem(KioskMenuItem.SPRITE.getName(), 2.5, "상쾌한 레몬 라임 맛의 탄산음료"),
        new MenuItem(KioskMenuItem.FANTA_ORANGE.getName(), 2.5, "달콤한 오렌지 맛의 탄산음료")
    );

    public static final List<MenuItem> DESSERTS = List.of(
        new MenuItem(KioskMenuItem.VANILLA_CUSTARD.getName(), 4.5, "부드럽고 진한 바닐라 커스터드 아이스크림"),
        new MenuItem(KioskMenuItem.CHOCOLATE_BROWNIE.getName(), 3.9, "진한 초콜릿 맛이 가득한 브라우니"),
        new MenuItem(KioskMenuItem.STRAWBERRY_CHEESECAKE.getName(), 5.2, "상큼한 딸기와 부드러운 치즈케이크 조합"),
        new MenuItem(KioskMenuItem.APPLE_PIE.getName(), 4.8, "달콤한 사과 필링과 바삭한 크러스트가 어우러진 애플파이")
    );

}
