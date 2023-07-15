package cn.goldencis.auxiliary.infrastructure.condition;

import cn.goldencis.auxiliary.domain.step.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class Discriminator {
    @Autowired
    List<Distinguish> distinguishList;

    public Boolean distinguish(Step step) {
        if (ObjectUtils.isEmpty(step.getConditionType())) {
            //此处有些特殊，如果判断条件类型为空的话，默认此命令不用参与进行条件执行的判别，直接执行此命令即可；
            return true;
        }
        for (Distinguish distinguish : distinguishList) {
            if (distinguish.support(step.getConditionType())) {
                return distinguish.distinguished(step);
            }
        }
        return false;
    }

}
