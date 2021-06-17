package com.tw.academy;

import com.tw.banking.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class TransactionRepositoryTest {
    @Test
    public void should_addDeposit_execute_todayAsString(){
        //given
        SpyClock spyClock = new SpyClock();
        int amount=10;
        TransactionRepository transactionRepository=new TransactionRepository(spyClock);
        //when
        transactionRepository.addDeposit(amount);
        //then
        assertTrue(spyClock.hasExecuteTodayAsString);
    }

    private class SpyClock extends Clock{
        boolean hasExecuteTodayAsString=false;

        @Override
        public String todayAsString() {
            hasExecuteTodayAsString=true;
            return "";
        }
    }
}
