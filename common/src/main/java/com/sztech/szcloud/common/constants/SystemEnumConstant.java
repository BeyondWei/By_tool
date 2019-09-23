package com.sztech.szcloud.common.constants;

import lombok.Getter;
import org.springframework.util.StringUtils;

public interface SystemEnumConstant {

    /**
     * 文件上传类型（fileType:文件类型）
     */
    @Getter
    enum FILE_TYPE {
        EXCLE("01","excle"), WORD("02","word"),PDF("03","pdf"),
        PIC("11","pic");

        private String code;
        private String fileType;

        FILE_TYPE(String code,String fileType) {
            this.code = code;
            this.fileType = fileType;
        }

        public static String getValue(String code) {
            String value = "";
            for (FILE_TYPE s : FILE_TYPE.values()) {
                if (s.getCode().equals(code)) {
                    value = s.getFileType();
                    break;
                }
            }
            return value;
        }
    }
}
