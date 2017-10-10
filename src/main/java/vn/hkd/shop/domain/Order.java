package vn.hkd.shop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String phone;
    
    private String address;
    
    private String note;

    private Date created;

    private User user;

    private List<Item> items;

    public Order() {

    }

    public Order(String name, String phone, String address, String note, Date created) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.created = created;
    }

    public Order(String name, String phone, String address, String note, Date created, User user, List<Item> items) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.created = created;
        this.user = user;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
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
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", note=" + note + ", created=" + created + '}';
    }
    
}
