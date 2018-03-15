package com.example.shehnepours.taxam.utils;

/**
 * Created by shehnepour.s on 1/15/2018.
 */

public class En2FaNumber {

    public static String En2FaChanger(String number) {

        switch (number) {
            case "1":
                return "۱";
            case "2":
                return "۲";
            case "3":
                return "۳";
            case "4":
                return "۴";
            case "5":
                return "۵";
            case "6":
                return "۶";
            case "7":
                return "۷";
            case "8":
                return "۸";
            case "9":
                return "۹";
            case "0":
                return "۰";
            default:
                return number;
        }
    }
}
