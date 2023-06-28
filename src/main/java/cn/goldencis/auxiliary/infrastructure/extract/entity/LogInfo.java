package cn.goldencis.auxiliary.infrastructure.extract.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 11:36
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo {
    private String path;
    private String name;
}
