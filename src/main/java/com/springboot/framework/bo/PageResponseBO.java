package com.springboot.framework.bo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回数据
 *
 * @author liyuchang
 * @version V1.0
 * @date 2017年3月31日
 */
@Data
@ApiModel
public class PageResponseBO<T> implements Serializable {

    private static final long serialVersionUID = 6887389993060457824L;

    /**
     * 返回数据内容
     */
    @ApiModelProperty(value = "返回数据内容")
    private List<T> content;
    /**
     * 总条目数
     */
    @ApiModelProperty(value = "总条数")
    private long totalElements;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private int totalPages;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int pageNum;
    /**
     * 当前页的条目数
     */
    @ApiModelProperty(value = "每页显示条数")
    private int pageSize;

    /**
     * 是否是第一页
     */
    @ApiModelProperty(value = "是否是第一页")
    private boolean first;

    /**
     * 是否是最后一页
     */
    @ApiModelProperty(value = "是否是最后一页")
    private boolean last;

    @ApiModelProperty(value = "code")
    private int code;

    @ApiModelProperty(value = "httpStatus")
    private int httpStatus;

    public PageResponseBO() {
    }

    public PageResponseBO(List<T> content, long totalElements, int pageNum, int pageSize) {
        super();
        this.content = content;
        this.totalElements = totalElements;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageResponseBO(PageInfo<T> pageInfo) {
        this.totalElements = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.totalPages = pageInfo.getPages();
        this.content = pageInfo.getList();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return (int) ((this.totalElements > 0 && this.pageSize > 0 && this.totalPages == 0)
                ? (this.totalElements / this.pageSize + (this.totalElements % this.pageSize > 0 ? 1 : 0)) : totalPages);
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return pageNum == totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirst() {
        return pageNum == 1;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
