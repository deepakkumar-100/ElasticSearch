package com.india.elastic.elasticsearchdemo.jparepository;

import com.india.elastic.elasticsearchdemo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UsersJpaRepository extends JpaRepository<Users,Long> {
}
