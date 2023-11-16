package InventoryManagementSystem.IMS_src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Report {
    private String date;
    private ArrayList<String> receipt;
    private double totalPrice;

    public Report(String date) {
        this.date = date;
        receipt = new ArrayList<>();
        totalPrice = 0.0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getReceipt() {
        return receipt;
    }

    public void setReceipt(ArrayList<String> receipt) {
        this.receipt = receipt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<String> getReceiptsInPeriod(LocalDate startDate, LocalDate endDate) {
        ArrayList<String> receiptsInPeriod = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (String receipt : receipt) {
            String receiptDate = receipt.split(":")[0];
            LocalDate transactionDate = LocalDate.parse(receiptDate, formatter);

            if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {
                receiptsInPeriod.add(receipt);
            }
        }

        return receiptsInPeriod;
    }
}
