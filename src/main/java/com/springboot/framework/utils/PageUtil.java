package com.springboot.framework.utils;

import com.github.pagehelper.PageInfo;
import com.springboot.framework.vo.PageResponseVO;

import java.util.List;

/**
 * @author huangpengfei
 * @since 2019/05/07
 **/
public class PageUtil {
    private static PageResponseVO getPageResponseVO(List recordList, List recordNewList) {
        PageInfo pageInfo = new PageInfo(recordList);
        pageInfo.setList(recordNewList);
        PageResponseVO page = new PageResponseVO(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    public static PageResponseVO page(List recordList) {
        return getPageResponseVO(recordList, recordList);
    }

    public static PageResponseVO page(List recordList, List recordNewList) {
        return getPageResponseVO(recordList, recordNewList);
    }
}
