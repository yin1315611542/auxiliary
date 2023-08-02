package cn.goldencis.auxiliary.infrastructure.condition;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.common.ELUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CompareDistinguish implements Distinguish {
    @Override
    public Boolean distinguished(Step step) {
        if (ObjectUtils.isEmpty(step.getExcCondition())) {
            return false;
        } else {
            return ELUtils.evaluate(step.getExcCondition());
        }
    }

    @Override
    public Boolean support(ConditionType conditionType) {
        return ConditionType.Compare.equals(conditionType);
    }
}
