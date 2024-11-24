package src.model;

import java.util.HashMap;

/**
 * A Receipt contains the customer's name a list of Items
 */
public class Receipt {
    private String customerName;
    private HashMap<Item, Integer> items;

    /**
     * Initialize a Receipt with the customer's name.
     * 
     * @param customerName
     */
    public Receipt(String customerName) {
        this.customerName = customerName;
        this.items = new HashMap<>();
    }

    /**
     * Add an Item to this Receipt.
     * 
     * @param item
     */
    public void addItem(Item item) {
        items.put(item, items.get(item) + 1);
    }

    /**
     * Remove an Item from this Receipt.
     * 
     * @param item
     */
    public void removeItems(Item item) {
        items.put(item, items.get(item) - 1);
    }

    /**
     * Get the customer name associated with this Receipt.
     * 
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set the customer name associated with this Receipt.
     * 
     * @param customerName
     * @return
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}