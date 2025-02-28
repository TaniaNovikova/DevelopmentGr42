package de.ait.javalessons.lesson_06.code;

public class ByteStreamWriter {
    public static void main(String[] args) {
        String strToWrite = "Hello";

        try {
            java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream("output.bin");
            byte[] bytes = strToWrite.getBytes();
            fileOutputStream.write(bytes);
            System.out.println("File written successfully!");
            fileOutputStream.close();
        }
        catch (java.io.FileNotFoundException exception){
            System.out.println("File not found!");
        }
        catch (java.io.IOException exception){
            System.out.println("Error while writing to file!");
        }
    }
}
