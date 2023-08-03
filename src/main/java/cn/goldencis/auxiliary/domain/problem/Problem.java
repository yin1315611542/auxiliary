package cn.goldencis.auxiliary.domain.problem;

import cn.goldencis.auxiliary.domain.problem.enumtype.ProblemType;
import cn.goldencis.auxiliary.domain.problem.enumtype.ProblemTypeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Convert;
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
 * @create: 2023-06-19 17:45
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "problem")
public class Problem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ProblemTypeConverter.class)
    private ProblemType problemType;

    private String causeChain;

    private String describe;

    private String keyWord;

    private Integer  status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
