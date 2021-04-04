package com.yg.fundrink.DataList.Response;

import java.util.List;

public class HomeInfoResponse {
    private int current;
    private int target;
    private List<DrinkItem> list;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public List<DrinkItem> getList() {
        return list;
    }

    public void setList(List<DrinkItem> list) {
        this.list = list;
    }

    public static class DrinkItem {
        long time;
        String drinkType;
        int quantity;

        public DrinkItem(long time, String drinkType, int quantity) {
            this.time = time;
            this.drinkType = drinkType;
            this.quantity = quantity;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getDrinkType() {
            return drinkType;
        }

        public void setDrinkType(String drinkType) {
            this.drinkType = drinkType;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
