package src.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import src.controller.Listener;

public class Top {
    private JFrame frame;

    private JMenuBar menuBar;
    private ArrayList<JMenu> menus;

    public Top() {
        this.setLookAndFeel();
        this.initalizeFrame();
        this.initializeMenu();
        this.frame.setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to set the system look and feel: " + e);
        }
    }

    private void initalizeFrame() {
        this.frame = new JFrame("Receipt system");
        this.frame.setSize(960, 540);
        this.frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeMenu() {
        menus = new ArrayList<>();
        Collections.addAll(
                menus,
                WidgetFactory.jMenu(Text.ITEMS,
                        Text.LIST_ITEMS, Text.ADD_ITEM, Text.EDIT_ITEM, Text.REMOVE_ITEM),
                WidgetFactory.jMenu(Text.HELP,
                        Text.TUTORIAL, Text.ABOUT));
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

    // Dialogs

    public void dialogSuccess(String title, String message) {
        System.out.println(Text.GREEN + "* Action succeeded: " + message + Text.RESET);
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void dialogFailure(String title, String message) {
        System.out.println(Text.RED + "* Action failed: " + message + Text.RESET);
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Present a dialog listing all item names.
     * 
     * @param itemNames
     */
    public void dialogListItems(String[][] data) {
        JDialog dialog = WidgetFactory.jDialog(frame, "Items");
        JScrollPane table = WidgetFactory.jTableInScrollPane(data, new String[] { "Name", "Price" });
        dialog.add(table);
        dialog.setVisible(true);
    }

    /**
     * Present the "add item" dialog.
     */
    public HashMap<String, String> dialogAddItem() {
        HashMap<String, String> data = new HashMap<>();
        JDialog dialog = WidgetFactory.jDialog(frame, "Add item");
        dialog.setLayout(new GridBagLayout());

        JTextField name = WidgetFactory.jTextField("AddItem.Name");
        JTextField price = WidgetFactory.jTextField("AddItem.Price");
        JButton addItem = WidgetFactory.jButton("Add", "AddItem.Add");
        JButton cancel = WidgetFactory.jButton("Cancel", "AddItem.Cancel");

        JComponent[][] components = new JComponent[][] {
                {
                        WidgetFactory.jLabel("Add item", WidgetFactory.BOLD),
                },
                {
                        WidgetFactory.jLabel("Name"),
                        name,
                },
                {
                        WidgetFactory.jLabel("Price"),
                        price,
                },
                {
                        addItem,
                        cancel
                }
        };

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(16, 16, 16, 16);
        for (int i = 0; i < components.length; i++) {
            gridBagConstraints.gridy++;
            for (int j = 0; j < components[i].length; j++) {
                gridBagConstraints.gridx = j;
                dialog.add(components[i][j], gridBagConstraints);
            }
        }

        DialogListener listener = new DialogListener(dialog);
        addItem.addActionListener(listener);
        cancel.addActionListener(listener);

        dialog.setVisible(true);

        if (listener.getStatus() == DialogStatus.PROCEED) {
            data.put("name", name.getText());
            data.put("price", price.getText());
        }
        return data;
    }
}
