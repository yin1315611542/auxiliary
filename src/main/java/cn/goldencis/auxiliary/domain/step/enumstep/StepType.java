package cn.goldencis.auxiliary.domain.step.enumstep;

public enum StepType {
    Command(1,"命令"),
    Script(2,"脚本");

    private Integer code;
    private String name;

    StepType(Integer code, String name) {
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
