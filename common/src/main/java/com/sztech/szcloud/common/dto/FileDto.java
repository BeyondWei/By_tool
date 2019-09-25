package com.sztech.szcloud.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sztech.szcloud.common.serializer.JsonObjectSerializer;
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
    @JsonSerialize(using = JsonObjectSerializer.class)
    private String filePath;
    private String fileUrl;
}
