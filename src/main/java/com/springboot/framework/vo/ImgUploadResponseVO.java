package com.springboot.framework.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangpengfei
 */
@Data
public class ImgUploadResponseVO {
    /**
     * 返回数据内容
     */
    @ApiModelProperty(value = "图片路径")
    private String src;

    public ImgUploadResponseVO(String src) {
        this.src = src;
    }

}
