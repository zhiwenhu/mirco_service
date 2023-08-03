package com.my.springcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 角色
 */
@Table(name = "tb_role")
@Entity
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 715713024946065570L;
    @Id
    @GeneratedValue
    private Long id; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户


    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TbRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;
    // 用户 - 角色关系定义;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TbUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<User> userInfos;// 一个角色对应多个用户


}
