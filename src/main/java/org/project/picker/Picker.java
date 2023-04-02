package org.project.picker;
import org.project.orders.Order;
import org.project.orders.OrderDto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Picker {
    private List<Order> ordersList;
    private final String id;
    private LocalTime availableFrom;

    public Picker(String id) {
        this.ordersList = new ArrayList<>();
        this.id = id;
        this.availableFrom = LocalTime.MAX;
    }
    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public String getId() {
        return id;
    }

    public LocalTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public void addOrderToList(Order order){
        ordersList.add(order);
    }

    @Override
    public String toString() {
        return "Picker{" +
                "ordersList=" + ordersList +
                ", id='" + id + '\'' +
                '}';
    }
}
