package com.test.test.Service.impl;

import com.test.test.Entity.User;
import com.test.test.Es.Es_wjf;
import com.test.test.Service.UserService_es;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Service
public class UserService_esImpl implements UserService_es {
    @Autowired
    Es_wjf es_wjf;
    @Override
    public Map<String, Object> add_user(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> map = es_wjf.add_es(user, "test", "user");
        System.out.println("添加得数据："+map);
        return map;
    }

    @Override
    public GetResponse select_user(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        GetResponse es = es_wjf.get_es(user, "test", "user");
        return es;
    }
}
