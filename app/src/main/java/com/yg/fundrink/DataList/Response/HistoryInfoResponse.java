package com.yg.fundrink.DataList.Response;

import java.util.List;

public class HistoryInfoResponse {
    private int total;
    private int perDay;
    private int reachCount;
    private int drinkCount;
    private List<HistoryItem> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerDay() {
        return perDay;
    }

    public void setPerDay(int perDay) {
        this.perDay = perDay;
    }

    public int getReachCount() {
        return reachCount;
    }

    public void setReachCount(int reachCount) {
        this.reachCount = reachCount;
    }

    public int getDrinkCount() {
        return drinkCount;
    }

    public void setDrinkCount(int drinkCount) {
        this.drinkCount = drinkCount;
    }

    public List<HistoryItem> getList() {
        return list;
    }

    public void setList(List<HistoryItem> list) {
        this.list = list;
    }

    public class HistoryItem {
        String date;
        int quantity;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "HistoryItem{" +
                    "date='" + date + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }

}
