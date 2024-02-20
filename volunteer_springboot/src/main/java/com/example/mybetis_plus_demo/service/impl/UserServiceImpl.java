package com.example.mybetis_plus_demo.service.impl;

import com.example.mybetis_plus_demo.common.Paging;
import com.example.mybetis_plus_demo.common.Result;
import com.example.mybetis_plus_demo.pojo.User;
import com.example.mybetis_plus_demo.mapper.UserMapper;
import com.example.mybetis_plus_demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laoba
 * @since 2024-02-05
 */
@Service
public class UserServiceImpl  implements UserService {
  @Resource
    private UserMapper userMapper;


    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

  @Override
  public User getById(Integer id) {
    return userMapper.getById(id);
  }

  @Override
  public boolean save(User user) {
    return userMapper.save(user);
  }

  @Override
  public boolean update(User user) {
    return userMapper.update(user);
  }

  @Override
  public boolean delete(Integer id) {
    return userMapper.delete(id);
  }

  @Override
  public List<User> getList() {
    return userMapper.getList();
  }

  @Override
  public User getByNo(String number) {
    return userMapper.getByNo(number);
  }

    @Override
    public Paging getByPageObscure(Integer pageNum, Integer pageSize, String number, String department,Integer roleId) {

        Integer skipNum = (pageNum - 1)*pageSize;
        List<User> users =userMapper.getByPageObscure(skipNum,pageSize,number,department, roleId);
//        System.out.println(users.get(0).getStudentId());

        Integer total;
        if(roleId!=null){
            total=userMapper.getCountByPage2(number, department,roleId);
        }else{
            total=userMapper.getCountByPage1(number,department);
        }
        Integer totalPage = (int) Math.ceil(1.0*total/pageSize);
        Paging paging =new Paging();
        paging.setTotalPage(totalPage);
        paging.setData(users);
        paging.setTotalCount(total);
        return paging;

    }

//  @Override
//  public Paging getByObscureNo(String number,Integer pageNum, Integer pageSize) {
//      Integer skipNum = (pageNum - 1)*pageSize;
//      List<User> users =userMapper.getByPage(skipNum,pageSize);
//      Integer total =userMapper.getCountByPage();
//      Integer totalPage = (int) Math.ceil(1.0*total/pageSize);
//      Paging paging =new Paging();
//      paging.setTotalPage(totalPage);
//      paging.setData(users);
//      paging.setTotalCount(total);
//      return paging;
//    return userMapper.getByObscureNo(number);
//  }

//  @Override
//  public List<User> getByObscureDepartment(String department) {
//    return userMapper.getByObscureDepartment(department);
//  }




}
