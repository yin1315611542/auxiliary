package cn.goldencis.auxiliary.domain.loginfo.enumeration;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-26 15:52
 **/
public enum ErrorInfoType {
    commonError(1,"普通错误"),
    codeError(2,"code定义的错误");

    private Integer code;
    private String name;

    ErrorInfoType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
