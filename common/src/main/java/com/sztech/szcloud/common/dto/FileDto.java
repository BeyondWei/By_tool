package com.sztech.szcloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FileDto {
    private String uuid;
    private String fileName;
    private String filePath;
    private String fileUrl;
}
