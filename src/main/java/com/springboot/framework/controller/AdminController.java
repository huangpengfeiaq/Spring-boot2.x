package com.springboot.framework.controller;

import com.springboot.framework.controller.request.*;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "管理员操作接口", produces = "application/json")
@RestController
@RequestMapping("/admin/")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseEntity<Integer> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        return adminService.deleteByPrimaryKey(id, super.getSessionUser(request).getName());
    }

    @ApiOperation(value = "新增管理员", notes = "新增管理员")
    @PostMapping(value = "insertSelective")
    public ResponseEntity<Integer> insertSelective(@RequestBody AdminInsertSelective bean, HttpServletRequest request) {
        Admin record = new Admin(bean.getAccount(), bean.getPassword(), bean.getPhone(), bean.getName(), super.getSessionUser(request).getName());
        return adminService.insertSelective(record);
    }

    @ApiOperation(value = "登陆", notes = "管理员登陆")
    @PostMapping(value = "login")
    public ResponseEntity<Admin> login(@Valid @RequestBody AdminLogin bean, HttpServletRequest request) {
        ResponseEntity<Admin> response = adminService.login(bean.getPhone(), bean.getLoginPwd());
        if (response.isSuccess()) {
            Admin admin = response.getData();
            //session.setAttribute(Const.CURRENT_USER, response.getData());
            // 创建访问token
            String accessToken = super.generateAccessToken(request);
            admin.setAccessToken(accessToken);

            super.setAccessTokenAttribute(request, accessToken);
            super.setSessionUser(request, admin);

            return ResponseEntityUtil.success(admin);
        }
        return response;
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping(value = "logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        deleteSessionUser(request);
        return ResponseEntityUtil.success();
    }

    @ApiOperation(value = "查看个人信息", notes = "查看个人信息")
    @GetMapping(value = "selectBySession")
    public ResponseEntity<Admin> selectBySession(HttpServletRequest request) {
//        System.out.println("re:"+request);
        Admin admin = super.getSessionUser(request);
        if (admin != null) {
            return ResponseEntityUtil.success(admin);
        }
        return ResponseEntityUtil.fail("用户未登录，无法获取当前用户信息");
    }

    @ApiOperation(value = "查看管理员", notes = "查看管理员")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<Admin> selectByPrimaryKey(@RequestParam Integer id) {
        return adminService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看管理员列表", notes = "查看管理员列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return adminService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "更新管理员状态", notes = "更新查看管理员状态")
    @PutMapping(value = "updateStatus")
    public ResponseEntity<Integer> updateStatus(@RequestParam Integer id, @RequestParam Byte status, HttpServletRequest request) {
        return adminService.updateStatus(id, status, super.getSessionUser(request).getName());
    }
}
