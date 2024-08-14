package com.company.projects.course.coursemanagementsystem.repository;

import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<AccountEntity, String> {
    boolean existsByUsername(String username);
    boolean existsByUsernameIgnoreCase(String username);
}

