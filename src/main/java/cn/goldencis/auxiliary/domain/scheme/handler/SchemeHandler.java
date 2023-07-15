package cn.goldencis.auxiliary.domain.scheme.handler;

import cn.goldencis.auxiliary.domain.scheme.Scheme;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.condition.Discriminator;
import cn.goldencis.auxiliary.infrastructure.execution.ExecDispatcher;
import cn.goldencis.auxiliary.infrastructure.execution.entity.ExecResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SchemeHandler {
    @Autowired
    ExecDispatcher dispatcher;
    @Autowired
    Discriminator discriminator;

    public void ImplementScheme(Scheme scheme) {
        scheme.init();
        log.info("\n\n");
        log.info("【问题描述】{}", scheme.getProblem().getDescribe());
        Map<Solution, Step> solutions = scheme.getSteps();
        for (Solution solution : solutions.keySet()) {
            log.info("【执行方案】：{}", solution.getName());
            boolean result = execStepOfSolution(solutions.get(solution), null);
            if (result) {
                break;
            }
        }
        log.info("\n\n");
    }

    public boolean execStepOfSolution(Step step, String paramStr) {
        log.info("--【执行步骤】：{}", step.getStepName());
        List<JsonNode> params = this.params(paramStr, step.getParameterType());
        step.initExcContent(params).initExcCondition(params).setParameter(params);
        //先判断是否满足执行条件
        if (!discriminator.distinguish(step)) {
            return false;
        }
        ExecResult execResult = dispatcher.doDispatch(step);
        if (execResult.getCode() == 1) {
            return false;
        } else {
            execStep(step.getChildStep(), String.valueOf(execResult.getMessage()));
        }
        return true;
    }

    private boolean execStep(List<Step> steps, String paramStr) {
        for (Step step : steps) {
            List<JsonNode> params = this.params(paramStr, step.getParameterType());
            step.initExcContent(params).initExcCondition(params).setParameter(params);
            //先判断是否满足执行条件
            if (!discriminator.distinguish(step)) {
                continue;
            }
            log.info("--【执行步骤】：{}", step.getStepName());
            ExecResult execResult = dispatcher.doDispatch(step);
            if (execResult.getCode() == 0) {
                return execStep(step.getChildStep(), String.valueOf(execResult.getMessage()));
            }
        }
        return false;
    }

    public List<JsonNode> params(String paramStr, String type) {
        if (ObjectUtils.isEmpty(paramStr) && ObjectUtils.isEmpty(type)) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch (ObjectUtils.isEmpty(type) ? "" : type) {
                case "List<JsonNode>":
                    return objectMapper.readValue(paramStr, new TypeReference<List<JsonNode>>() {
                    });
                case "JsonNode":
                    return Collections.singletonList(objectMapper.readValue(paramStr, new TypeReference<JsonNode>() {
                    }));
                default:
                    return null;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
