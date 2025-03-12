package xyz.ncookie.data;

import java.util.stream.Stream;

public enum DiscountType {

    HONORED_CITIZEN(1, "국가유공자", 0.1),
    SOLDIER(2, "군인", 0.05),
    STUDENT(3, "학생", 0.03),
    NORMAL(4, "일반", 0);

    private final int index;
    private final String target;
    private final double rate;

    DiscountType(int index, String target, double rate) {
        this.index = index;
        this.target = target;
        this.rate = rate;
    }

    public String getTarget() {
        return target;
    }

    public double getRate() {
        return rate;
    }

    public static DiscountType fromIndex(int index) {
        return Stream.of(values())
                .filter(d -> d.index == index)
                .findFirst()
                .get();
    }

}
