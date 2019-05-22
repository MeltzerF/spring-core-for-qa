package com.acme.banking.dbo.legacy.service;

import com.acme.banking.dbo.legacy.domain.Account;
import org.junit.Before;
import org.junit.Test;

import static com.acme.banking.dbo.legacy.domain.AccountType.CHECKING;
import static com.acme.banking.dbo.legacy.domain.AccountType.SAVING;
import static org.fest.assertions.api.Assertions.assertThat;

public class ReportingTest {
    @Before
    public void resetAccounts() {
        AccountRepository.reset();
    }

    @Test
    public void shouldGetReportWhenSavingAccount() {
        Account savingAccount = new Account(SAVING, 100, 0);
        AccountRepository.save(savingAccount);

        String savingAccountReport = ReportingService.reportForAccount(1L);

        assertThat(savingAccountReport).isEqualTo("## 1 100.0 S");
    }

    @Test
    public void shouldGetReportWhenCheckingAccount() {
        Account savingAccount = new Account(CHECKING, 100, 100);
        AccountRepository.save(savingAccount);

        String savingAccountReport = ReportingService.reportForAccount(1L);

        assertThat(savingAccountReport).isEqualTo("## 1 100.0 C 100.0");
    }
}
