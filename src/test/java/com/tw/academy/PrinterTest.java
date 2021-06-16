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
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setup() {
        System.setOut(new PrintStream(outContent));
    }

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

    @Test
    public void should_contain_STATEMENT_HEADER_execute_print(){
        //given
        Console console = new Console();
        Printer printer= new Printer(console);
        String date="2021-06-16";
        int amount=10;
        Transaction transaction = new Transaction(date,amount);
        //when
        printer.print(Arrays.asList(transaction));
        //then
        assertThat(systemOut().contains(Printer.STATEMENT_HEADER)).isTrue();
    }

    @Test
    public void should_print_execute_printStatementLines(){
        //given
        Console console = new Console();
        Printer printer= new Printer(console);
        String date="2021-06-16";
        int amount=10;
        String output="2021-06-16 | 10 | 10";
        Transaction transaction = new Transaction(date,amount);
        //when
        printer.print(Arrays.asList(transaction));
        //then
        assertThat(systemOut().contains(output)).isTrue();
    }


    private class SpyConsole extends Console{
        boolean hasPrintLine=false;

        @Override
        public void printLine(String line) {
            hasPrintLine=true;
        }
    }

    private String systemOut() {
        return outContent.toString();
    }
}
