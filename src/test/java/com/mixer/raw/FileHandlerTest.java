package com.mixer.raw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class FileHandlerTest {


    @Test
    void read2() throws IOException {

        FileHandler fileHandler = new FileHandler("dbdata.txt");
        Person person = fileHandler.read(0);
        System.out.println(person);
        fileHandler.close();
    }


    @Test
    void add() throws IOException {

        FileHandler fileHandler = new FileHandler("dbdata.txt");
        fileHandler.add("Mirek", 30, "Warszawa", "WZ 1234", "This is test data");
        fileHandler.add("Kasia", 25, "Dobron", "EPA 4321", "This is very long description with test test data");
        fileHandler.add("Record 3", 55, "Zdunska Wola", "EZD 9999", "This is another very long description with test test data");
        fileHandler.close();


    }
}