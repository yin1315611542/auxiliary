package cn.goldencis.auxiliary.infrastructure.execution;

import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.step.Step;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-28 10:03
 **/
public interface ExecInterface {
    void exec(Step step);
    Boolean support(Step step);
}
