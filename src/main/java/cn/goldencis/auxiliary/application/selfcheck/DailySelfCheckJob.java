package cn.goldencis.auxiliary.application.selfcheck;

import cn.goldencis.auxiliary.application.errorfix.ErrorFixService;
import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.problem.service.ProblemService;
import cn.goldencis.auxiliary.infrastructure.common.SpringUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-07-31 15:41
 **/
@DisallowConcurrentExecution
public class DailySelfCheckJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ProblemService problemService = SpringUtil.getBean("problemServiceImpl");
        List<Problem> problems = problemService.finSelfCheckProblem();
        ErrorFixService errorFixService = SpringUtil.getBean("errorFixService");
        problems.forEach(errorFixService::fix);
    }
}
