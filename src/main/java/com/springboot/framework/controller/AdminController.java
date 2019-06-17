package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.bo.UserBO;
import com.springboot.framework.constant.Const;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.*;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.dao.pojo.Admin;
import com.springboot.framework.dto.AdminDTO;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.service.RedisService;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.util.ResponseBOUtil;
import com.springboot.framework.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = {"管理员操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/admin/")
public class AdminController extends BaseController {
    @Resource
    private AdminService adminService;
    @Resource
    private RedisService redisService;

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        AdminDTO recordDTO = new AdminDTO(id, super.getSessionUser(request).getName());
        return adminService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增超级管理员", notes = "新增超级管理员")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody AdminInsert bean, HttpServletRequest request) {
        AdminDTO recordDTO = new AdminDTO(bean.getAccount(), bean.getPassword(), bean.getPhone(), bean.getName(), super.getSessionUser(request).getName());
        return adminService.insertSelective(recordDTO);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "登陆", notes = "管理员登陆")
    @PostMapping(value = "login")
    public ResponseBO<UserBO> login(@Valid @RequestBody AdminLogin bean, HttpServletRequest request) {
//        Boolean flag = verifyCode(bean.getVerifyCode());
//        if (!flag) {
//            return ResponseBOUtil.fail("验证码错误");
//        }
        AdminDTO recordDTO = new AdminDTO(bean.getLoginKey(), bean.getLoginPwd());
        ResponseBO<Admin> response = adminService.login(recordDTO);
        if (response.isSuccess()) {
            UserBO userBO = new UserBO(response.getData());
            return accessToken(userBO, request);
        }
        return ResponseBOUtil.fail("登陆失败");
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping(value = "logout")
    public ResponseBO<Void> logout(HttpServletRequest request) {
        deleteSessionUser(request);
        return ResponseBOUtil.success();
    }

    @ApiOperation(value = "查看个人信息", notes = "查看个人信息")
    @GetMapping(value = "selectBySession")
    public ResponseBO<UserBO> selectBySession(HttpServletRequest request) {
        UserBO admin = super.getSessionUser(request);
        if (admin != null) {
            return ResponseBOUtil.success(admin);
        }
        return ResponseBOUtil.fail("用户未登录，无法获取当前用户信息");
    }

    @ApiOperation(value = "查看管理员", notes = "查看管理员")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<Admin> selectByPrimaryKey(@RequestParam Integer id) {
        return adminService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看管理员列表", notes = "查看管理员列表")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adminService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看管理员总数", notes = "查看管理员总数")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return adminService.selectCount();
    }

    @ApiOperation(value = "根据手机号码查看管理员列表", notes = "根据手机号码查看管理员列表")
    @GetMapping(value = "selectListByPhone")
    public PageResponseBO selectListByPhone(@RequestParam String phone, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adminService.selectListByPhone(phone, pageNum, pageSize);
    }

    @ApiOperation(value = "更新管理员信息", notes = "更新管理员信息")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody AdminUpdateByPrimaryKey bean, HttpServletRequest request) {
        AdminDTO recordDTO = new AdminDTO(bean.getId(), bean.getPassword(), bean.getPhone(), bean.getName(), super.getSessionUser(request).getName(), bean.getStatus());
        return adminService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新个人密码", notes = "更新个人密码")
    @PutMapping(value = "updateByPassword")
    public ResponseBO<Errors> updateByPassword(@RequestBody AdminUpdateByPassword bean, HttpServletRequest request) {
        return adminService.updateByPassword(super.getSessionUser(request).getId(), bean.getOldPassword(), bean.getNewPassword(), super.getSessionUser(request).getName());
    }

    @ApiOperation(value = "更新个人手机号", notes = "更新管理员手机号")
    @PutMapping(value = "updateByPhone")
    public ResponseBO<Errors> updateByPhone(@RequestParam String phone, HttpServletRequest request) {
        AdminDTO recordDTO = new AdminDTO(super.getSessionUser(request).getId(), null, phone, null, super.getSessionUser(request).getName(), null);
        return adminService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新管理员状态", notes = "更新管理员状态")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        AdminDTO recordDTO = new AdminDTO(bean.getId(), null, null, null, super.getSessionUser(request).getName(), bean.getStatus());
        return adminService.updateByPrimaryKeySelective(recordDTO);
    }

    private Boolean verifyCode(String verifyCode) {
        if (redisService.get(Const.VERIFY_CODE) != null) {
            String randomCode = redisService.get(Const.VERIFY_CODE).toString();
            if (StringUtil.isNotBlank(randomCode) && verifyCode.toUpperCase().equals(randomCode.toUpperCase())) {
                redisService.delete(Const.VERIFY_CODE);
                return true;
            }
        }
        return false;
    }

    private ResponseBO<UserBO> accessToken(UserBO userBO, HttpServletRequest request) {
        //session.setAttribute(Const.CURRENT_USER, response.getData());
        // 创建访问token
        String accessToken = super.generateAccessToken(request);
        userBO.setAccessToken(accessToken);
        super.setAccessTokenAttribute(request, accessToken);
        super.setSessionUser(request, userBO);
        return ResponseBOUtil.success(userBO);
    }
}
