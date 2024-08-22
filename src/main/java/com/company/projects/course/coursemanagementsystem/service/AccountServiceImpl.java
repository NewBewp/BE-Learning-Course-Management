package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.AccountDto;
import com.company.projects.course.coursemanagementsystem.mapper.AccountMapper;
import com.company.projects.course.coursemanagementsystem.model.AccountEntity;
import com.company.projects.course.coursemanagementsystem.repository.AccountRepository;
import com.company.projects.course.coursemanagementsystem.util.Base64Util;
import com.company.projects.course.coursemanagementsystem.util.PasswordUtil;
import com.company.projects.course.coursemanagementsystem.util.SHAUtil;
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
        entity.setUsername(dto.getUser().getEmail());
        byte[] salt = SHAUtil.generateSalt();
        String passwordGenerated = PasswordUtil.generatePassword();
        try {
            String password = SHAUtil.hashPassword(passwordGenerated, salt);
            entity.setPassword(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        entity.setSalt(Base64Util.encode(salt));

        AccountEntity savedAccount = accountRepository.save(entity);

        try {
            emailService.sendCredentials(savedAccount.getUser().getEmail(), savedAccount.getUsername(), passwordGenerated, savedAccount.getUser().getName());
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
