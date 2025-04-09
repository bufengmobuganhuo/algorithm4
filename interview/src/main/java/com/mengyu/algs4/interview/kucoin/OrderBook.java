package com.mengyu.algs4.interview.kucoin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @date 2025/4/7 11:56
 * TODO
 */
public class OrderBook {

    private final Map<Action, PriorityQueue<Order>> orderBooks;

    public OrderBook() {
        orderBooks = new HashMap<>();
        orderBooks.put(Action.BUY, new PriorityQueue<>());
        orderBooks.put(Action.SELL, new PriorityQueue<>());
    }

    public void onOrderEvent(Order order) {
        if (order == null) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<Order> otherOrderBook = getOtherOrderBook(order);
        if (otherOrderBook == null || otherOrderBook.isEmpty()) {
            return;
        }
        int volume = 0;
        while (!otherOrderBook.isEmpty() && volume < order.quantity) {
            Order otherOrder = otherOrderBook.peek();
            if (isMatched(order, otherOrder)) {
                int filledQty = Math.min(otherOrder.quantity, order.quantity - volume);
                System.out.println(String.format("matched %s with %s", order.id, otherOrder.id));
                volume += filledQty;
                otherOrder.quantity -= filledQty;
                if (otherOrder.quantity <= 0) {
                    otherOrderBook.poll();
                }
            } else {
                break;
            }
        }
        if (volume < order.quantity) {
            order.quantity -= volume;
            PriorityQueue<Order> thisOrderBook = orderBooks.get(order.action);
            thisOrderBook.offer(order);
            System.out.printf("order can't be filled, put into book, orderId=%s", order.id);
        }
    }



    private boolean isMatched(Order thisOrder, Order other) {
        if (thisOrder == null || other == null) {
            throw new IllegalArgumentException();
        }
        if (thisOrder.action == other.action) {
            throw new IllegalArgumentException();
        }
        if (thisOrder.action == Action.SELL) {
            return other.price.compareTo(thisOrder.price) >= 0;
        }
        return thisOrder.price.compareTo(other.price) >= 0;
    }

    private PriorityQueue<Order> getOtherOrderBook(Order order) {
        switch (order.action) {
            case BUY:
                return orderBooks.get(Action.SELL);
            case SELL:
                return orderBooks.get(Action.BUY);
            default:
                throw new UnsupportedOperationException();
        }
    }

    private static class Order implements Comparable<Order> {
        private long id;

        private long createdAt;

        private BigDecimal price;

        private int quantity;

        private Action action;

        @Override
        public int compareTo(Order o) {
            if (price.compareTo(o.price) != 0) {
                return price.compareTo(o.price);
            }
            return Long.compare(createdAt, o.createdAt);
        }
    }

    private static enum Action {
        BUY,
        SELL
    }
}
