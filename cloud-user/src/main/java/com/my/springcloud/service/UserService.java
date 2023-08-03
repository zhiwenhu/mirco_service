package com.my.springcloud.service;

import com.my.springcloud.entity.User;
import com.my.springcloud.repository.UserRepository;
import com.my.springcloud.util.KeyUtil;
import com.my.springcloud.repository.DTODao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;
    @Resource
    DTODao dtoDao;

    /**
     * 新建用户
     * @param user
     * @return
     */
    public User add(User user) {
        user.setId(KeyUtil.getId());
        return userRepository.save(user);
    }

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    public User getById(String id) {
        return userRepository.findById(id).get();
    }

    /**
     * 查询用户信息
     * @param uname
     * @return
     */
    public User getByUname(String uname) {
        return userRepository.findByUname(uname);
    }

    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    public int updateUser(User user) {
        return userRepository.userEdit(user.getName(),user.getSex(),user.getMobile(),user.getId(),user.getAge(),user.getEmail());
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int updatePwd (User user) {
        return userRepository.updatePwd(user.getPwd(), user.getId());
    }

    /**
     * 修改头像
     * @param imagePath
     * @param id
     * @return
     */
    public int modifyImagePath(String imagePath, String id) {
        return userRepository.modifyImagePath(imagePath,id);
    }

    /**
     * 职位申请
     * @param id
     * @param path
     * @return
     */
    public int modifyApplicationStatus(String id, String path) {
        return userRepository.modifyApplicationStatus(id, path);
    }

    /**
     * 用户角色更改
     * @param id
     * @return
     */
    public int modifyRole(String id) {
        return dtoDao.modifyRole(id);
    }

    /**
     * 显示申请中的用户
     * @return
     */
    public List<User> showAllApplication() {
        return userRepository.findByApplicationStatus((byte)1);
    }
}
