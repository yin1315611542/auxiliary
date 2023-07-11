package cn.goldencis.auxiliary.domain.step;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 16:38
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "step")
public class Step implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String StepName;

    private String StepType;

    private String excContent;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long level;

    private Long parentId;

    private Long solutionId;

    private String execResult;

    @Transient
    private List<Step> childStep = new ArrayList<>();
}
