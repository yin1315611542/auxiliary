package cn.goldencis.auxiliary.infrastructure.condition;

import cn.goldencis.auxiliary.domain.step.Step;

public interface Distinguish {
    Boolean distinguished(Step step);

    Boolean support(ConditionType conditionType);
}
