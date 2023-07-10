package cn.goldencis.auxiliary.domain.loginfo;

import cn.goldencis.auxiliary.domain.loginfo.enumeration.ErrorInfoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String occurrenceTime;

    public ErrorInfo(String message, ErrorInfoType type) {
        this.message = message;
        this.type = type;
    }

    public ErrorInfo init() {
        if (!ObjectUtils.isEmpty(this.message)) {
            this.occurrenceTime = extractTimestamp(this.message);
        }
        return this;
    }

    //message中截取时间戳
    public String extractTimestamp(String logRecord) {
        Matcher matcher = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}").matcher(logRecord);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
