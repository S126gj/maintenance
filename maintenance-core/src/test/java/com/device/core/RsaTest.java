package com.device.core;

import com.device.common.utils.RSAEncryptUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * RSA 非对称加密测试
 * @Author: Guoji Shen
 * @Date: 2023/9/22 09:58
 */
@SpringBootTest
public class RsaTest {

    public static String privateKey = "";
    public static String publicKey = "";

    @Test
    public void test() {
        // 生成公钥与密钥
        // RSAEncryptUtils.KeyPairInfo keyPair = RSAEncryptUtils.getKeyPair(RSAEncryptUtils.KEY_SIZE);
        // System.out.println("privateKey:" + keyPair.getPrivateKey());
        // System.out.println("publicKey:" + keyPair.getPublicKey());

        // 加解密
        String conent="shit bro";
        String encrypt = RSAEncryptUtils.encipher(conent, publicKey);
        System.out.println("-----------------加密------------------");
        System.out.println(encrypt);

        //------------------------RSA算法解密
        String decrypt = RSAEncryptUtils.decipher(encrypt, privateKey);
        System.out.println("-----------------解密------------------");
        System.out.println(decrypt);
    }
}
