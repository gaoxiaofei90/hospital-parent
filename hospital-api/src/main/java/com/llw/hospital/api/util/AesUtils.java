/**
 * Project Name:banking
 * Date:2017-07-07 15:29
 * Copyright (c) 2017, AEYE All Rights Reserved.
 */
package com.llw.hospital.api.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;

import static com.llw.hospital.api.util.Base64Utils.*;

/**
 * Function: ADD FUNCTION. <br/>
 * Date: 2017-07-07 15:29 <br/>
 *
 * @author duanxuhua
 * @version 1.0
 */
public class AesUtils {

    public static final String VIPARA = "0102030405060708";
    public static final String BM = "UTF-8";

    public static String encrypt(String dataPassword, String cleartext)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(cleartext.getBytes(BM));

        return encode(encryptedData);
    }

    public static String decrypt(String dataPassword, String encrypted)
            throws Exception {
        byte[] byteMi = decode(encrypted);
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptedData = cipher.doFinal(byteMi);

        return new String(decryptedData, BM);
    }


    public static File encryptFile(InputStream inputStream, String fileName, String key) {
        File encrypfile = null;
        OutputStream outputStream = null;
        try {
            encrypfile = new File(fileName);
            outputStream = new FileOutputStream(encrypfile);
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(key.getBytes()));
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey secretKey = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            cipherInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encrypfile;
    }

    public static File encryptFile(File sourceFile, String key) {
        File encrypfile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String tempName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
            String fileName = sourceFile.getParent() + File.separator + tempName + ".AE";
            inputStream = new FileInputStream(sourceFile);
            encrypfile = new File(fileName);
            outputStream = new FileOutputStream(encrypfile);
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(key.getBytes()));
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey secretKey = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            cipherInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encrypfile;
    }

    public static File decryptFile(File sourceFile, String key) {
        File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String tempName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
            tempName = new String(Hex.decodeHex(tempName.toCharArray()), "utf-8");
            String fileName = sourceFile.getParent() + File.separator + tempName + ".jpg";
            decryptFile = new File(fileName);
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(key.getBytes()));
            SecretKey original_key = keygen.generateKey();
            byte[] raw = original_key.getEncoded();
            SecretKey secretKey = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(decryptFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, r);
            }
            cipherOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return decryptFile;
    }

    public static void main(String[] args) throws Exception {

        String pwd = "ANSBDUANXUHUAZUISHUAIHOUJIEMINSB";
        String content = "abc";
        /*
         * 加密
         */
        System.out.println("使用AES对称加密，请输入加密的规则");
        String enContent = AesUtils.encrypt(pwd, content);
        System.out.println("根据输入的规则" + pwd + "加密后的密文是:" + enContent);

        /*
         * 解密
         */
        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
        System.out.println("根据输入的规则" + pwd + "解密后的明文是:" + AesUtils.decrypt(pwd, enContent));
    }
}
