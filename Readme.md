Checkout - Code Kata
==============================================================================

Implementation of a modified Kata by Dave Thomas in Java: 
[Kata09: Back to the Checkout](http://codekata.com/kata/kata09-back-to-the-checkout/)

Code Kata about a checkout process, which includes a 
pricing model with discounts for units 
_e.g. you will get one A for 30 cents, three A for 100 cents._

## Quick Start

To run the tests given for this kata just run:
```shell
mvn clean test
```

## Basic Example
```java
/**
 * Initializes the Pricing Rules for the 
 * given model from the task.
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
```

## Additional Information

When you want to modify the pricing rules just create a `HashMap<String, PricingDetail> pricing_rules`
like in the above example and pass these rules to the new created instance of CheckOut.

The Rules are build with the unit name as key and a PricingDetail-Instance as value. 
A PricingDetail contains a unit price in cents and optional Discount-Instance or null.
The Discount contains a quantity and the discount.

```java
public PricingDetail(int unitPrice, Discount discount);

public Discount(int quantity, int value); 
```

Than you can run code to scan several items like this:
```java
CheckOut co = new CheckOut(pricing_rules);
co.scan(item);
co.scan(item);
[...]
price = co.total();
```
