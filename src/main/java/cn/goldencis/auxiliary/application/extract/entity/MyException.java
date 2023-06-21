package cn.goldencis.auxiliary.application.extract.entity;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 16:13
 **/
public class MyException extends Exception{
    private String type;

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getType()).append(": ").append(getMessage());
        if (getCause() != null) {
            sb.append("\nCaused by: ").append(getCause());
        }
        return sb.toString();
    }
}
