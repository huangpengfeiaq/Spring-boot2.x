package com.springboot.framework.util;

import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;

import java.util.List;

/**
 * @author Huangpengfei
 * @date 2019/05/07
 **/
public class PageUtil {
    public static PageResponseBean page(List recordList) {
        PageInfo pageInfo = new PageInfo(recordList);
        pageInfo.setList(recordList);
        PageResponseBean page = new PageResponseBean(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    public static PageResponseBean page(List recordList, List recordVOList) {
        PageInfo pageInfo = new PageInfo(recordList);
        pageInfo.setList(recordVOList);
        PageResponseBean page = new PageResponseBean(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }
}
