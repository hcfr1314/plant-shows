package com.hhgs.plantshows.common;

import com.github.pagehelper.PageInfo;

/**
 * 分页工具类
 */
public class PageUtil {
    public static Pages genPage(PageInfo pageInfo){
        Pages page = new Pages();
        page.setPageNum(pageInfo.getPageNum());
        page.setPageCount(pageInfo.getPages());
        page.setDataCount((int)pageInfo.getTotal());
        page.setPageSize(pageInfo.getPageSize());
        return page;
    }

    /**
     * 验证处理  pageNum  pageSize
     * @param pageNum  页码
     * @param pageSize  每页行数
     */
    public static void processPageNumberAndSize(int pageNum,int pageSize) {
        pageSize=pageSize>CacheConstant.maxPageSize?CacheConstant.maxPageSize:pageSize;
        pageSize=pageSize<CacheConstant.minPageSize?CacheConstant.minPageSize:pageSize;
        pageNum=pageNum>CacheConstant.maxPageNum?CacheConstant.maxPageNum:pageNum;
        pageNum=pageNum<CacheConstant.minPageNum?CacheConstant.minPageNum:pageNum;
    }
}
