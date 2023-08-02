package cn.goldencis.auxiliary.domain.step.enumstep;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

public class StepTypeConverter implements AttributeConverter<StepType,Integer> {
    @Override
    public Integer convertToDatabaseColumn(StepType stepType) {
        return stepType.getCode();
    }

    @Override
    public StepType convertToEntityAttribute(Integer code) {
        return Arrays.stream(StepType.values()).filter(e->e.getCode().equals(code)).findFirst().get();
    }
}
