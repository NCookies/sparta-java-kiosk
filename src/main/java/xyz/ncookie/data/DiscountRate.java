package xyz.ncookie.data;

import java.util.stream.Stream;

public enum DiscountRate {
    HONORED_CITIZEN(1, "국가유공자", 0.1),
    SOLDIER(2, "군인", 0.05),
    STUDENT(3, "학생", 0.03),
    NORMAL(4, "일반", 0);

    private final int index;
    private final String target;
    private final double rate;

    DiscountRate(int index, String target, double rate) {
        this.index = index;
        this.target = target;
        this.rate = rate;
    }

    public int getIndex() {
        return index;
    }

    public String getTarget() {
        return target;
    }

    public double getRate() {
        return rate;
    }

    public static DiscountRate fromIndex(int index) {
        return Stream.of(values())
                .filter(d -> d.index == index)
                .findFirst()
                .get();
    }
    
    // TODO: 출력 전용 클래스로 옮겨야 함
    public static void printDiscountRate() {
        for (DiscountRate rate : DiscountRate.values()) {
            System.out.printf("%d. %-8s : %4.1f%% \n",
                    rate.ordinal() + 1,
                    rate.getTarget(),
                    rate.getRate() * 100
            );
        }
    }
}
