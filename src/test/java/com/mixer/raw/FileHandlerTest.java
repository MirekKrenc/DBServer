package com.mixer.raw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class FileHandlerTest {

    static FileHandler fileHandler;

    @BeforeAll
    static void setup() {
        try {
            fileHandler = new FileHandler("dbdata.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void read() throws IOException {

        FileHandler fileHandler = new FileHandler("dbdata.txt");
        Person person = fileHandler.read(15);
        System.out.println(person);
        fileHandler.close();
    }


    @Test
    @Order(1)
    void add() throws IOException {


        fileHandler.add("Mirek", 30, "Warszawa", "WZ 1234", "This is test data");
        fileHandler.add("Kasia", 25, "Dobron", "EPA 4321", "This is very long description with test test data");
        fileHandler.add("Record 3", 55, "Zdunska Wola", "EZD 9999", "This is another very long description with test test data");
        fileHandler.close();
        //assertEquals(3, Index.getInstance().getTotalNumberOfRows());

    }

}