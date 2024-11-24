package src.view;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WidgetFactory {
    /**
     * This class is not meant to be instantiated.
     */
    private WidgetFactory() {

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
}
