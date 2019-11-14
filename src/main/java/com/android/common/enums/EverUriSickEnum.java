package com.android.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EverUriSickEnum
 * @Description 曾经是否患病枚举
 * @Author: lijiao73
 * @Date: 2019/11/13 16:25
 */
public enum EverUriSickEnum {
    YES("YES", "是"),
    NO("NO", "否"),
    ;
    private String code;
    private String desc;

    EverUriSickEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<String, EverUriSickEnum> map = new HashMap<String, EverUriSickEnum>();
    static {
        EverUriSickEnum[] values = values();
        for (int i = 0; i < values.length; i++) {
            EverUriSickEnum temp = values[i];
            map.put(temp.getCode(), temp);
        }
    }

    public static EverUriSickEnum getEnumByCode(String code){
        return map.get(code);
    }
}
