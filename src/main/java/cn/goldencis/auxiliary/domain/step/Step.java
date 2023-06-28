package cn.goldencis.auxiliary.domain.step;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

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
}
