package cn.goldencis.auxiliary.domain.scheme;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.extract.entity.MyException;
import cn.goldencis.auxiliary.domain.problem.Problem;
import cn.goldencis.auxiliary.domain.solution.Solution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

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

    private MyException myException;

    private Problem problem;

    private Map<Solution,List<Step>> solutions;

    public Scheme(Problem problem, Map<Solution,List<Step>> solutions) {
        this.problem = problem;
        this.solutions = solutions;
    }
}
