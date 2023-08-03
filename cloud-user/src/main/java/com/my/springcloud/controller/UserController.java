package com.my.springcloud.controller;

import com.my.springcloud.common.Result;
import com.my.springcloud.entity.User;
import com.my.springcloud.service.UserService;
import com.my.springcloud.util.MD5Utils;
import com.my.springcloud.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@Api(value = "用户控制类")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation(value = "登录API")
    @PostMapping("/login")
    public Result selectByNamePwd(@RequestBody User user) {
        System.out.println("进入认证");
        System.out.println("username***" + user.getUname());
        System.out.println("password***" + user.getPwd());
        String password = user.getPwd();
        String username = user.getUname();
        password = MD5Utils.encrypt(username, password);
        System.out.println("encryptpassword***" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User loginuser = (User) SecurityUtils.getSubject().getPrincipal();
            return ResultUtil.success(loginuser);
        } catch (UnknownAccountException e) {
            return ResultUtil.error(500, "账号不存在");
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.error(500, "密码错误");
        } catch (LockedAccountException e) {
            return ResultUtil.error(500, "账号被锁定");
        } catch (AuthenticationException e) {
            return ResultUtil.error(500, "登录失败");
        }
    }

    /**
     * 查询当前用户信息
     **/
    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result showUserInfo(@RequestParam("id") String id) {
        User userInfo = userService.getById(id);

        return ResultUtil.success(userInfo);
    }


    /**
     * 修改当前用户信息
     **/
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/infoEdit")
    public Result UserInfoEdit(@RequestBody User user) {
        if (userService.updateUser(user) == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(500, "修改失败");
        }
    }

    /**
     * 注册
     * 注意： 数据库保存的密码是 MD5加密以后的密文
     *
     * @param user
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result add(@RequestBody User user) {
        if (userService.getByUname(user.getUname()) != null) {
            return ResultUtil.error(500, "用户名已存在");
        } else {
            String password = MD5Utils.encrypt(user.getUname(), user.getPwd());
            System.out.println("注册加密密码：" + password);
            user.setPwd(password);
            return ResultUtil.success(userService.add(user));
        }

    }

    /**
     * 验证当前用户原始密码
     **/
    @ApiOperation(value = "验证原始密码")
    @PostMapping("/confirmOldPwd")
    public Result confirmOldPwd(@RequestBody User user) {
        String password = MD5Utils.encrypt(user.getUname(), user.getPwd());
        String oldPassword = userService.getById(user.getId()).getPwd();

        if (password.equals(oldPassword)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(500, "原始密码错误");
        }
    }


    /**
     * 修改当前用户原始密码
     **/
    @ApiOperation(value = "修改密码")
    @PostMapping("/modifyPwd")
    public Result modifyPwd(@RequestBody User user) {
        String password = MD5Utils.encrypt(user.getUname(), user.getPwd());
        user.setPwd(password);
        if (userService.updatePwd(user) == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(500, "修改失败");
        }

    }

    /**修改用户头像**/
    @ApiOperation(value = "修改用户头像")
    @PostMapping("/modifyImage/{imagePath}/{id}")
    public void modifyImage(@PathVariable("imagePath") String imagePath, @PathVariable("id") String id) throws IOException {

        int result = userService.modifyImagePath("images/" + id + "-" + imagePath, id);
    }

    /**企业申请**/
    @ApiOperation(value = "企业申请")
    @GetMapping("/application/{id}/{applicationPath}")
    public Result modifyApplicationStatus(@PathVariable("id") String id, @PathVariable("applicationPath") String applicationPath) throws IOException {

        if (userService.modifyApplicationStatus(id, "/job/application/" + id + "-" + applicationPath) == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(500, "申请失败");
        }
    }

    /**修改用户角色**/
    @ApiOperation(value = "修改用户角色")
    @GetMapping("/modifyRole/{id}")
//    @RequiresPermissions("userInfo:modifyRole")//权限管理;
    public Result modifyRole(@PathVariable("id") String id) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isPermitted("userInfo:modifyRole")){
            System.out.println("adgasdf");
        }
        boolean b = subject.hasRole("管理员");
        subject.checkPermission("userInfo:modifyRole");

        if (userService.modifyRole(id) == 1) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error(500, "申请失败");
        }
    }

    /**查询所有企业申请**/
    @ApiOperation(value = "查询所有企业申请")
    @GetMapping("showAllApplication")
    public Result showAllApplication(){
        return ResultUtil.success( userService.showAllApplication());
    }
}
