package de.philippbro.checkout;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * @author      Philipp Brock <philipp.bro@gmail.com>
 */
public class TestPrice {

    /**
     * Initializes the Pricing Rules for the given model from the task.
     *
     *  SKU    Unit Price   Special Price
     * -----------------------------------------------
     *   A       40           3 for 100 (Discount: 20)
     *   B       50           2 for 80  (Discount: 20)
     *   C       25
     *   D       20
     *
     */
    private static final HashMap<String, PricingDetail> RULES = new HashMap<String, PricingDetail>() {
        {
            put("A", new PricingDetail(40, new Discount(3, 20)));
            put("B", new PricingDetail(50, new Discount(2, 20)));
            put("C", new PricingDetail(25, null));
            put("D", new PricingDetail(20, null));
        }
    };

    /**
     * Returns total price for a given article chain.
     *
     * @param goods String representing the articles added to stock like "AAABBACCD"
     * @return total price of given articles
     */
    private int calculatePrice(String goods) {
        CheckOut co = new CheckOut(RULES);
        for(int i = 0; i < goods.length(); i++) {
            co.scan(String.valueOf(goods.charAt(i)));
        }
        return co.total();
    }

    @Test
    public void totals() {
        assertEquals(0, calculatePrice(""));
        assertEquals(40, calculatePrice("A"));
        assertEquals(90, calculatePrice("AB"));
        assertEquals(135, calculatePrice("CDBA"));
        assertEquals(80, calculatePrice("AA"));
        assertEquals(100, calculatePrice("AAA"));
        assertEquals(140, calculatePrice("AAAA"));
        assertEquals(180, calculatePrice("AAAAA"));
        assertEquals(200, calculatePrice("AAAAAA"));
        assertEquals(150, calculatePrice("AAAB"));
        assertEquals(180, calculatePrice("AAABB"));
        assertEquals(200, calculatePrice("AAABBD"));
        assertEquals(200, calculatePrice("DABABA"));
    }

    @Test
    public void incremental() {
        CheckOut co = new CheckOut(RULES);
        assertEquals(0, co.total());
        co.scan("A");
        assertEquals(40, co.total());
        co.scan("B");
        assertEquals(90, co.total());
        co.scan("A");
        assertEquals(130, co.total());
        co.scan("A");
        assertEquals(150, co.total());
        co.scan("B");
        assertEquals(180, co.total());
    }
}
