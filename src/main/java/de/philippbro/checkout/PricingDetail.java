package de.philippbro.checkout;

/**
 * Class representing the Details of price calculation for one item.
 *
 * @author      Philipp Brock <philipp.bro@gmail.com>
 */
public class PricingDetail {

    /** Price per unit in cents. */
    private int unitPrice;

    /** Discount for this item. */
    private Discount discount;

    /**
     * Creates an instance of PricingDetail with given price and discount.
     *
     * @param unitPrice Price per unit in cents.
     * @param discount Discount for this item.
     */
    public PricingDetail(int unitPrice, Discount discount) {
        this.discount = discount;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price of one article for given quantity.
     *
     * @param quantity number of articles for one item.
     * @return price in total.
     */
    public int getPriceForQuantity(int quantity) {
        int totalPrice = quantity * this.unitPrice;
        if (this.discount != null) {
            totalPrice -= this.discount.calculateDiscountForQuantity(quantity);
        }
        return totalPrice;
    }
}
