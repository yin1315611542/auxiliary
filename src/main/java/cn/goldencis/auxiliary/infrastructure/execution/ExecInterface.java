package cn.goldencis.auxiliary.infrastructure.execution;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-28 10:03
 **/
public interface ExecInterface {
    ExecResult exec(Step step);
    Boolean support(Step step);
}
