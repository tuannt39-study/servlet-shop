package vn.hkd.shop.domain;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private Product product;

    private int quantity;
    
    private int price;

    private Order order;

    public Item() {

    }

    public Item(Product product, int quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getSubTotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.product);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

}
