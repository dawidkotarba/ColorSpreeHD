package com.dk.colorgame.model;

/**
 * Created by Dawid Kotarba on 2015-05-18.
 */
public class Order {

    private int column;
    private int row;

    public Order(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public void add(int column, int row) {
        this.column += column;
        this.row += row;
    }

    public void sub(int column, int row) {
        this.column -= column;
        this.row -= row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "Order{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return column == order.column && row == order.row;

    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }
}
