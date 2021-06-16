package com.tw.academy;

import com.tw.banking.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AccountTest {
    @Test
    public void should_deposit_execute_addDeposit(){
        //given
        Clock clock = mock(Clock.class);
        Console console = mock(Console.class);
        SpyTransactionRepository spyTransactionRepository=new SpyTransactionRepository(clock);
        Printer printer = new Printer(console);
        Account account = new Account(spyTransactionRepository,printer);
        int amount=10;
        //when
        account.deposit(amount);
        //then
        assertTrue(spyTransactionRepository.hasAddDeposit);
    }

    @Test
    public void should_withdraw_execute_addWithdraw(){
        //given
        Clock clock = mock(Clock.class);
        Console console = mock(Console.class);
        SpyTransactionRepository spyTransactionRepository=new SpyTransactionRepository(clock);
        Printer printer = new Printer(console);
        Account account = new Account(spyTransactionRepository,printer);
        int amount=10;
        //when
        account.withdraw(amount);
        //then
        assertTrue(spyTransactionRepository.hasAddWithdraw);
    }



    private class SpyTransactionRepository extends TransactionRepository {
        boolean hasAddDeposit=false;
        boolean hasAddWithdraw=false;

        public SpyTransactionRepository(Clock clock) {
            super(clock);
        }

        @Override
        public void addDeposit(int amount) {
            hasAddDeposit=true;
        }

        @Override
        public void addWithdraw(int amount) {
            hasAddWithdraw=true;
        }
    }
}
