package com.example.mybetis_plus_demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author laoba
 * @since 2024-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String number;


    @ApiModelProperty(value = "学号")
    private String studentId;


    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "角色 0超级管理员，1管理员，2普通账号")
    private Integer roleId;

    @ApiModelProperty(value = "是否初次登录，0是，1不是")
    private Integer isFirst;
    @ApiModelProperty(value = "志愿经历")
    private String experience;

    private String token;




}
