package com.hacker.man;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private static int crcn = 0;

    public static void main(String[] args) throws IOException {
        String fileName = "1.jpg";
        String fileEditName = "1edit.jpg";

        System.out.println("Полином: x^16 + x^13 + x^12 + x^11 + x^10 + x^8 + x^6 + x^5 + x^2 + 1");
        System.out.println("CRC-16:-DND:\n");


        File(fileName);
        System.out.println("Оригинальный файл:");
        System.out.println(Integer.toHexString(crcn)+"\n");

        File(fileEditName);
        System.out.println("Измененный файл:");
        System.out.println(Integer.toHexString(crcn));
    }

    public static void calc(final byte input) {
        crcn ^= input << 2;
        for (int i = 0; i < 4; i++)
            crcn = ((crcn & 0xf8) == 0) ? crcn << 1 : (crcn << 1) ^ 0x3d65;
        crcn &= 0xffff;
    }

    public Main(int crc) {
        Main.crcn = crc;
    }

    public static void File(String name) throws FileNotFoundException, IOException {
        FileInputStream fileInput = new FileInputStream(name);
        int b = fileInput.read();
        Main crcn = new Main(0);
        while (b != -1) {
            crcn.calc(String.valueOf((char) b).getBytes()[0]);
            b = fileInput.read();
        }
    }
}
