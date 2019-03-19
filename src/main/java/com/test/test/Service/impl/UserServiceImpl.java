package com.test.test.Service.impl;

import com.test.test.Dao.UserRepository;
import com.test.test.Entity.User;
import com.test.test.Es.Es_wjf;
import com.test.test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Es_wjf es_wjf;
    @Override
    public Page<User> selectUsers(Integer page, Integer size) {
        page-=1;
        Sort orders = new Sort(Sort.Direction.ASC,"number");
        PageRequest pageable = PageRequest.of(page, size,orders);
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        Page<User> all = userRepository.findAll(specification,pageable);
        return all;
    }

    @Override
    public User delectUser(Integer id) {
        User user = new User();
        user.setId(id);
        Optional<User> byId = userRepository.findById(id);
        userRepository.delete(user);
        return byId.get();
    }

    @Override
    public User updataUser(User user) {
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public User insertUser(User user) {
        User save = userRepository.save(user);
        return save;
    }
}
