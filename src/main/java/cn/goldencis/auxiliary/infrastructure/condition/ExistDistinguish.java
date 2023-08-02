package cn.goldencis.auxiliary.infrastructure.condition;

import cn.goldencis.auxiliary.domain.step.Step;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class ExistDistinguish implements Distinguish {
    @Override
    public Boolean distinguished(Step step) {
        AtomicReference<Boolean> mark = new AtomicReference<>(true);
        if (ObjectUtils.isEmpty(step.getParameter())) {
            return false;
        } else {
            step.getParameter().forEach(jsonNode -> {
                jsonNode.fields().forEachRemaining(entry -> {
                    if (ObjectUtils.isEmpty(entry.getKey()) || ObjectUtils.isEmpty(entry.getValue())) {
                        mark.set(false);
                    }
                });
            });
        }
        return mark.get();
    }

    @Override
    public Boolean support(ConditionType conditionType) {
        return ConditionType.Exist.equals(conditionType);
    }


}
