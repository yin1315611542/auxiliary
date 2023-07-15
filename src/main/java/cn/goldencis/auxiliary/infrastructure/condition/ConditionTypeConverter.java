package cn.goldencis.auxiliary.infrastructure.condition;

import javax.persistence.AttributeConverter;

public class ConditionTypeConverter implements AttributeConverter<ConditionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ConditionType conditionType) {
        return conditionType == null ? null : conditionType.getCode();
    }

    @Override
    public ConditionType convertToEntityAttribute(Integer code) {
        return ConditionType.of(code);
    }
}
