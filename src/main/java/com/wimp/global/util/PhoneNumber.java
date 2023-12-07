package com.wimp.global.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumber {
    public static String convertI18nPhoneNumber(String phoneNumber, String countryCode) {
        if (countryCode == null || countryCode.isEmpty()) {
            countryCode = "KR";
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber1 = phoneNumberUtil.parse(phoneNumber, countryCode);
            phoneNumber = phoneNumberUtil.format(phoneNumber1, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
        } catch (NumberParseException e) {
            return null;
        }
        return phoneNumber;
    }
}
