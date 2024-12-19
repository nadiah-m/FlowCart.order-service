package net.javaguides.orderservice.modelTest;

import net.javaguides.orderservice.dto.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize Order object before each test
        order = new Order("12345", "Laptop", 2, 500.0);
    }

    @Test
    void testConstructor() {
        // Test the constructor to ensure it sets the values correctly
        Order newOrder = new Order("54321", "Phone", 1, 300.0);

        assertEquals("54321", newOrder.getOrderId());
        assertEquals("Phone", newOrder.getName());
        assertEquals(1, newOrder.getQty());
        assertEquals(300.0, newOrder.getPrice());
    }

    @Test
    void testGettersAndSetters() {
        // Test getters and setters
        order.setOrderId("67890");
        order.setName("Tablet");
        order.setQty(3);
        order.setPrice(350.0);

        assertEquals("67890", order.getOrderId());
        assertEquals("Tablet", order.getName());
        assertEquals(3, order.getQty());
        assertEquals(350.0, order.getPrice());
    }

    @Test
    void testDefaultConstructor() {
        // Test the default constructor generated by Lombok
        Order defaultOrder = new Order();

        assertNull(defaultOrder.getOrderId());
        assertNull(defaultOrder.getName());
        assertEquals(0, defaultOrder.getQty());
        assertEquals(0.0, defaultOrder.getPrice(), 0.01); // Allow for small floating point error
    }

    @Test
    void testToString() {
        // Test the `toString()` method generated by Lombok
        String expectedToString = "Order(orderId=12345, name=Laptop, qty=2, price=500.0)";
        assertEquals(expectedToString, order.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        // Test the `equals()` and `hashCode()` methods
        Order anotherOrder = new Order("12345", "Laptop", 2, 500.0);
        assertTrue(order.equals(anotherOrder));
        assertEquals(order.hashCode(), anotherOrder.hashCode());

        Order differentOrder = new Order("54321", "Phone", 1, 300.0);
        assertFalse(order.equals(differentOrder));
        assertNotEquals(order.hashCode(), differentOrder.hashCode());
    }

    @Test
    void testEdgeCaseZeroQtyAndPrice() {
        // Test edge case where quantity and price are zero
        order.setQty(0);
        order.setPrice(0.0);

        assertEquals(0, order.getQty());
        assertEquals(0.0, order.getPrice());
    }

    @Test
    void testNullOrderIdAndName() {
        // Test if setting null values for orderId and name is allowed
        order.setOrderId(null);
        order.setName(null);

        assertNull(order.getOrderId());
        assertNull(order.getName());
    }

    @Test
    void testNegativeQtyAndPrice() {
        // Test if negative qty and price are handled (business logic may vary)
        order.setQty(-5);
        order.setPrice(-100.0);

        assertEquals(-5, order.getQty());
        assertEquals(-100.0, order.getPrice());
    }
}
