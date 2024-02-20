package com.example.mybetis_plus_demo.common;



import com.example.mybetis_plus_demo.pojo.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Paging {


    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 集合数据
     */
    private List<User> data;






}
