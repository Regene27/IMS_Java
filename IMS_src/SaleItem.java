package IMS_src;

public class SaleItem extends Product {
    private int quantity;

    public SaleItem(String name, double price, int quantity) {
        super(name, price);
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return quantity * getPrice();
    }

    public int getAmount() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + quantity +
                ", total price=" + getTotalPrice() + "}";
    }
}
