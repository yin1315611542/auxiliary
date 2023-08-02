package cn.goldencis.auxiliary.domain.problem.enumtype;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-07-31 15:45
 **/
public enum ProblemType {
    OrdinaryProblem(1,"普通问题"),
    InteractionProblem(2,"交互类问题"),
    SelfCheckProblem(3,"自检类问题");


    private Integer code;
    private String name;

    ProblemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

}
