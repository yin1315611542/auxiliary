package cn.goldencis.auxiliary.domain.problem.repository;

import cn.goldencis.auxiliary.domain.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:33
 **/
@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long>, QuerydslPredicateExecutor<Problem> {
}
