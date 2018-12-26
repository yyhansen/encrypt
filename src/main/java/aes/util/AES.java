package aes.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by chenhansen on 2018/5/16.
 */

public class AES {

    public static byte[] encrypt(String plainText,String strKey)throws Exception{
        SecretKeySpec secretKeySpec=getKey(strKey);
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv=new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,iv);
        byte[] encrypted=cipher.doFinal(plainText.getBytes());
        return encrypted;
    }

    public static String decrypt(byte[] plainText,String strKey)throws Exception{
        SecretKeySpec skeySpec=getKey(strKey);
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv=new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE,skeySpec,iv);
        byte[] original=cipher.doFinal(plainText);
        return new String(original);
    }

    private static SecretKeySpec getKey(String strKey){
        byte[] arrBTmp=strKey.getBytes();
        byte[] arrB=new byte[16];

        for(int i=0;i<arrBTmp.length&&i<arrB.length;i++){
            arrB[i]=arrBTmp[i];
        }

        SecretKeySpec skeySpec=new SecretKeySpec(arrB,"AES");

        return skeySpec;

    }

    public static String base64Encode(byte[]  bytes){

        return new BASE64Encoder().encode(bytes);
    }

    public static byte[] base64Decode(String base64Code) throws Exception{
        return base64Code.isEmpty()?null:new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static String aesEncrypt(String plainText,String password)throws Exception{
        return base64Encode(encrypt(plainText,password));
    }

    public static String aesDecrypt(String plainText,String password)throws Exception{
        return plainText.isEmpty()?null:decrypt(base64Decode(plainText),password);
    }
//    public static void main(String[] args)throws Exception{
//        String plainText="test";
//        String password="123456";
//
//        System.out.println("加密前： "+plainText);
//        String encryptResult=aesEncrypt(plainText,password);
//        //System.out.println("加密后： "+ encryptResult);
//
//        System.out.println("加密后: "+encryptResult);
//        String decryptResult=aesDecrypt(encryptResult,password);
//        System.out.println("解密后： "+decryptResult);
//    }
}
