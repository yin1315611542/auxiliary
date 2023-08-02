package cn.goldencis.auxiliary.infrastructure.execution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExecResult {
    Integer code;
    StringBuffer message = new StringBuffer();
    StringBuffer errorMessage = new StringBuffer();
    Integer lineNumber;
}
