package src.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class WidgetFactory {
    public static Font PLAIN = new Font("Segoe UI", Font.PLAIN, 14);
    public static Font BOLD = new Font("Segoe UI", Font.BOLD, 14);
    /**
     * This class is not meant to be instantiated.
     */
    private WidgetFactory() {

    }

    public static void commonStyle(JComponent component) {
        component.setFont(PLAIN);
    }

    /**
     * Create a Menu with a title and specified list of options.
     * 
     * @param title
     * @param options
     * @return
     */
    public static JMenu jMenu(String title, String... options) {
        JMenu menu = new JMenu(title);
        for (String option : options) {
            menu.add(new JMenuItem(option));
        }
        return menu;
    }

    /**
     * Create a MenuBar with a specified list of Menus.
     * 
     * @param menus
     * @return
     */
    public static JMenuBar jMenuBar(ArrayList<JMenu> menus) {
        JMenuBar menuBar = new JMenuBar();
        for (JMenu menu : menus) {
            menuBar.add(menu);
        }
        return menuBar;
    }

    /**
     * Create a new JDialog.
     * 
     * @param frame
     * @param title
     * @return
     */
    public static JDialog jDialog(JFrame frame, String title) {
        JDialog dialog = new JDialog(frame, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationByPlatform(true);
        dialog.setSize(640, 360);
        return dialog;
    }

    /**
     * Create a new JTable.
     * 
     * @param data
     * @param columns
     * @return
     */
    public static JTable jTable(String[][] data, String[] columns) {
        JTable table = new JTable(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        commonStyle(table);
        table.getTableHeader().setFont(BOLD);
        return table;
    }

    public static JScrollPane jTableInScrollPane(String[][] data, String[] columns) {
        JTable table = jTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        commonStyle(scrollPane);
        return scrollPane;
    }

    public static JButton jButton(String text, String name) {
        JButton button = new JButton(text);
        button.setName(name);
        commonStyle(button);
        return button;
    }

    public static JLabel jLabel(String text) {
        JLabel label = new JLabel(text);
        commonStyle(label);
        return label;
    }

    public static JLabel jLabel(String text, Font font) {
        JLabel label = jLabel(text);
        label.setFont(font);
        return label;
    }

    public static JTextField jTextField(String name) {
        JTextField textField = new JTextField(16);
        textField.setName(name);
        commonStyle(textField);
        return textField;
    }
}
