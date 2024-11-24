package src.controller;

import java.sql.SQLException;

import src.model.Shop;
import src.view.Text;
import src.view.Top;

public class Driver {
    public static void main(String[] args) {
        System.out.println(Text.BLUE + "# Simple Receipt System by Justin Go, 2024" + Text.RESET);
        Shop shop = new Shop();
        Top top = new Top();

        Listener listener = new Listener(top, shop);
        top.addListener(listener);

        try {
            shop.connectToDatabase();
            shop.loadItems();
        } catch (SQLException e) {
            System.out.print(Text.RED);
            System.out.println(e);
            System.out.print(Text.RESET);
        }

    }
}
