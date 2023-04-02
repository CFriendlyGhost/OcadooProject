package org.project.algorithm;

import org.project.orders.Order;
import org.project.picker.Picker;
import org.project.store.Store;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PickersScheduler {
    List<Order> mutableOrders;
    Store store;
    List<Order> rejectedOrders;

    public PickersScheduler(List<Order> orders, Store store) {
        mutableOrders = new ArrayList<>(orders);
        rejectedOrders = new ArrayList<>();
        this.store = store;
    }

    public void makeScheduleForPickers() {
        mutableOrders.sort(Comparator.comparing(Order::getStartFrom).reversed());
        while (!mutableOrders.isEmpty()){
            Order latestDeadlineOrder = mutableOrders.remove(0);
            assignOrderToPickerOrReject(latestDeadlineOrder);
        }

        rejectedOrders.sort(Comparator.comparing(Order::getPickingTime));
        for (Order rejectedOrder : rejectedOrders) {
            tryToAssignRejectedOrder(rejectedOrder);
        }

        displayPickersOrders();
    }

    public void assignOrderToPickerOrReject(Order order) {
        for (Picker picker : store.getPickers()) {
            if (!order.getCompleteBy().isAfter(picker.getAvailableFrom())) {
                picker.addOrderToList(order);
                return;
            }
        }
        rejectedOrders.add(order);
    }


    public void displayOrderList(List<Order> orders){
        for (Order order: orders) {
            System.out.println(order);
        }
    }

    public void displayPickersOrders(){
        int numberOfOrders = 0;

        for(Picker picker : store.getPickers()) {
            System.out.println(picker.getId());
            picker.getOrdersList().sort(Comparator.comparing(Order::getStartFrom));
            displayOrderList(picker.getOrdersList());
            numberOfOrders += picker.getOrdersList().size();
        }

        System.out.println(numberOfOrders);
    }
    public void tryToAssignRejectedOrder(Order order){
        Picker potentialPicker = null;
        long shortestGap = Integer.MAX_VALUE;
        LocalTime finalLatestPossibleDeadline = null; // just to suppress warning
        int gapIndex = 1;

        for (Picker picker : store.getPickers()) {
            for (int i = 1; i < picker.getOrdersList().size(); i++) {
                LocalTime gapStart = picker.getOrdersList().get(i).getCompleteBy();
                LocalTime gapEnd = picker.getOrdersList().get(i - 1).getStartFrom();
                long gapDurationInMinutes = gapStart.until(gapEnd, ChronoUnit.MINUTES);

                LocalTime latestPossibleDeadline =
                        order.getCompleteBy().isAfter(gapEnd) ? gapEnd : order.getCompleteBy();
                LocalTime latestPossibleStart = latestPossibleDeadline.minusMinutes(order.getPickingTime().toMinutes());

                if (!latestPossibleStart.isBefore(gapStart) && gapDurationInMinutes < shortestGap) {
                    potentialPicker = picker;
                    finalLatestPossibleDeadline = latestPossibleDeadline;
                    shortestGap = gapDurationInMinutes;
                    gapIndex = i;
                }
            }
        }
        if (potentialPicker != null) {
            order.setCompleteBy(finalLatestPossibleDeadline);
            order.setStartFrom(finalLatestPossibleDeadline.minusMinutes(order.getPickingTime().toMinutes()));

            potentialPicker.getOrdersList().add(gapIndex, order);
            if (order.getStartFrom().isBefore(potentialPicker.getAvailableFrom())) {
                potentialPicker.setAvailableFrom(order.getStartFrom());
            }
        }
    }
}