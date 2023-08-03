package com.my.springcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Bsea
 *  角色
 *
 */
@Table(name="tb_role")
@Entity
@Data
public class Role  implements Serializable {
    private static final long serialVersionUID = -4351769176316939920L;

    /**主键ID**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**角色标识程序中判断使用,如"admin",这个是唯一的:**/
    private String role;

    /**角色描述,UI界面显示使用**/
    private String description;

    /**是否可用,如果不可用将不会添加给用户**/
    private Boolean available = Boolean.FALSE;


    @ManyToMany(targetEntity = Permission.class)
    @JoinTable(name="tb_role_permission",
            joinColumns={@JoinColumn(name="role_id", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="permission_id", referencedColumnName = "id")}
    )
    private Set<Permission> permissions;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;// 一个角色对应多个用户


}
