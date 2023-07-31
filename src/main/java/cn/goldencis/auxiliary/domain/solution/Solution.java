package cn.goldencis.auxiliary.domain.solution;

import cn.goldencis.auxiliary.domain.step.Step;
import cn.goldencis.auxiliary.infrastructure.extract.entity.AuxException;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-21 10:18
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "solution")
public class Solution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String stepId;
    @Transient
    private List<Long> stepIds;

    private Long problemId;
    //是否可以验证
    private Boolean ifVerify;


    @Transient
    private List<Step> steps;

    @Transient
    private AuxException auxException;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Solution init(){
        if (!ObjectUtils.isEmpty(stepId)){
            this.stepIds = Lists.newArrayList(stepId.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        }
        return this;
    }
}
