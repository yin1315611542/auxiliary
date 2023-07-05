package cn.goldencis.auxiliary.domain.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hub {
    private String name;
    private Boolean state;
    private String path;
    private String logPath;
    private Boolean dState;
    //jcmd 19160 VM.uptime | awk '{print int($1/60)" 分钟"}' | xargs -I {} echo "程序已运行 {}
    private Long RunTime;
}
