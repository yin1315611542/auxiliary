package cn.goldencis.auxiliary.infrastructure.execution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecResult {
    Integer code;
    String message;
    Integer lineNumber;
}
