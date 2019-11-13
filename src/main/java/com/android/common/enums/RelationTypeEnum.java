package com.android.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RelationTypeEnum
 * @Description 用户关系枚举
 * @Author: lijiao73
 * @Date: 2019/11/13 16:26
 */
public enum RelationTypeEnum {
    PARENTS("PARENTS", "父母"),
    SELF("SELF", "自己"),
    BROTHER("MEDIA_FILE", "兄弟"),
    SISTER("SISTER", "姐妹"),
    CHILDREN("CHILDREN", "子女"),
    OTHERS("COOPERATE_FILE", "其他"),
    ;
    private String code;
    private String desc;

    RelationTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private static Map<String, RelationTypeEnum> map = new HashMap<String, RelationTypeEnum>();
    static {
        RelationTypeEnum[] values = values();
        for (int i = 0; i < values.length; i++) {
            RelationTypeEnum temp = values[i];
            map.put(temp.getCode(), temp);
        }
    }

    public static RelationTypeEnum getEnumByCode(String code){
        return map.get(code);
    }
}
