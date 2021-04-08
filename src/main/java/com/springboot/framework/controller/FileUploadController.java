package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.utils.ResponseVOUtil;
import com.springboot.framework.vo.ImgUploadResponseVO;
import com.springboot.framework.vo.ResponseVO;
import com.springboot.framework.config.ImageConfig;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.service.ObjectStorageService;
import com.springboot.framework.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/13 23:17
 */
@SuppressWarnings("restriction")
@Api(tags = {"文件上传"}, produces = "application/json")
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Resource
    private ObjectStorageService objectStorageService;
    @Resource
    private ImageConfig imageConfig;

    /**
     * 上传图片
     *
     * @param file    图片
     * @param request http请求
     * @return json包装的url
     */
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "上传图片", notes = "上传图片<br/>http://aligreen.alibaba.com/porn.html,在此检测rate超过80的为涉黄图片，会上传失败")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseVO<String> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 尺寸验证
        measurementValidation(file.getInputStream());
        return ResponseVOUtil.success(objectStorageService.upload(file));
    }

    /**
     * 富文本内插入图片
     */
    @ApiOperation(value = "富文本内插入图片", notes = "")
    @RequestMapping(value = "/uploadImageEdit", method = RequestMethod.POST)
    public ResponseVO<ImgUploadResponseVO> uploadImageEdit(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 尺寸验证
        measurementValidation(file.getInputStream());
        String filePath = objectStorageService.upload(file);
        return ResponseVOUtil.success(new ImgUploadResponseVO(filePath));
    }

//  /**
//   * 上传图片
//   *
//   * @param file
//   * @param request
//   * @return
//   * @throws IOException
//   */
//  @ACS(allowAnonymous = true)
//  @ApiOperation(value = "上传图片ie浏览器", notes = "上传图片ie浏览器", consumes = "application/json")
//  @RequestMapping(value = "/uploadImageIe", method = RequestMethod.POST)
//  public void uploadImageIe(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
//      throws IOException {
//    // 尺寸验证
//    measurementValidation(file.getInputStream());
//    String filePath = obsService.upload(file);
//    response.setContentType("text/html");
//    PrintWriter out = response.getWriter();
//    out.write(filePath);
//    out.flush();
//    out.close();
//  }

//  /**
//   * 直接上传图片(base64Code方式)
//   *
//   * @param request
//   * @return
//   * @throws IOException
//   */
//  @ACS(allowAnonymous = true)
//  @ApiOperation(value = "上传图片base64Code方式", notes = "上传图片base64Code方式压缩", consumes = "application/json")
//  @RequestMapping(value = "/uploadImageBase64", method = RequestMethod.POST)
//  public ResponseEntity<String> uploadImageBase64(@RequestBody UploadImageBase64RequestBean bean, HttpServletRequest request)
//      throws IOException {
//    byte[] imgByte =
//        new BASE64Decoder().decodeBuffer(bean.getImage().substring(bean.getImage().indexOf(",") + 1, bean.getImage().length()));
//    // 尺寸验证
//    String filePath = obsService.uploadImageBase64(imgByte);
//    return ResponseEntityUtil.success(filePath);
//  }

//  /**
//   * 上传音频
//   * w
//   * @param file
//   * @param request
//   * @return
//   */
//  @ACS(allowAnonymous = true)
//  @ApiOperation(value = "上传音频", notes = "上传音频", consumes = "application/json")
//  @RequestMapping(value = "/uploadAudio", method = RequestMethod.POST)
//  public ResponseEntity<String> uploadAudio(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//    String filePath = obsService.upload(file);
//    return ResponseEntityUtil.success(filePath);
//  }

    /**
     * 图片尺寸校验
     */
    private void measurementValidation(InputStream is) {
        BufferedImage source = null;
        try {
            source = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //防止空指针异常
        assert source != null;
        int owidth = source.getWidth();
        int oheight = source.getHeight();
        if (owidth > imageConfig.getWidth() || oheight > imageConfig.getHeight()) {
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "图片尺寸过大");
        }
    }
}