package com.sztech.szcloud.file.service;


import com.sztech.szcloud.common.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface BusFileService {

    /**
     * 文件下载
     *
     * @author 无垠
     * @Date 2019/7/11 13:52
     */
    void download(HttpServletResponse response, String realPath, String fileName) throws UnsupportedEncodingException;

    /**
     * 文件上传
     *
     * @author 无垠
     * @Date 2019/7/11 13:53
     */
    List<FileDto> upload(MultipartFile[] files, String filePath, String fileUrl);

    /**
    *  下载表中的文件
    * @author 无垠
    * @Date 2019/8/23 11:09
    */
    void downloadFile(HttpServletResponse response, String fileUuid) throws UnsupportedEncodingException;

}
