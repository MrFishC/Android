package cn.jack.module09_encryption_decryption;


import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * @author zhaoqi
 */
public class RsaEncrypt {
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 貌似默认是RSA/NONE/PKCS1Padding，未验证
     */
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static RsaEncrypt RsaEncrypt = null;
    private static PublicKey pubkey2;
    public static String PUBLIC_KEY = "publicKey";

    /**
     * RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024
     */
    public static final int KEY_SIZE = 2048;

    private RsaEncrypt() {

    }

    public static RsaEncrypt getInstance(String publicKey) {
        pubkey2 = RsaEncrypt.restorePublicKey(Base64.decode(publicKey.getBytes(),Base64.DEFAULT));
        return new RsaEncrypt();
    }



    /**
     * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
     *
     * @param keyBytes
     * @return
     */
    private static PublicKey restorePublicKey(byte[] keyBytes) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 加密，三步走。
     *
     * @param plainText
     * @return
     */
    public static byte[] RSAEncode(byte[] plainText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubkey2);
            return cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

}