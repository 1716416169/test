package com.test.test.Service;

import com.test.test.Entity.User;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserService {
    public Page<User> selectUsers(Integer page,Integer size);
    public User delectUser(Integer id);
    public User updataUser(User user);
    public User insertUser(User user);
}
