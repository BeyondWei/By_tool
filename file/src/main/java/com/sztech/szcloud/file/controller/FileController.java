package com.sztech.szcloud.file.controller;

import com.sztech.szcloud.common.constants.SystemEnumConstant;
import com.sztech.szcloud.common.dto.ResultsDto;
import com.sztech.szcloud.common.properties.ResourceProperties;
import com.sztech.szcloud.file.service.BusFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 文件操作
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Resource
    private BusFileService fileService;

    @Autowired
    private ResourceProperties resourceProperties;

    /**
     * 文件上传
     * fileType:文件类型
     *
     * @author 无垠
     * @Date 2019/8/21 17:45
     */
    @RequestMapping("uploadFile")
    public ResultsDto uploadFile(MultipartFile[] files, String fileType) {
        String path = "";
        String url = null;
        if (SystemEnumConstant.FILE_TYPE.PIC.getCode().equals(fileType)) {
            path = resourceProperties.getPicPath();
            url = resourceProperties.getPicUrl();
        }
        return ResultsDto.success(fileService.upload(files, path, url));
    }

    /**
     * 上传至本地的文件下载
     *
     * @author 无垠
     * @Date 2019/8/23 11:04
     */
    @RequestMapping("downloadLocalFile")
    public void downloadLocalFile(HttpServletResponse response, String fileUuid) throws UnsupportedEncodingException {
        fileService.downloadFile(response, fileUuid);
    }

    /**
     * 上传至本地的文件下载
     *
     * @author 无垠
     * @Date 2019/9/19 15:49
     */
    @RequestMapping("downloadLocalFileT")
    public void downloadLocalFile(HttpServletResponse response, String realPath, String fileName) throws UnsupportedEncodingException {
        fileService.download(response, realPath, fileName);
    }





}
