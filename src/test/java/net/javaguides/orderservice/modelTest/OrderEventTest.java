package net.javaguides.orderservice.modelTest;

import net.javaguides.orderservice.dto.Order;
import net.javaguides.orderservice.dto.OrderEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderEventTest {
    private OrderEvent orderEvent;
    private Order order;

    @BeforeEach
    void setUp() {
        // Initialize before each test
        order = new Order("12345","test",1, 100.50);  // Example order
        orderEvent = new OrderEvent();
    }

    @Test
    void testSetAndGetStatus() {
        // Test for setting and getting the status
        orderEvent.setStatus("pending");
        assertEquals("pending", orderEvent.getStatus());

        orderEvent.setStatus("progress");
        assertEquals("progress", orderEvent.getStatus());

        orderEvent.setStatus("completed");
        assertEquals("completed", orderEvent.getStatus());
    }

    @Test
    void testSetAndGetMessage() {
        // Test for setting and getting the message
        orderEvent.setMessage("Order is pending.");
        assertEquals("Order is pending.", orderEvent.getMessage());

        orderEvent.setMessage("Order is being processed.");
        assertEquals("Order is being processed.", orderEvent.getMessage());

        orderEvent.setMessage("Order is completed.");
        assertEquals("Order is completed.", orderEvent.getMessage());
    }

    @Test
    void testSetAndGetOrder() {
        // Test for setting and getting the order object
        orderEvent.setOrder(order);
        assertEquals(order, orderEvent.getOrder());

        // Modify order and check if the change reflects
        Order newOrder = new Order("67890", "changeTest", 2, 150.75);
        orderEvent.setOrder(newOrder);
        assertEquals(newOrder, orderEvent.getOrder());
    }

    @Test
    void testStatusShouldNotBeNull() {
        // Test that the status cannot be null
        orderEvent.setStatus("completed");
        assertNotNull(orderEvent.getStatus(), "Status should not be null");
    }

    @Test
    void testMessageShouldNotBeNull() {
        // Test that message should not be null
        orderEvent.setMessage("Processing order");
        assertNotNull(orderEvent.getMessage(), "Message should not be null");
    }

    @Test
    void testOrderShouldNotBeNull() {
        // Test that the order cannot be null
        orderEvent.setOrder(order);
        assertNotNull(orderEvent.getOrder(), "Order should not be null");
    }

    @Test
    void testNullOrder() {
        // Test setting the order to null
        orderEvent.setOrder(null);
        assertNull(orderEvent.getOrder(), "Order should be null");
    }
}
