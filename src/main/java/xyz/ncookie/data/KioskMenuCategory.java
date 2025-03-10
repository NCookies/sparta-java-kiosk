package xyz.ncookie.data;

import java.util.stream.Stream;

public enum KioskMenuCategory {
    NONE(0), BURGERS(1), DRINKS(2), DESSERTS(3);

    private final int index;

    KioskMenuCategory(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static KioskMenuCategory fromValue(int value) {
        return Stream.of(values())
                .filter(c -> c.index == value)
                .findFirst()
                .get();     // InputReader에서 이미 숫자 범위를 확인하고 사용하므로 바로 get() 해도 됨
    }
}
