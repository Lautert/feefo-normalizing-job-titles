package com.feefo.normalizingjob.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.feefo.normalizingjob.application.SpringWebApplication;
import com.feefo.normalizingjob.exception.MessageUserException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringWebApplication.class)
public class NormalizeServiceTests
{

    @Test
    public void returnSoftwareEngineerGivenJava () {

        String search = "java";
        String expected = "Software engineer";
        String actual = new NormalizeService().normalize(search);

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void returnSoftwareEngineerGivenC () {

        String search = "C";
        String expected = "Software engineer";
        String actual = new NormalizeService().normalize(search);

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void returnSoftwareEngineerGivenHash () {

        String search = "#";
        String expected = "Software engineer";
        String actual = new NormalizeService().normalize(search);

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void returnAccountantGivenCC () {

        String search = "cc";
        String expected = "Accountant";
        String actual = new NormalizeService().normalize(search);

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void returnChiefAccountantGivenChi () {

        String search = "Chi";
        String expected = "Accountant";
        String actual = new NormalizeService().normalize(search);

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void returnEmptyGivenBigWord () {

        String search = "Incomprehensibility";
        String expected = "";
        String actual = new NormalizeService().normalize(search);

        assertEquals(
            expected,
            actual
        );
    }

    @Test
    public void returnExceptionGivenSpaceString () {
        Exception exception = assertThrows(
            MessageUserException.class,
            () -> {
                String search = "   ";
                String expected = "";
                String actual = new NormalizeService().normalize(search);
            }
        );

        String expectedMessage = "The search parameter is required";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void returnExceptionGivenEmpty () {
        Exception exception = assertThrows(
            MessageUserException.class,
            () -> {
                String search = "";
                String expected = "Accountant";
                String actual = new NormalizeService().normalize(search);
            }
        );

        String expectedMessage = "The search parameter is required";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
