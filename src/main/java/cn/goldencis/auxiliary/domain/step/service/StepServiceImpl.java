package cn.goldencis.auxiliary.domain.step.service;

import cn.goldencis.auxiliary.domain.step.QStep;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.domain.step.repository.StepRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-27 15:53
 **/
@Service
public class StepServiceImpl implements StepService{
    private QStep step = QStep.step;

    @Autowired
    private StepRepository stepRepository;
    @Override
    public List<Step> finStepByIds(List<Long> ids) {
        return Lists.newArrayList(stepRepository.findAll(step.id.in(ids)));
    }

    @Override
    public List<Step> finStepBySolutionId(Long solutionId) {
        return Lists.newArrayList(stepRepository.findAll(step.solutionId.eq(solutionId)));
    }


}
