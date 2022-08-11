package com.example.hw_institutionCatalog.util;

import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    @Test
    void reformatRuTelephoneRemoveWhiteSpaces() throws NumberParseException {
        String removeWhiteSpaces = Util.reformatRuTelephone("+7(999)222-33-11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+7-999-222-33-11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+7(999)222-33-11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+7(999)    222 - 33  -  11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+78008888888");
        assertEquals("+78008888888", removeWhiteSpaces);
    }

    @Test
    void reformatRuTelephoneRemoveNotRu() {
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+9(999)222-33-11"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+7(899)222-33-11"));
    }

    @Test
    void reformatRuTelephoneRemoveNotTelephone() {
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+cdvbgfcdxdsaxd"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+7(ddddd899)222-33-11"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+7000000899)2-0-----0999922-33-11"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+70000008900000000000000000000"));
    }

    @Test
    void reformatEmail(){
        String email = Util.reformatEmailAddress("jNk25@googLe.com");
        assertEquals("jnk25@google.com", email);
    }

    @Test
    void correctedEmail(){
        assertFalse(Util.validateEmail("gjkklll"));
        assertFalse(Util.validateEmail("dffg@google.com"));
    }

    @Test
    public void ValidEmailTest() {
        String[] email = new String[] {
                "alex@yandex.ru",
                "alex-27@yandex.com",
                "alex.27@yandex.com",
                "alex111@devcolibri.com",
                "alex.100@devcolibri.com.ua",
                "alex@1.com",
                "alex@gmail.com.com",
                "alex+27@gmail.com",
                "alex-27@yandex-test.com"
        };
        for (String temp : email) {
            boolean valid = Util.validateEmail(temp);
            System.out.println("Email: " + temp + " -> " + valid);
            assertTrue(valid);
        }
    }

    @Test
    public void InValidEmailTest() {
        String[] email = new String[] {
                "devcolibri",
                "alex@.com.ua",
                "alex123@gmail.a",
                "alex123@.com",
                "alex123@.com.com",
                ".alex@devcolibri.com",
                "alex()*@gmail.com",
                "alex@%*.com",
                "alex..2013@gmail.com",
                "alex.@gmail.com",
                "alex@devcolibri@gmail.com",
                "alex@gmail.com.1ua"
        };
        for (String temp : email) {
            boolean valid = Util.validateEmail(temp);
            System.out.println("Email: " + temp + " -> " + valid);
            assertFalse(valid);
        }
    }
}
