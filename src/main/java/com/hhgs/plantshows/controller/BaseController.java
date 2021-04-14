package com.hhgs.plantshows.controller;

import com.hhgs.plantshows.common.FileTypeEnum;
import com.hhgs.plantshows.common.Pages;
import com.hhgs.plantshows.util.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseController {
//    @Value("${template.path.power_plant}")
//    public String power_plant_template_path;

    /**
     * 动态生成文件存储路径
     */
    @Value("${file.storage.path.dynamic}")
    public String file_storage_path_dynamic;
    /**
     * 静态文件存储路径
     */
    @Value("${file.storage.path.template}")
    public String file_storage_path_template;
    /**
     * 上传文件存储路径
     */
    @Value("${file.storage.path.upload}")
    public String file_storage_path_upload;

    /**
     * 静态资源目录
     */
    @Value("${file.storage.path.static}")
    public String file_storage_path_static;



    /**
     * 分页
     * @param listObject 分页数据
     * @param pageNum    页码
     * @param pageSize   分页大小
     * @return common
     */
    protected BaseResponse processPaging(List<?> listObject, int pageNum, int pageSize) {
        Pages page = new Pages();
        page.setDataCount(listObject.size());
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);

        if(listObject.size()>0) {
            page.setPageCount((listObject.size() - 1) / pageSize + 1);
            List<?> result = new ArrayList<>();
            int start=(pageNum-1)*pageSize>listObject.size()?0:(pageNum-1)*pageSize;
            int end=pageNum*pageSize>listObject.size()?listObject.size():pageNum*pageSize;
            result=listObject.subList(start,end);

            return BaseResponse.initSuccess(result,page);
        }else{
            page.setPageCount(0);
            return BaseResponse.initSuccess(listObject,page);
        }
    }

    protected ResponseEntity<byte[]> downLoad(HttpServletResponse response, HttpServletRequest req, String fileName, String filePath,
                                      FileTypeEnum enums) throws Exception {

        try {
            FileInputStream fin=new FileInputStream(filePath);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int length = 0;
            byte[] buffer = new byte[1024];
            while ((length = fin.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            byte[] bytes = baos.toByteArray();

            HttpHeaders httpHeaders = new HttpHeaders();
            // 通知浏览器以下载文件方式打开
            httpHeaders.setContentDispositionFormData("attachment", "123.txt");
            // application/octet_stream设置MIME为任意二进制数据
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 使用到了apache commons-io 里边的 FileUtils工具类
            return new ResponseEntity<byte[]>(bytes,
                    httpHeaders, HttpStatus.OK);
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
        }
        return null;

    }
}
