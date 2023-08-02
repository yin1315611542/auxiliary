package cn.goldencis.auxiliary.domain.loginfo.enumeration;

import javax.persistence.AttributeConverter;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-26 15:53
 **/
public class ErrorInfoConverter implements AttributeConverter<ErrorInfoType,Integer> {

    @Override
    public Integer convertToDatabaseColumn(ErrorInfoType errorInfoType) {
        return null;
    }

    @Override
    public ErrorInfoType convertToEntityAttribute(Integer integer) {
        return null;
    }
}
