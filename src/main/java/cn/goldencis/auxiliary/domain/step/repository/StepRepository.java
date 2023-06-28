package cn.goldencis.auxiliary.domain.step.repository;

import cn.goldencis.auxiliary.domain.step.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: auxiliary
 * @description:
 * @Author: yinhd
 * @create: 2023-06-27 15:54
 **/
@Repository
public interface StepRepository extends JpaRepository<Step,Long>, QuerydslPredicateExecutor<Step> {
}
