package com.acme.banking.dbo.ooad.service;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;

//Requirements for Spring
//1. Lifecycle management
//1.1 Scope: singleton vs prototype vs request (mvc only)
public class ReportingService {
    //Creator [GRASP]
    //Factory Method [GoF]
    //Abstract Factory [GoF]
    //Registry [PoEAA]
    //Field DI
    private AccountRepository accounts;

    //Constructor DI
    public ReportingService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    //Setter DI
    public void setAccounts(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public String reportForAccount(long id) {
        Account account = accounts.findById(id);
        return "## " + account.getId() + " : " + account.getAmount();
    }
}
