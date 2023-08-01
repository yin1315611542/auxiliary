package cn.goldencis.auxiliary.domain.problem.enumtype;

import cn.goldencis.auxiliary.domain.loginfo.enumeration.ErrorInfoType;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-07-31 15:46
 **/
public class ProblemTypeConverter implements AttributeConverter<ProblemType,Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProblemType problemType) {
        return problemType.getCode();
    }

    @Override
    public ProblemType convertToEntityAttribute(Integer code) {
        return Arrays.stream(ProblemType.values()).filter(e->e.getCode().equals(code)).findFirst().get();
    }
}
