package cn.goldencis.auxiliary.infrastructure.condition;

import lombok.Getter;

public enum ConditionType {
    Exist("存在判断", 1),
    Compare("数值比较判断", 2);
    private String name;

    @Getter
    private Integer code;

    ConditionType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static ConditionType of(Integer code) {
        for (ConditionType conditionType : ConditionType.values()) {
            if (conditionType.code.equals(code)) {
                return conditionType;
            }
        }
        return null;
    }

}
