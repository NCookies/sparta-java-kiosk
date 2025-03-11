package xyz.ncookie.order;

import xyz.ncookie.data.DiscountType;
import xyz.ncookie.io.ConsolePrinter;
import xyz.ncookie.io.Printer;

public class Order {

    private Double totalPayment;        // 최종 결제 가격
    
    private Double discountRate;        // 할인율
    private Double discountedPrice;     // 할인 받은 가격
    
    private final Printer printer = new ConsolePrinter();

    // 장바구니로부터 총 가격을 인계받음
    public Order(Double totalPrice) {
        this.totalPayment = totalPrice;
    }

    public void applyDiscount(DiscountType discountType) {
        discountRate = discountType.getRate();
        discountedPrice = totalPayment * discountRate;
        totalPayment = totalPayment - discountedPrice;
    }

    public void finishOrder() {
        printer.printSeparateLine();

        printer.print("<<주문이 완료되었습니다.>>");
        printer.print(String.format("%-10s \t: %10.2f", "[합계]", totalPayment));
        printer.print(String.format("%-10s \t: %10.2f", "[할인금액]", discountedPrice));
        printer.print(String.format("%-10s \t: %9.2f%%", "[할인율]", discountRate * 100));

        printer.printSeparateLine();
    }

}
