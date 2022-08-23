package com.example.hw_institutionCatalog.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);
    private final static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private final static String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static Matcher matcher;

    public static String reformatRuTelephone(String phone) throws NumberParseException {
        Phonenumber.PhoneNumber phoneNumber =
                phoneNumberUtil.parse(phone, Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        boolean validNumber = phoneNumberUtil.isValidNumberForRegion(phoneNumber, "RU");
        if(!validNumber) {
            throw new IllegalArgumentException("number is not russian");
        }
        return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static boolean checkRuTelephone(String cxt) {
        try {
            reformatRuTelephone(cxt);
            return true;
        } catch (NumberParseException e) {
            return false;
        }
    }

    public static boolean validateEmail(final String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String reformatEmailAddress(String email){
        email = email.toLowerCase(Locale.ROOT);
        try {
           validateEmail(email);
        } catch (Exception e){
            log.debug("email address not correct");
        }
        return email;
    }
}
