package utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;


public class CryptUtil {

    private static final String ALGORITHM = "AES";

    public static String encrypt(final String valueEnc, String keyPath) {

        String encryptedVal = null;

        try {
            final Key key = generateKey(keyPath);
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
            final byte[] encValue = c.doFinal(valueEnc.getBytes());
            encryptedVal = new BASE64Encoder().encode(encValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encryptedVal;
    }

    public static String decrypt(String encryptedValue, String keyPath) {

        String decryptedValue = null;

        try {
            ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(new File(keyPath)));
            Key key = (Key) fileReader.readObject();

            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            final byte[] decorVal = new BASE64Decoder().decodeBuffer(encryptedValue);
            final byte[] decValue = c.doFinal(decorVal);
            decryptedValue = new String(decValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return decryptedValue;
    }

    private static Key generateKey(String pathName) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(new File(pathName)));
        fileWriter.writeObject(key);
        return key;
    }

}