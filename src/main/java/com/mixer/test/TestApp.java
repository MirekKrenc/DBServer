package com.mixer.test;

import com.mixer.raw.FileHandler;

public class TestApp {

    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("dbdata.txt");
            fileHandler.add("Mirek", 30, "Warszawa", "WZ 1234", "This is test data");
            fileHandler.add("Kasia", 25, "Dobron", "EPA 4321", "This is very long description with test test data");
            fileHandler.add("Record 3", 55, "Zdunska Wola", "EZD 9999", "This is another very long description with test test data");
            fileHandler.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
