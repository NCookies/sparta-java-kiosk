package xyz.ncookie;

import xyz.ncookie.kiosk.Kiosk;
import xyz.ncookie.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();
        kiosk.start();

        System.out.println("프로그램을 종료합니다.");
    }
}
