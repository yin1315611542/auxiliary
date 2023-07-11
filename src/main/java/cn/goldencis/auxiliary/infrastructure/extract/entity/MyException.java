package cn.goldencis.auxiliary.infrastructure.extract.entity;

import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 16:13
 **/
@Data
public class MyException extends Exception{
    private String type;

    private String  causeChain;

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException() {
    }


    public String getType() {
        return type;
    }
    public void  setCause(Throwable cause){
        this.initCause(cause);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCauseChain() {
        if (ObjectUtils.isEmpty(this.causeChain)){
            initCauseChain();
        }
        return this.causeChain;
    }
    public void initCauseChain(){
        StringBuilder causeChain = new StringBuilder(this.getMessage());
        Throwable cause = this.getCause();
        while (!ObjectUtils.isEmpty(cause)){
            causeChain.append(((MyException) cause).getType()).append(":");
            cause = cause.getCause();
        }

        this.causeChain = causeChain.substring(0,causeChain.length()-1);
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
