package cn.goldencis.auxiliary.domain.problem;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

    private String problemType;

    private String describe;

    private String keyWord;
}
