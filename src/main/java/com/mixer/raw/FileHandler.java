package com.mixer.raw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileHandler {

    private RandomAccessFile dbFile;

    public FileHandler(final String dbFileName) throws FileNotFoundException {
        this.dbFile = new RandomAccessFile(dbFileName, "rw");
    }

    public void close() throws IOException {
        this.dbFile.close();
    }

    public boolean add(String name,
                       int age,
                       String address,
                       String cardPlate,
                       String description) throws IOException {
        /*
        the structure of record:
        isDeleted:  1 byte
        record length: 4 bytes (int)
        name length: 4 bytes (int)
        name: ?
        age        : 4 bytes (int)
        address length: 4 bytes (int)
        address: ?
        cardPlate length: 4 bytes (int)
        cardPlate: ?
        description length: 4 bytes (int)
        description: ?
         */
        //seek to last position
        this.dbFile.seek(this.dbFile.length());
        //summed length of record without the first boolean
        int length =
                4 + //record length
                4 + //name length
                name.length() +
                4 + //age
                4 + //address length
                address.length() +
                4 + //cardPlate length
                cardPlate.length() +
                4 + //description length
                description.length();
        //saving data

        //is deleted - false
        this.dbFile.writeBoolean(false);
        //record length
        this.dbFile.writeInt(length);
        //name
        this.dbFile.writeInt(name.length());
        this.dbFile.write(name.getBytes());
        //age
        this.dbFile.writeInt(age);
        //address
        this.dbFile.writeInt(address.length());
        this.dbFile.write(address.getBytes());
        //card plate
        this.dbFile.writeInt(cardPlate.length());
        this.dbFile.write(cardPlate.getBytes());
        //description
        this.dbFile.writeInt(description.length());
        this.dbFile.write(description.getBytes());

        return true;
    }
}
