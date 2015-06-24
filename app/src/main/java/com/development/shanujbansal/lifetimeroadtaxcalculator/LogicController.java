package com.development.shanujbansal.lifetimeroadtaxcalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shanuj.bansal on 5/30/2015.
 */
public class LogicController {
    private static LogicController instance;

    public static LogicController getInstance() {
        if (instance == null) {
            instance = new LogicController();
        }
        return instance;
    }

    // blank constructor.
    public LogicController() {
    }

    public Date isValidDate(String inDate) {
        Date parsedDate;

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat1.setLenient(false);
        try {
            parsedDate = dateFormat1.parse(inDate.trim());
            return parsedDate;
        } catch (ParseException pe) {
        }

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat2.setLenient(false);
        try {
            parsedDate = dateFormat2.parse(inDate.trim());
            return parsedDate;
        } catch (ParseException pe) {
        }

        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat3.setLenient(false);
        try {
            parsedDate = dateFormat3.parse(inDate.trim());
            return parsedDate;
        } catch (ParseException pe) {
        }

        SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat4.setLenient(false);
        try {
            parsedDate = dateFormat4.parse(inDate.trim());
            return parsedDate;
        } catch (ParseException pe) {
        }

        SimpleDateFormat dateFormat5 = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat5.setLenient(false);
        try {
            parsedDate = dateFormat5.parse(inDate.trim());
            return parsedDate;
        } catch (ParseException pe) {
        }

        SimpleDateFormat dateFormat6 = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat6.setLenient(false);
        try {
            parsedDate = dateFormat6.parse(inDate.trim());
            return parsedDate;
        } catch (ParseException pe) {
        }

        return null;
    }

    public double calculateTaxForTwoWheeler(Date purchaseDate, int purchaseAmt, boolean isNewRegistration) {
        double taxableAmt = (double) purchaseAmt;
        int taxRate = 1;

        if (purchaseAmt <= 50000) {
            taxRate = 10;
        } else {
            taxRate = 12;
        }

        if (!isNewRegistration) {
            Date currentDate = new Date();
            long years = (currentDate.getTime() - purchaseDate.getTime()) / (1000L * 60 * 60 * 24 * 365);
            int yearsDiff = (int) years;

            if (yearsDiff < 2)
                taxableAmt = 0.93 * purchaseAmt;
            else if (yearsDiff >= 2 && yearsDiff < 3)
                taxableAmt = 0.87 * purchaseAmt;
            else if (yearsDiff >= 3 && yearsDiff < 4)
                taxableAmt = 0.81 * purchaseAmt;
            else if (yearsDiff >= 4 && yearsDiff < 5)
                taxableAmt = 0.75 * purchaseAmt;
            else if (yearsDiff >= 5 && yearsDiff < 6)
                taxableAmt = 0.69 * purchaseAmt;
            else if (yearsDiff >= 6 && yearsDiff < 7)
                taxableAmt = 0.64 * purchaseAmt;
            else if (yearsDiff >= 7 && yearsDiff < 8)
                taxableAmt = 0.59 * purchaseAmt;
            else if (yearsDiff >= 8 && yearsDiff < 9)
                taxableAmt = 0.54 * purchaseAmt;
            else if (yearsDiff >= 9 && yearsDiff < 10)
                taxableAmt = 0.49 * purchaseAmt;
            else if (yearsDiff >= 10 && yearsDiff < 11)
                taxableAmt = 0.45 * purchaseAmt;
            else if (yearsDiff >= 11 && yearsDiff < 12)
                taxableAmt = 0.41 * purchaseAmt;
            else if (yearsDiff >= 12 && yearsDiff < 13)
                taxableAmt = 0.37 * purchaseAmt;
            else if (yearsDiff >= 13 && yearsDiff < 14)
                taxableAmt = 0.33 * purchaseAmt;
            else if (yearsDiff >= 14 && yearsDiff < 15)
                taxableAmt = 0.29 * purchaseAmt;
            else if (yearsDiff >= 15)
                taxableAmt = 0.25 * purchaseAmt;
        }

        double roadTax = (taxRate * taxableAmt) / 100;
        double eduCess = 0.11 * roadTax;
        double totalAmount = roadTax + eduCess;

        return totalAmount;
    }

    public double calculateTaxForFourWheeler(Date purchaseDate, int purchaseAmt, boolean isNewRegistration) {
        double taxableAmt = (double) purchaseAmt;
        int taxRate = 1;

        if (purchaseAmt <= 500000) {
            taxRate = 13;
        } else if (purchaseAmt > 500000 && purchaseAmt < 1000000) {
            taxRate = 14;
        } else if (purchaseAmt > 1000000 && purchaseAmt < 2000000) {
            taxRate = 17;
        } else if (purchaseAmt > 2000000) {
            taxRate = 18;
        }

        if (!isNewRegistration) {
            Date currentDate = new Date();
            long years = (currentDate.getTime() - purchaseDate.getTime()) / (1000L * 60 * 60 * 24 * 365);
            int yearsDiff = (int) years;

            if (yearsDiff < 2)
                taxableAmt = 0.93 * purchaseAmt;
            else if (yearsDiff >= 2 && yearsDiff < 3)
                taxableAmt = 0.87 * purchaseAmt;
            else if (yearsDiff >= 3 && yearsDiff < 4)
                taxableAmt = 0.81 * purchaseAmt;
            else if (yearsDiff >= 4 && yearsDiff < 5)
                taxableAmt = 0.75 * purchaseAmt;
            else if (yearsDiff >= 5 && yearsDiff < 6)
                taxableAmt = 0.69 * purchaseAmt;
            else if (yearsDiff >= 6 && yearsDiff < 7)
                taxableAmt = 0.64 * purchaseAmt;
            else if (yearsDiff >= 7 && yearsDiff < 8)
                taxableAmt = 0.59 * purchaseAmt;
            else if (yearsDiff >= 8 && yearsDiff < 9)
                taxableAmt = 0.54 * purchaseAmt;
            else if (yearsDiff >= 9 && yearsDiff < 10)
                taxableAmt = 0.49 * purchaseAmt;
            else if (yearsDiff >= 10 && yearsDiff < 11)
                taxableAmt = 0.45 * purchaseAmt;
            else if (yearsDiff >= 11 && yearsDiff < 12)
                taxableAmt = 0.41 * purchaseAmt;
            else if (yearsDiff >= 12 && yearsDiff < 13)
                taxableAmt = 0.37 * purchaseAmt;
            else if (yearsDiff >= 13 && yearsDiff < 14)
                taxableAmt = 0.33 * purchaseAmt;
            else if (yearsDiff >= 14 && yearsDiff < 15)
                taxableAmt = 0.29 * purchaseAmt;
            else if (yearsDiff >= 15)
                taxableAmt = 0.25 * purchaseAmt;
        }

        double roadTax = (taxRate * taxableAmt) / 100;
        double eduCess = 0.11 * roadTax;
        double totalAmount = roadTax + eduCess;

        return totalAmount;
    }
}
