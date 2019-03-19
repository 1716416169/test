package com.test.test.Service;

import com.test.test.Entity.User;
import org.elasticsearch.action.get.GetResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface UserService_es {
    public Map<String, Object> add_user(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    public GetResponse select_user(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
