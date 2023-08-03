package com.my.springcloud.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author Bsea
 *  用户表
 *
 */
@Entity
@Table(name="tb_user")
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 45894356169853180L;

	/**主键ID**/
	@Id
	@Column(length=60)
	private String id;

	/**用户名**/
	@Column(length=30)
	private String uname;

	/**用户登录密码**/
	@Column(length=90)
	private String pwd;

	/**用户手机号码**/
	@Column(length=30)
	private String mobile;

	/**头像**/
	private String logo;

	/**用户邮箱**/
	private String email;

	/**姓名**/
	@Column(length=30)
	private String name;

	/**用户性别**/
	@Column(length = 2)
	private String sex;

	/**用户积分**/
	private Integer mark;

	/**用户年龄**/
	private Integer age;

	/**用户等级**/
	private Integer level;

	/**头像**/
	private String imagePath;

	/**申请状态 0：未申请，1：申请中，2：申请通过**/
	private byte applicationStatus;

	/**企业营业执照**/
	private String applicationPath;


	/**用户状态0表示激化 1表示冻结**/
	private Integer status=0;

	@ManyToMany(targetEntity = Role.class)
	@JoinTable(name = "tb_user_role",
			joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	private Set<Role> roles;


}
