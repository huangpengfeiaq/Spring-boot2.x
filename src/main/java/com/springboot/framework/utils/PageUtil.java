package com.springboot.framework.utils;

import com.github.pagehelper.PageInfo;
import com.springboot.framework.bo.PageResponseBO;

import java.util.List;

/**
 * @author huangpengfei
 * @date 2019/05/07
 **/
public class PageUtil {
    private static PageResponseBO getPageResponseBO(List recordList, List recordVOList) {
        PageInfo pageInfo = new PageInfo(recordList);
        pageInfo.setList(recordVOList);
        PageResponseBO page = new PageResponseBO(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    public static PageResponseBO page(List recordList) {
        return getPageResponseBO(recordList, recordList);
    }

    public static PageResponseBO page(List recordList, List recordVOList) {
        return getPageResponseBO(recordList, recordVOList);
    }
}
