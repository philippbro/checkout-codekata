package de.philippbro.checkout;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author      Philipp Brock <philipp.bro@gmail.com>
 */
public class TestDiscount {

    /** initalizes a discount of 50 for the quantity of 2 units. */
    private static final Discount discount = new Discount(2, 50);

    @Test
    public void discounts() {
        assertEquals(0, discount.calculateDiscountForQuantity(0));
        assertEquals(0, discount.calculateDiscountForQuantity(1));
        assertEquals(50, discount.calculateDiscountForQuantity(2));
        assertEquals(50, discount.calculateDiscountForQuantity(3));
        assertEquals(100, discount.calculateDiscountForQuantity(4));
    }
}
