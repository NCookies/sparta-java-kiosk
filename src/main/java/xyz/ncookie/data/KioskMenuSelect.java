package xyz.ncookie.data;

import java.util.stream.Stream;

public enum KioskMenuSelect {

    NONE(0, "NONE"),
    BURGERS(1, "BURGERS"),
    DRINKS(2, "DRINKS"),
    DESSERTS(3, "DESSERTS"),
    ORDER(4, "ORDER"),
    CLEAR(5, "CLEAR");

    private final int index;
    private final String desc;

    KioskMenuSelect(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }

    public static KioskMenuSelect fromValue(int value) {
        return Stream.of(values())
                .filter(c -> c.index == value)
                .findFirst()
                .get();     // InputReader에서 이미 숫자 범위를 확인하고 사용하므로 바로 get() 해도 됨
    }

}
