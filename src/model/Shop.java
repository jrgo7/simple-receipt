package src.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;

import src.view.Text;

/**
 * A Shop manages the creation of Items and Receipts.
 */
public class Shop {
    private ArrayList<Item> items;
    private Connection connection;

    public Shop() {
        items = new ArrayList<>();
    }

    /**
     * Attempt to connect to the database
     * 
     * @throws SQLException
     * @throws SQLTimeoutException
     */
    public void connectToDatabase() throws SQLException, SQLTimeoutException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/model/shop.db");
    }

    /**
     * Load Items data from the SQL database into the `items` ArrayList.
     */
    public void loadItems() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet itemEntries = statement.executeQuery("SELECT * FROM `items`");
        while (itemEntries.next()) {
            String name = itemEntries.getString("name");
            BigDecimal price = new BigDecimal(itemEntries.getString("price"));
            items.add(new Item(name, price));
        }
    }

    /**
     * Insert an Item into the database.
     */
    public void addItem(String name, BigDecimal price) throws SQLException {
        String query = "INSERT INTO `items` (`name`, `price`) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, price.toString());
        System.out.println(Text.BLUE + statement + Text.RESET);
        statement.executeUpdate();

        Item item = new Item(name, price);
        items.add(item);
    }

    /**
     * Get the names of each item in the database.
     * 
     * @return
     */
    public ArrayList<String> getItemNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Item item : items) {
            names.add(item.name());
        }
        return names;
    }

}
