package cn.goldencis.auxiliary.domain.deploy.repository;

import cn.goldencis.auxiliary.domain.deploy.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRepository extends JpaRepository<Hub, Long>, QuerydslPredicateExecutor<Hub> {
}
