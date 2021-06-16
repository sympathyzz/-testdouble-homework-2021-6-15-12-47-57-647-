package com.tw.academy;

import com.tw.banking.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PrinterTest {


    @Test
    public void should_print_execute_printLine(){
        //given
        SpyConsole spyConsole = new SpyConsole();
        Printer printer= new Printer(spyConsole);
        String date="2021-06-16";
        int amount=10;
        Transaction transaction = new Transaction(date,amount);
        //when
        printer.print(Arrays.asList(transaction));
        //then
        assertTrue(spyConsole.hasPrintLine);
    }


    private class SpyConsole extends Console{
        boolean hasPrintLine=false;

        @Override
        public void printLine(String line) {
            hasPrintLine=true;
        }
    }


}
