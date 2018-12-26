package aes.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

import static aes.util.AES.base64Encode;

public class DES {

    public static byte[] getKey(String keyRule) {
        Key key = null;
        byte[] keyByte = keyRule.getBytes();
        byte[] byteTemp = new byte[8];
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        key = new SecretKeySpec(byteTemp, "DES");
        return key.getEncoded();
    }

    public static byte[] encrypt(String content, String key) throws Exception{

        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(getKey(key));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        byte[] result = cipher.doFinal(content.getBytes());
        return result;
    }


    public static String decrypt(byte[] content, String key) throws Exception{
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(getKey(key));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        byte[] result = cipher.doFinal(content);
        return new String(result);

    }

    public static byte[] base64Decode(String base64Code) throws Exception{
        return base64Code.isEmpty()?null:new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static String desDecrypt(String cipherText,String password)throws Exception{
        return cipherText.isEmpty()?null:decrypt(base64Decode(cipherText),password);
    }

    public static String desEncrypt(String plainText,String password)throws Exception{
        return base64Encode(encrypt(plainText,password));
    }



}
