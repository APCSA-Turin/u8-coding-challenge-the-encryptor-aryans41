package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen == 0) {
            return 1;
        }
        double divided = (double) messageLen / rows;
        int columns = messageLen / rows; 
        if (divided % 1 != 0) {
            columns++;
        }
        return columns;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int columns = determineColumns(message.length(), rows);
        String[][] encryptedArray = new String [rows][columns];
        int index = 0;
        for (int row = 0; row < encryptedArray.length; row++) {
            for (int column = 0; column < columns; column++) {
                if (index > message.length() - 1) {
                    encryptedArray[row][column] = "=";
                    index++;
                } else {
                    encryptedArray[row][column] = message.substring(index, index + 1);
                    index++;
                }
            }
        }
        return encryptedArray;
    }

    public static String encryptMessage(String message, int rows){
        String encryptedMessage = ""; 
        String[][] encryptedMessageArray = generateEncryptArray(message, rows);
        for (int column = encryptedMessageArray[0].length - 1; column >= 0; column--) {
            for (int row = 0; row < encryptedMessageArray.length; row++) {
                encryptedMessage += encryptedMessageArray[row][column];
            }
        }
        return encryptedMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String decryption = "";
        int columns = determineColumns(encryptedMessage.length(), rows);
        int index = 0;
        String[][] encryptArray = new String[rows][columns];
        if (rows * columns < encryptedMessage.length()) {
            return "";
        }
        for (int column = columns - 1; column >= 0; column--) {
                for (int row = 0; row < rows; row++) {
                    if (index < encryptedMessage.length()) {
                        encryptArray[row][column] = encryptedMessage.substring(index, index + 1); 
                        index++;
                    }
                }
        }
        for (int row = 0; row < encryptArray.length; row++) {
            for (int column = 0; column < encryptArray[0].length; column++) {
               if (encryptArray[row][column] != null && encryptArray[row][column].equals("=") == false) {
                    decryption += encryptArray[row][column];
                 }
            }
        }
        return decryption;
    }
}