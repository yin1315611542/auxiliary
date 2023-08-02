package cn.goldencis.auxiliary.domain.step.service;

import cn.goldencis.auxiliary.domain.step.Step;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-27 15:53
 **/
public interface StepService {
    List<Step> finStepByIds(List<Long> ids);

    List<Step> finStepBySolutionId(Long solutionId);
}
