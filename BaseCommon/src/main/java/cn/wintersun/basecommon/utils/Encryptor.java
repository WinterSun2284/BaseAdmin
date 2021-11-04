package cn.wintersun.basecommon.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basecommon.utils.Encryptor
 * @create 2021-11-03-15:18
 * @Description TODO
 */
public class Encryptor {

    /**
     * 加密
     * @param value 需要被加密的字符
     * @return java.lang.String
     **/
    public static String encoder(String value) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("wintersun");
        return encryptor.encrypt(value);
    }


    /**
     * 解密
     * @param value 需要被解密的字符
     * @return java.lang.String
     **/
    public static String decoder(String value) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("wintersun");
        return encryptor.decrypt(value);
    }

    public static void main(String[] args) {
        String a="wintersun";
        String encoder = Encryptor.encoder(a);
        System.out.println(encoder);
        String decoder = Encryptor.decoder(encoder);
        System.out.println(decoder);
    }

}
