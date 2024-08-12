package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AccountDto;
import com.company.projects.course.coursemanagementsystem.mapper.AccountMapper;
import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import com.company.projects.course.coursemanagementsystem.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<String, AccountDto, AccountEntity> implements AccountService {
    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper) {
        super(repository, mapper, "Account");
    }
}
