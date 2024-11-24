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
        try {
            String query = "SELECT * FROM `items`";
            Statement statement = connection.createStatement();
            ResultSet itemEntries = statement.executeQuery(query);
            while (itemEntries.next()) {
                String name = itemEntries.getString("name");
                BigDecimal price = new BigDecimal(itemEntries.getString("price"));
                items.add(new Item(name, price));
            }
            statement.close();
            itemEntries.close();
        } catch (SQLException e) {
            System.out.println(Text.YELLOW + "* Items table doesn't exist yet; creating..." + Text.RESET);
            String query = """
                    CREATE TABLE IF NOT EXISTS
                        `items` (name TEXT PRIMARY KEY, price DECIMAL(9, 2))
                    """;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
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
        System.out.println(Text.YELLOW + "> " + statement + Text.RESET);
        statement.executeUpdate();
        items.add(new Item(name, price));
        statement.close();
    }


    /**
     * Get item data as a String matrix.
     */
    public String[][] getItemData() {
        String[][] data = new String[items.size()][2];

        int i = 0;
        for (Item item : items) {
            data[i][0] = item.name();
            data[i][1] = item.price().toString();
            i++;
        }

        return data;
    }


}
