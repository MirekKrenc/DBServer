package com.mixer.raw;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    private RandomAccessFile dbFile;
    private final File fileDbFile;

    public FileHandler(final String dbFileName) throws FileNotFoundException {
        this.dbFile = new RandomAccessFile(dbFileName, "rw");
        this.fileDbFile = new File(dbFileName);
    }

    public void close() throws IOException {
        this.dbFile.close();
    }

    public Person read(int rowNumber) throws IOException {
        byte[] raw = readRawBytes(rowNumber);
        Person person = new Person();
        DataInputStream dataInputStream =
                new DataInputStream(new ByteArrayInputStream(raw));
        int len = dataInputStream.readInt();
        byte[] b = new byte[len];
        dataInputStream.read(b);
        person.name = new String(b);

        /*
        int age,
                       String address,
                       String cardPlate,
                       String description
         */

        int age = dataInputStream.readInt();
        person.age = age;

        //address
        len = dataInputStream.readInt();
        b = new byte[len];
        dataInputStream.read(b);
        person.address = new String(b);

        //card plate
        len = dataInputStream.readInt();
        b = new byte[len];
        dataInputStream.read(b);
        person.cardPlate = new String(b);

        //description
        len = dataInputStream.readInt();
        b = new byte[len];
        dataInputStream.read(b);
        person.description = new String(b);

        return person;
    }

    private byte[] readRawBytes(int rowNumber) throws IOException {
        //TODO - add position calculation for row number
        long position = 0L;
        this.dbFile.seek(position);
        //read if record is deleted
        Boolean isDeleted = this.dbFile.readBoolean();
        if (isDeleted)
            return new byte[0];

        this.dbFile.seek(position + 1);
        int recordLength = this.dbFile.readInt();
        this.dbFile.seek(position + 1 + 4);
        byte[] recordBytes = new byte[recordLength];
        this.dbFile.read(recordBytes);

        return recordBytes;
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

    public void delete() {
        this.fileDbFile.deleteOnExit();
    }
}
