package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AccountDto;
import com.company.projects.course.coursemanagementsystem.mapper.AccountMapper;
import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import com.company.projects.course.coursemanagementsystem.repository.AccountRepository;
import com.company.projects.course.coursemanagementsystem.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<String, AccountDto, AccountEntity> implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final EmailService emailService;

    @Autowired
    public AccountServiceImpl(AccountRepository repository, AccountMapper mapper, EmailService emailService) {
        super(repository, mapper, "Assignment");
        this.accountRepository = repository;
        this.accountMapper = mapper;
        this.emailService = emailService;
    }

    @Override
    public AccountDto save(AccountDto dto) {
        AccountEntity entity = accountMapper.toEntity(dto);
        assert entity != null;
        entity.setPassword(PasswordUtil.generatePassword());
        entity.setUsername(dto.getUser().getEmail());

        AccountEntity savedAccount = accountRepository.save(entity);

        try {
            emailService.sendCredentials(dto.getUser().getEmail(), dto.getUsername(), savedAccount.getPassword(), dto.getUser().getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto update(String id, AccountDto dto) {
        return null;
    }
}
