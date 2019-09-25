package com.sztech.szcloud.file.service.impl;

import com.sztech.szcloud.common.dto.FileDto;
import com.sztech.szcloud.common.exception.BusinessException;
import com.sztech.szcloud.common.util.UuidUtil;
import com.sztech.szcloud.file.service.BusFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class BusFileServiceImpl implements BusFileService {


    @Override
    public void download(HttpServletResponse response, String realPath, String fileName) throws UnsupportedEncodingException {
        File file = new File(realPath);
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            // 设置文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public List<FileDto> upload(MultipartFile[] files, String filePath, String fileUrl) {
        if (files == null || files.length == 0) {
            throw new BusinessException("01", "上传失败，请选择文件");
        }
        List<FileDto> fileDtoList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileDto fileDto = new FileDto();
            String fileName = file.getOriginalFilename();
            fileDto.setFileName(fileName);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //设置文件上传的绝对路径
            String uuid = UuidUtil.uniqueNo16();
            fileDto.setUuid(uuid);
            StringBuilder lastName = new StringBuilder();
            lastName.append("/").append(uuid).append("_").append(simpleDateFormat.format(date)).append("_").append(fileName);
            fileName = filePath + simpleDateFormat.format(date) + lastName.toString();
            if (!StringUtils.isEmpty(fileUrl)) {
                fileDto.setFileUrl(fileUrl + simpleDateFormat.format(date) + lastName.toString());
            }
            File dest = new File(fileName).getAbsoluteFile();
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                fileDto.setFilePath(dest.getAbsolutePath().replace("\\","/"));
                log.info(fileName + "上传成功");
                // todo 存入数据库
//                FileInfo fileInfo = new FileInfo();
//                dozerMapper.map(fileDto, fileInfo);
//                fileInfo.setCreateTime(new Date());
//                fileInfo.setIsDelete(ConstantsEnum.DATA_STATUS.NORMAL.getStatus());
//                fileInfoService.insertNotNull(fileInfo);
            } catch (IOException e) {
                throw new BusinessException("01", "上传失败,系统异常");
            }
            fileDtoList.add(fileDto);
        }
        return fileDtoList;
    }

    @Override
    public void downloadFile(HttpServletResponse response, String fileUuid) throws UnsupportedEncodingException {
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setFileUuid(fileUuid);
//        fileInfo.setIsDelete(ConstantsEnum.DATA_STATUS.NORMAL.getStatus());
//        fileInfo = fileInfoService.selectOne(fileInfo);
//        if (fileInfo == null) {
//            throw new BusinessException("03", "文件不存在");
//        }
//        this.download(response, fileInfo.getFilePath(), fileInfo.getFileName());
    }



}
