package vn.hkd.shop.dao;

import vn.hkd.shop.domain.User;

public interface UserDAO {

    User findOne(String email, String password);

    boolean exists(String email);

    int save(User user);

}
