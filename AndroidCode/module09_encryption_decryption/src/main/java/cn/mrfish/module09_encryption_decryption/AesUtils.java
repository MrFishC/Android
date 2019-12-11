package cn.mrfish.module09_encryption_decryption;


import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @TODO 类描述: AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；使用AES-128-CBC-PKCS5Padding加密模式，key需要为16位。
 *
 * @类名称: AesUtils
 * @作者: Alan_tay
 * @时间: 19-10-21 下午5:32
 */

public class AesUtils {
    /*
     * 加密用的Key 可以用26个字母和数字组成
     */
//    private static String sKey;//公钥
//    private static String ivParameter = ;//默认私钥

    private static String sKey           ;
    private static String ivParameter    ;

    private static AesUtils aesUtils = null;

    //使用CBC模式
    private static String ciphers = "AES/CBC/PKCS5Padding";

    //算法
    private static final String ALGORITHM        = "AES";
    
    public static AesUtils getInstance(String key,String iv) {
            sKey = new String(Base64.decode(key,Base64.DEFAULT));
            ivParameter = new String(Base64.decode(iv,Base64.DEFAULT));
//        sKey= key;
//        ivParameter= iv;

        if(aesUtils == null){
            aesUtils = new AesUtils();
        }
        return aesUtils;
    }

    /**
     *
     * 方法描述: AES加密,使用CBC模式,使用BASE64做转码。
     *
     * @方法名称 encrypt
     * @param sSrc 需要加密值
     * @return:
     * @作者: Alan_tay
     * @date: 18-8-27 下午4:43
     */
    public static String encrypt(String sSrc){
        String result = "";
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(sKey.getBytes("utf-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("utf-8"), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ciphers);
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv,random);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            result = byteToHexString(encrypted);
        } catch (Exception e) {

            System.out.println("encrypt :"+ e.getMessage() );

            e.printStackTrace();
        }finally {
            sKey = "";
            ivParameter = "";
        }
        return result;
    }

    /**
     *
     * @TODO 方法描述: 将byte转换为16进制字符串
     *
     * @方法名称: byteToHexString
     * @param src
     * @return java.lang.String
     * @作者: Alan_tay
     * @时间: 19-10-22 下午6:25
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

//    public static void main(String[] args) throws Exception{
//        String passwd = "1234qwer";
//        System.out.println("原密码:"+passwd);
////        String key = "D60faTBiXnq1lu4Y";
////        String iv = "nX5BSQ0sN113RtB2";
//        String key = "SGVjbHhqSFhDTzlqR25IQg==";
//        String iv = "MUtqNHdYRzZ3N3kzcVFxaA==";
//        System.out.println("key:"+key+",iv:"+iv);
////        String passwds = AesUtils.getInstance(key,iv).encrypt(passwd);
//        String passwds = AesUtils.getInstance(key,iv).encrypt(passwd);
//        System.out.println("加密之后:"+passwds);
////        passwds = AesUtils.getInstance(key,iv).decrypt(passwds);
////        System.out.println("解密之后:"+passwds);
//    }
}
