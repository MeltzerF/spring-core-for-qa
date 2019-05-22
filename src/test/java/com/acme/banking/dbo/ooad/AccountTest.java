package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.domain.CheckingAccount;
import com.acme.banking.dbo.ooad.domain.SavingAccount;
import com.acme.banking.dbo.ooad.service.TransferService;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Evgeniy Slobozheniuk on 2019-05-20.
 */
public class AccountTest {
    @Test
    public void shouldCheckingAccountTransferWhenWithinOverdraft() {
        //region Given
        CheckingAccount sut = new CheckingAccount(0L, 100, 50);
        Account toAccount = mock(Account.class);
        TransferService transferService = new TransferService();
        //endregion
        //region When
        transferService.transfer(sut, toAccount, 120);
        //endregion
        //region Then
        assertEquals(0D, sut.getAmount());
        verify(toAccount).deposit(120);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldCheckingAccountThrowExceptionWhenTransferWithOverdraft() {
        //region Given
        CheckingAccount sut = new CheckingAccount(0L, 100, 50);
        Account toAccount = mock(Account.class);
        TransferService transferService = new TransferService();
        //endregion
        //region When
        transferService.transfer(sut, toAccount, 160);
        //endregion
    }

    @Test
    public void shouldSavingAccountWithdrawWithinAmount() {
        //region Given
        SavingAccount sut = new SavingAccount(0L, 100);
        //endregion
        //region When
        sut.withdraw(80D);
        //endregion
        //region Then
        assertEquals(20D, sut.getAmount());
    }
}
