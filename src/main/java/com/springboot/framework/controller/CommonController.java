package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.config.AppConfig;
import com.springboot.framework.constant.Const;
import com.springboot.framework.utils.RedisUtil;
import com.springboot.framework.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:39
 */
@Api(tags = {"公共操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/common/")
public class CommonController {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private AppConfig appConfig;

//    /**
//     * 发送验证码
//     *
//     * @param request
//     * @return
//     */
//    @ACS(allowAnonymous = true)
//    @ApiOperation(
//            value = "发送短信验证码",
//            notes = "type类型：1注册,2修改密码,3重置密码,4注册+登陆,5绑定卡<br/>有效时间5分钟，相同类型发送冷却时间1分钟<br/>返回：code=0成功；code=1手机号有误;code=2未超过发送冷却时间，exception=剩余发送冷却时间(单位秒)；code=3送失败请稍后再试")
//    @PostMapping(value = "/sms/sendCaptcha")
//    public ResponseVO<CaptchaResponseBean> sendCaptcha(@Valid @RequestBody CaptchaRequestBean bean, BindingResult bindingResult, HttpServletRequest request) {
//        if (bindingResult.hasErrors()) {
//            throw new BusinessException(bindingResult.getFieldError().getDefaultMessage());
//        }
//        try {
//            CaptchaResponseBean result = mobileCaptchaService.send(bean, request);
//            return ResponseVoUtil.success(result);
//        } catch (BusinessException e) {
//            throw e;
//        }
//    }


    @ACS(allowAnonymous = true)
    @ApiOperation(value = "图片验证码接口", notes = "随机验证码")
    @GetMapping(value = "get_verify_code")
    public void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1定义bufferedImage对象
        BufferedImage bImage = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        //2获得Graphics对象
        Graphics graphics = bImage.getGraphics();

        Color color = new Color(200, 150, 255);
        graphics.setColor(color);
        graphics.fillRect(0, 0, 68, 22);

        // 3通过Random产生随机验证码信息
        // 4使用Graphics绘制图片
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        Random r = new Random();
        int len = ch.length, index;

        String str = "";
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            graphics.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            graphics.drawString(ch[index] + "", (i * 15) + 5, 16);

            str += ch[index];
        }

        // 5将验证码信息保存到Session中
        // request.getSession().setAttribute(Const.VERIFY_CODE, str);
        redisUtil.set(appConfig.getAppName() + "_" + appConfig.getEnv() + "_" + Const.VERIFY_CODE, str, 600);

        System.out.println("verifyCode:---------------" + str);
        // 6使用ImageIO输出图片
        ImageIO.write(bImage, "JPG", response.getOutputStream());
    }


    @ACS(allowAnonymous = true)
    @ApiOperation(value = "校验图片验证码接口", notes = "校验随机验证码")
    @PostMapping(value = "valid_verify_code")
    public ResponseEntity<Boolean> validVerifyCode(@RequestBody String code, HttpServletRequest request) {
        Boolean flag = false;
        // String randomCode=
        // (String)request.getSession().getAttribute(Const.VERIFY_CODE);

        if (redisUtil.get(Const.VERIFY_CODE) != null) {
            String randomCode = redisUtil.get(Const.VERIFY_CODE).toString();

            if (StringUtil.isNotBlank(randomCode) && code.toUpperCase().equals(randomCode.toUpperCase())) {
                flag = true;
                // request.getSession().removeAttribute(Const.VERIFY_CODE);
                redisUtil.del(Const.VERIFY_CODE);
            }
        }
        return ResponseEntity.ok(flag);
    }

//    /**
//     * 根据地址名称获取经纬度
//     */
//    @ACS(allowAnonymous = true)
//    @ApiOperation(value = "逆地址解析（坐标位置描述）", notes = "本接口提供由坐标到坐标所在位置的文字描述的转换。输入坐标返回地理位置信息和附近poi列表。目前应用于物流、出行、O2O、社交等场景。服务响应速度快、稳定，支撑亿级调用。\n" +
//            "     1）满足传统对省市区、乡镇村、门牌号、道路及交叉口、河流、湖泊、桥、poi列表的需求。\n" +
//            "     2）业界首创，提供易于人理解的地址描述：海淀区中钢国际广场(欧美汇购物中心北)。\n" +
//            "     3）提供精准的商圈、知名的大型区域、附近知名的一级地标、代表当前位置的二级地标等。")
//    @GetMapping(value = "geocoderByLocation")
//    public JSONObject geocoderByLocation(@RequestParam String lat, @RequestParam String lng) {
//        String url = "https://apis.map.qq.com/ws/geocoder/v1/";
//        String jsonStrToken = ToolsUtil.sendGet(url, "location=" + lat + "," + lng + "&key=" + appConfig.getTencentMapKey());
//        return JSON.parseObject(jsonStrToken);
//    }
//
//    /**
//     * 根据地址名称获取经纬度
//     */
//    @ACS(allowAnonymous = true)
//    @ApiOperation(value = "地址解析（地址转坐标）", notes = "本接口提供由地址描述到所述位置坐标的转换，与逆地址解析的过程正好相反。")
//    @GetMapping(value = "geocoderByAddress")
//    public JSONObject geocoderByAddress(@RequestParam String address) {
//        String url = "https://apis.map.qq.com/ws/geocoder/v1/";
//        String jsonStrToken = ToolsUtil.sendGet(url, "address=" + address + "&key=" + appConfig.getTencentMapKey());
//        return JSON.parseObject(jsonStrToken);
//    }
}
