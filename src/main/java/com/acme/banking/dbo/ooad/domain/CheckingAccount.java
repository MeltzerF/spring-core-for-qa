package com.acme.banking.dbo.ooad.domain;

/**
 * Inheritance as Code Reuse
 */
public class CheckingAccount extends SavingAccount {
    private double overdraft;

    public CheckingAccount(long id, double amount, double overdraft) {
        super(id, amount);
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getAmount()) {
            if (amount < getAmount() + overdraft) {
                double amountToOverdraft = amount - getAmount();
                overdraft -= amountToOverdraft;
                amount -= amountToOverdraft;
            }
        }
        super.withdraw(amount);
    }
}
