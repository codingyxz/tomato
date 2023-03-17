package com.tomato.base64;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @Author zhxy
 * @Date 2023/3/6 3:15 下午
 * @Version V1.0
 **/
public class ASCEncryptUtil {

    private static final String ASC_SECRET_KET = "docKey";

    private static final Logger logger = LoggerFactory.getLogger(ASCEncryptUtil.class);

    private ASCEncryptUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param content 需要加密的内容
     * @return
     */
    public static String encrypt(String content) {
        try {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, initKeyForAES());// 初始化

            byte[] result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64URLSafeString(result);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    /**
     * @param cipherContent 待解密内容
     * @return
     */
    public static String decrypt(String cipherContent) {

        try {
            byte[] decodeBase64 = Base64.decodeBase64(cipherContent);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, initKeyForAES());// 初始化

            byte[] decryptBytes = cipher.doFinal(decodeBase64);
            return new String(decryptBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            logger.info("the cipher content is not right because of {}", e.getMessage());
            throw new RuntimeException("内容无效");
        } catch (Exception e) {
            logger.info("decrypt fail because of {}", e.getMessage());
            throw new RuntimeException("内容有误");
        }
    }

    private static SecretKeySpec initKeyForAES() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(ASC_SECRET_KET.getBytes());

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, secureRandom);

            return new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException("初始化密钥出现异常");
        }
    }

    public static void main(String[] args) {
//        String decrypt = decrypt("JhBZJsTB_NQkVdp3Z-eT01YieyPQViuu65_E5xiv7GPSeJSdSCRZpnAH9lg_WXb4SAqQC1gzq7seOzawOp4jrw");
        String decrypt = decrypt("JhBZJsTB_NQkVdp3Z-eT01YieyPQViuu65_E5xiv7GOfKW2dSqiu1eixM2E3JjVZ");
        System.out.println(decrypt);
    }
}
