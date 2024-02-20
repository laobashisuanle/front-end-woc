package com.example.mybetis_plus_demo.mapper;

import com.example.mybetis_plus_demo.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laoba
 * @since 2024-02-05
 */@Mapper
public interface UserMapper {

    List<User> getAll();


    User getById(Integer id);

    boolean save(User user);

    boolean update(User user);

    boolean delete(Integer id);


    List<User>  getList();

    User getByNo(String number);


    List<User> getByPageObscure(Integer skipNum, Integer pageSize, String number,String department,Integer roleId);

    User getByName(String name);
    @Select("     select count(id) from user where  department like concat('%',#{department},'%') and number like concat('%',#{number},'%')")
    Integer getCountByPage1(String number,String department);

    @Select("     select count(id) from user where role_id =#{roleId} and department like concat('%',#{department},'%') and number like concat('%',#{number},'%')")
    Integer getCountByPage2(String number,String department,Integer roleId);



}
