package com.test.test.Controller;

import com.test.test.Entity.User;
import com.test.test.Es.Es_wjf;
import com.test.test.Service.impl.UserServiceImpl;
import com.test.test.Service.impl.UserService_esImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("/User")
@CrossOrigin
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    Es_wjf es_wjf;

    @Autowired
    UserService_esImpl userService_es;

    @GetMapping("")
    public String test(){
        return "test";
    }
    @GetMapping("/selectUsers")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "page",value = "要查询的页码",dataType = "Integer", paramType = "query"),
                    @ApiImplicitParam(name = "size",value = "要查询的数量",dataType = "Integer", paramType = "query")
            }
    )
    public Page<User> selectUsers(Integer page,Integer size){
        System.out.println(page+"       "+size);
        Page<User> users = userService.selectUsers(page, size);
        return users;
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name="user",value = "实体")
            }
    )
    @GetMapping("/selectUser_es")
    public GetResponse selectUser_es(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        GetResponse es = userService_es.select_user(user);
        return es;
    }

    @PostMapping("/insertUser")
    public User insertUser(User user) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User user1 = userService.insertUser(user);
        Map<String, Object> sm = userService_es.add_user(user1);
        return user1;
    }

    @PutMapping("/updataUser")
    public User updataUser(User user){
        User user1 = userService.updataUser(user);
        return user1;
    }

    @ApiImplicitParam(name = "id",value = "要删除的用户id",dataType = "Integer", paramType = "query")
    @DeleteMapping("/deleteUser")
    public User deleteUser(Integer id){
        User user = userService.delectUser(id);
        return user;
    }
}
