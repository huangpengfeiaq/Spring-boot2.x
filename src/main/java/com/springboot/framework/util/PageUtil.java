package com.springboot.framework.util;

import com.github.pagehelper.PageInfo;
import com.springboot.framework.bo.PageResponseBO;

import java.util.List;

/**
 * @author Huangpengfei
 * @date 2019/05/07
 **/
public class PageUtil {
    public static PageResponseBO page(List recordList) {
        PageInfo pageInfo = new PageInfo(recordList);
        pageInfo.setList(recordList);
        PageResponseBO page = new PageResponseBO(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    public static PageResponseBO page(List recordList, List recordVOList) {
        PageInfo pageInfo = new PageInfo(recordList);
        pageInfo.setList(recordVOList);
        PageResponseBO page = new PageResponseBO(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }
}
