package de.philippbro.checkout;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author      Philipp Brock <philipp.bro@gmail.com>
 */
public class TestPricingDetail {

    /** creates PricingDetail with a price per unit of 150 and a Discount of 50 for each 2 units. */
    private static final PricingDetail priceWithDiscount = new PricingDetail(150, new Discount(2, 50));
    /** creates PricingDetail with a price per unit of 150 without any Discount. */
    private static final PricingDetail priceWithoutDiscount = new PricingDetail(150, null);

    @Test
    public void priceWithDiscount() {
        assertEquals(0, priceWithDiscount.getPriceForQuantity(0));
        assertEquals(150, priceWithDiscount.getPriceForQuantity(1));
        // the discount should be used once.
        assertEquals(250, priceWithDiscount.getPriceForQuantity(2));
        assertEquals(400, priceWithDiscount.getPriceForQuantity(3));
        // the discount should be used twice.
        assertEquals(500, priceWithDiscount.getPriceForQuantity(4));
    }

    @Test
    public void priceWithoutDiscount() {
        assertEquals(0, priceWithoutDiscount.getPriceForQuantity(0));
        assertEquals(150, priceWithoutDiscount.getPriceForQuantity(1));
        assertEquals(300, priceWithoutDiscount.getPriceForQuantity(2));
        assertEquals(450, priceWithoutDiscount.getPriceForQuantity(3));
    }
}
