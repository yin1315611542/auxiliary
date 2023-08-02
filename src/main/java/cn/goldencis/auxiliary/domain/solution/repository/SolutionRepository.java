package cn.goldencis.auxiliary.domain.solution.repository;

import cn.goldencis.auxiliary.domain.solution.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-25 17:42
 **/
@Repository
public interface SolutionRepository extends JpaRepository<Solution,Long>, QuerydslPredicateExecutor<Solution> {
}
