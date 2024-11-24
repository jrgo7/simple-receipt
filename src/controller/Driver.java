package src.controller;

import java.sql.SQLException;

import src.model.Shop;
import src.view.Text;
import src.view.Top;

public class Driver {
    public static void main(String[] args) {
        System.out.println(Text.YELLOW + "# Simple Receipt System" + Text.RESET);
        System.out.println(Text.YELLOW + "* by Justin Go, 2024" + Text.RESET);
        Shop shop = new Shop();
        Top top = new Top();

        Listener listener = new Listener(top, shop);
        top.addListener(listener);

        try {
            shop.connectToDatabase();
        } catch (SQLException e) {
            System.out.print(Text.RED);
            System.out.println(e);
            System.out.print(Text.RESET);
        }

        try {
            shop.loadItems();
        } catch (SQLException e) {
            System.out.println(Text.YELLOW + "`items` table does not exist." + Text.RESET);
        }

    }
}
