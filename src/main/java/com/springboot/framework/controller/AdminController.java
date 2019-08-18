package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.exception.BusinessException;
import com.springboot.framework.utils.BinaryUtil;
import com.springboot.framework.utils.PageUtil;
import com.springboot.framework.utils.ResponseVOUtil;
import com.springboot.framework.vo.PageResponseVO;
import com.springboot.framework.vo.ResponseVO;
import com.springboot.framework.vo.UserVO;
import com.springboot.framework.constant.Const;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.*;
import com.springboot.framework.dao.pojo.Admin;
import com.springboot.framework.dto.AdminDTO;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.service.RedisService;
import com.springboot.framework.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author huangpengfei
 */
@Api(tags = {"管理员操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/admin/")
public class AdminController extends BaseController {
    @Resource
    private AdminService adminService;
    @Resource
    private RedisService redisService;

    @ApiOperation(value = "删除管理员", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseVO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setUpdateBy(super.getSessionUser(request).getName());
        try {
            Errors errors = adminService.deleteByPrimaryKey(admin);
            return ResponseVOUtil.success(errors);
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "新增超级管理员", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseVO<Errors> insertSelective(@RequestBody AdminInsert bean, HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setAccount(bean.getAccount());
        admin.setPassword(BinaryUtil.encodeMd5(bean.getPassword()));
        admin.setPhone(bean.getPhone());
        admin.setName(bean.getName());
        admin.setCreateBy(super.getSessionUser(request).getName());
        try {
            Errors errors = adminService.insertSelective(admin);
            return ResponseVOUtil.success(errors);
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "登陆", notes = "")
    @PostMapping(value = "login")
    public ResponseVO<UserVO> login(@Valid @RequestBody AdminLogin bean, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        //验证码校验：验证码会在控制台显示，前端可通过CommonController获取验证码。若有自定义验证码工具，注释以下56-59行代码即可
//        Boolean flag = verifyCode(bean.getVerifyCode());
//        if (!flag) {
//            return ResponseVOUtil.fail("验证码错误");
//        }
        try {
            Admin admin = adminService.login(bean.getLoginKey(), bean.getLoginPwd());
            UserVO userVO = new UserVO(admin);
            return ResponseVOUtil.success(accessToken(userVO, request));
        } catch (BusinessException e) {
            System.out.println(e.getLabel());
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "退出登录", notes = "")
    @PostMapping(value = "logout")
    public ResponseVO<Void> logout(HttpServletRequest request) {
        deleteSessionUser(request);
        return ResponseVOUtil.success();
    }

    @ApiOperation(value = "查看个人信息", notes = "")
    @GetMapping(value = "selectBySession")
    public ResponseVO<UserVO> selectBySession(HttpServletRequest request) {
        UserVO admin = super.getSessionUser(request);
        if (admin != null) {
            return ResponseVOUtil.success(admin);
        }
        return ResponseVOUtil.fail("用户未登录，无法获取当前用户信息");
    }

    @ApiOperation(value = "查看管理员", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseVO<Admin> selectByPrimaryKey(@RequestParam Integer id) {
        try {
            Admin admin = adminService.selectByPrimaryKey(id);
            return ResponseVOUtil.success(admin);
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "查看管理员列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseVO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List list = adminService.selectList(pageNum, pageSize);
        return PageUtil.page(list);
    }

    @ApiOperation(value = "查看管理员总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseVO<Integer> selectCount() {
        Admin admin = new Admin();
        admin.setStatus((byte) 1);
        try {
            return ResponseVOUtil.success(adminService.selectCount(admin));
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "根据手机号码查看管理员列表", notes = "")
    @GetMapping(value = "selectListByPhone")
    public PageResponseVO selectListByPhone(@RequestParam String phone, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List list = adminService.selectListByPhone(phone, pageNum, pageSize);
        return PageUtil.page(list);
    }

    @ApiOperation(value = "更新管理员信息", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseVO<Errors> updateByPrimaryKeySelective(@RequestBody AdminUpdateByPrimaryKey bean, HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setId(bean.getId());
        admin.setPassword(BinaryUtil.encodeMd5(bean.getPassword()));
        admin.setPhone(bean.getPhone());
        admin.setName(bean.getName());
        admin.setUpdateBy(super.getSessionUser(request).getName());
        admin.setStatus(bean.getStatus());
        try {
            return ResponseVOUtil.success(adminService.updateByPrimaryKeySelective(admin));
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "更新个人密码", notes = "")
    @PutMapping(value = "updateByPassword")
    public ResponseVO<Errors> updateByPassword(@RequestBody AdminUpdateByPassword bean, HttpServletRequest request) {
        try {
            return ResponseVOUtil.success(adminService.updateByPassword(super.getSessionUser(request).getId(), bean.getOldPassword(), bean.getNewPassword(), super.getSessionUser(request).getName()));
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "更新个人手机号", notes = "")
    @PutMapping(value = "updateByPhone")
    public ResponseVO<Errors> updateByPhone(@RequestParam String phone, HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setId(super.getSessionUser(request).getId());
        admin.setPhone(phone);
        admin.setUpdateBy(super.getSessionUser(request).getName());
        try {
            return ResponseVOUtil.success(adminService.updateByPrimaryKeySelective(admin));
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @ApiOperation(value = "更新管理员状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseVO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        Admin admin = new Admin();
        admin.setId(bean.getId());
        admin.setUpdateBy(super.getSessionUser(request).getName());
        admin.setStatus(bean.getStatus());
        try {
            return ResponseVOUtil.success(adminService.updateByPrimaryKeySelective(admin));
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
    }

    @Deprecated
    private ResponseVO tryService(Object obj) {
        try {
            return ResponseVOUtil.success(obj);
        } catch (BusinessException e) {
            return ResponseVOUtil.fail(e.getCode(), e.getLabel());
        }
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

    private UserVO accessToken(UserVO userVO, HttpServletRequest request) {
        //session.setAttribute(Const.CURRENT_USER, response.getData());
        // 创建访问token
        String accessToken = super.generateAccessToken(request);
        userVO.setAccessToken(accessToken);
        super.setAccessTokenAttribute(request, accessToken);
        super.setSessionUser(request, userVO);
        return userVO;
    }
}
