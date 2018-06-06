package com.example.mint.applicationtest.DataClass;

/**
 * Created by Far on 7/10/2560.
 */

public class DataClass {
    private String nameRice;
    private String priceRice;
    private String amount;
    private String total;
    public String getNameRice() {
        return nameRice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setNameRice(String nameRice) {
        this.nameRice = nameRice;
    }

    public String getPriceRice() {
        return priceRice;
    }

    public void setPriceRice(String priceRice) {
        this.priceRice = priceRice;
    }

    public DataClass(String nameRice, String priceRice ) {
        this.nameRice = nameRice;
        this.priceRice = priceRice;

    }
}
