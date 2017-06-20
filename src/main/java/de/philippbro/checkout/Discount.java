package de.philippbro.checkout;

/**
 * Class representing a discount for a special unit.
 *
 * @author      Philipp Brock <philipp.bro@gmail.com>
 */
public class Discount {

    /** Quantity relevant for the discount. */
    private int quantity;

    /** Discount given when quantity is in stock. */
    private int value;

    /**
     * Creates new instance of Discount.
     *
     * @param quantity Quantity relevant for the discount.
     * @param value Discount given when quantity is in stock.
     */
    public Discount(int quantity, int value) {
        this.quantity = quantity;
        this.value = value;
    }

    /**
     * Calculates the discount for a given quantity.
     *
     * @param quantity number of articles the discount should be calcualted for.
     * @return Calculated discount.
     */
    public int calculateDiscountForQuantity(int quantity) {
        return (int) Math.floor(quantity / this.quantity) * this.value;
    }
}
