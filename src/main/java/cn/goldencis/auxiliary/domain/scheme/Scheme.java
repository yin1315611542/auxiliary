package cn.goldencis.auxiliary.domain.scheme;

import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.solution.Solution;
import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Scheme {

    private AuxException auxException;

    private Problem problem;

    private Map<Solution,List<Step>> solutions;

    private Map<Solution, Step> steps = new HashMap<>();

    public Scheme(Problem problem, Map<Solution,List<Step>> solutions) {
        this.problem = problem;
        this.solutions = solutions;
    }

    public Scheme(AuxException auxException, Problem problem, Map<Solution, List<Step>> solutions) {
        this.auxException = auxException;
        this.problem = problem;
        this.solutions = solutions;
    }


    //步骤整理
    public Scheme init() {
        if (!ObjectUtils.isEmpty(solutions)) {
            for (Solution solution : solutions.keySet()) {
                Map<Long, Step> stepMap = solutions.get(solution).stream().collect(Collectors.toMap(Step::getId, Function.identity()));
                List<Step> collect = solutions.get(solution).stream().sorted(Comparator.comparing(Step::getLevel).reversed()).collect(Collectors.toList());
                for (Step step : collect) {
                    if (!ObjectUtils.isEmpty(step.getParentId())) {
                        stepMap.get(step.getParentId()).getChildStep().add(stepMap.get(step.getId()));
                    }
                }
                this.steps.put(solution, stepMap.get(collect.stream().min(Comparator.comparing(Step::getLevel)).orElseThrow(() -> new RuntimeException("步骤划分关系错误")).getId()));
            }
        }
        return this;
    }
}
