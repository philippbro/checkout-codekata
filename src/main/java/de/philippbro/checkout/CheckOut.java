package de.philippbro.checkout;

import java.util.HashMap;
import java.util.Set;

/**
 * Class representing a checkout for a specified price model.
 *
 * @author      Philipp Brock <philipp.bro@gmail.com>
 */
public class CheckOut {

    /** Items currently in stock with key: Name and value: quantity. */
    private HashMap<String, Integer> items;

    /** Rules for pricing of each SKU key: Name and value: pricing. */
    private HashMap<String, PricingDetail> pricingRules;

    /**
     * Creates an instance of CheckOut with given pricing rules.
     *
     * @param rules the rules for pricing for all known SKUs
     */
    public CheckOut(HashMap<String, PricingDetail> rules) {
        this.items = new HashMap<String, Integer>();
        this.pricingRules = rules;
    }

    /**
     * Scans one article specified by key and adds to stock.
     *
     * @param articleKey Key of article to scan
     */
    public void scan(String articleKey) {
        int currentValue = this.items.getOrDefault(articleKey, 0);
        this.items.put(articleKey, currentValue + 1);
    }

    /**
     * Returns the total price of the current stock based on the pricing rules.
     *
     * @return The total price of the current stock.
     */
    public int total() {
        Set<String> keys = this.items.keySet();
        int total = 0;

        for (String articleKey: keys) {
            int quantity = this.items.get(articleKey);
            PricingDetail pricingDetail = this.pricingRules.get(articleKey);
            if (pricingDetail != null) {
                total += pricingDetail.getPriceForQuantity(quantity);
            } else {
                // Do error-handling for not existent pricing rules.
            }
        }
        return total;
    }
}
