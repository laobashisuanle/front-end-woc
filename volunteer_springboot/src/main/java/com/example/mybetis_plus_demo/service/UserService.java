package com.example.mybetis_plus_demo.service;

import com.example.mybetis_plus_demo.common.Paging;
import com.example.mybetis_plus_demo.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laoba
 * @since 2024-02-05
 */
public interface UserService  {

    List<User> getAll();

    User getById(Integer id);

    boolean save(User user);

    boolean update(User user);

    boolean delete(Integer id);


    List<User> getList();

    User getByNo(String number);

//
//    Paging getByObscureNo(String number);
//
//    Paging getByObscureDepartment(String department);

    Paging getByPageObscure(Integer pageNum, Integer pageSize, String number, String department,Integer roleId);
}
