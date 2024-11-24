package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;

import src.model.Shop;
import src.view.Text;
import src.view.Top;

public class Listener implements ActionListener {
    private Top top;
    private Shop shop;

    public Listener(Top top, Shop shop) {
        this.top = top;
        this.shop = shop;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // String buttonName = ((JComponent) e.getSource()).getName();
        String buttonText = e.getActionCommand();
        // System.out.println(buttonName + ' ' + buttonText);
        System.out.println(Text.BLUE + "\n## Executing action: " + buttonText + Text.RESET);
        switch (buttonText) {
            case Text.LIST_ITEMS:
                listItems();
                break;
            case Text.ADD_ITEM:
                addItem();
                break;
            default:
                System.out.println(Text.RED + "* Action has no function." + Text.RESET);
                return;
        }
        System.out.println(Text.GREEN + "* Action finished." + Text.RESET);
    }

    public void listItems() {
        top.dialogListItems(shop.getItemNames());
    }

    public void addItem() {
        // top.dialogAddItem();
        try {
            shop.addItem("banana", new BigDecimal(120));
        } catch (SQLException e) {
            System.out.print(
                    Text.RED
                            + "\n* SQLException\n```"
                            + e.getMessage()
                            + "\n```\n"
                            + Text.RESET);
        }
    }

}
