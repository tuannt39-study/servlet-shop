package vn.hkd.shop.dao;

import vn.hkd.shop.domain.Product;
import java.util.List;

public interface ProductDAO {

    List<Product> findAll(int max);

    List<Product> findByCategory(int id);

    List<Product> search(String q);

    Product findOne(int id);

}
