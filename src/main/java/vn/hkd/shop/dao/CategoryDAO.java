package vn.hkd.shop.dao;

import vn.hkd.shop.domain.Category;
import java.util.List;

public interface CategoryDAO {

    List<Category> findAll();

}
