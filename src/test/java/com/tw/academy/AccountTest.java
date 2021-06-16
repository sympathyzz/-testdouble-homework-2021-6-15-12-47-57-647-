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

    private class SpyTransactionRepository extends TransactionRepository {
        boolean hasAddDeposit=false;

        public SpyTransactionRepository(Clock clock) {
            super(clock);
        }

        @Override
        public void addDeposit(int amount) {
            hasAddDeposit=true;
        }
    }
}
