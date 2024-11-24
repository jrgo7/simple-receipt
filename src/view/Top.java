package src.view;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import src.controller.Listener;

public class Top {
    private JFrame frame;

    private JMenuBar menuBar;
    private ArrayList<JMenu> menus;

    public Top() {
        this.initalizeFrame();
        this.initializeMenu();
        this.frame.setVisible(true);
    }

    public void initalizeFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to set the system look and feel: " + e);
        }
        this.frame = new JFrame("Receipt system");
        this.frame.setSize(400, 400);
        this.frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initializeMenu() {
        menus = new ArrayList<>();
        Collections.addAll(
                menus,
                WidgetFactory.jMenu(
                        Text.ITEMS, Text.LIST_ITEMS, Text.ADD_ITEM,
                        Text.EDIT_ITEM, Text.REMOVE_ITEM),
                WidgetFactory.jMenu(Text.HELP, Text.TUTORIAL, Text.ABOUT));
        menuBar = WidgetFactory.jMenuBar(menus);
        frame.setJMenuBar(menuBar);
    }

    public void addListener(Listener listener) {
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            for (int j = 0; j < menu.getMenuComponentCount(); j++) {
                ((JMenuItem) menu.getMenuComponent(j)).addActionListener(listener);
            }
        }
    }

    /**
     * Present a dialog listing all item names.
     * 
     * @param itemNames
     */
    public void dialogListItems(ArrayList<String> itemNames) {
        StringBuffer display = new StringBuffer();
        for (String itemName : itemNames) {
            display.append(itemName + '\n');
        }
        JOptionPane.showMessageDialog(frame, display);
    }

    /**
     * Present the "add item" dialog.
     */
    public void dialogAddItem() {

    }
}
