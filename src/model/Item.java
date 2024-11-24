package src.model;

import java.math.BigDecimal;

public record Item(String name, BigDecimal price) {
    /**
     * Return the total price of an item given an arbitrary amount.
     * 
     * @param amount the amount of item to compute the total price for
     * @return
     */
    public BigDecimal totalPrice(int amount) {
        return price.multiply(new BigDecimal(amount));
    }
}
