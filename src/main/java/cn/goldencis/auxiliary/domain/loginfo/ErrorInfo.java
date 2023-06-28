package cn.goldencis.auxiliary.domain.loginfo;

import cn.goldencis.auxiliary.domain.loginfo.enumeration.ErrorInfoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-26 15:39
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    String message;
    ErrorInfoType type;

}
