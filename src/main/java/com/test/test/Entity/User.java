package com.test.test.Entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Document(indexName = "test",type = "user")
@Table(name = "user")
@ApiModel(value = "用户")
public class User {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden=true)
    private Integer id;
    @ApiModelProperty("用户编号（自填）")
    private Integer number;
    @ApiModelProperty("用户姓名（自填）")
    private String name;
    @ApiModelProperty("用户性别（自填【男，女，其他】）")
    private String sex;
    @ApiModelProperty("用户年龄（自填）")
    private Integer age;
    @ApiModelProperty("用户电话号码（自填）")
    private Integer phone;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                '}';
    }
}
