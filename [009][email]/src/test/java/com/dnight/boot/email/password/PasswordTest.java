package com.dnight.boot.email.password;

import com.dnight.boot.email.UTApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZHONGPENG769
 * @date 2019/11/6
 */
public class PasswordTest extends UTApplication {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void generatePassword(){
        String password = "Just4Test!";
        String encryptPassword = encryptor.encrypt(password);
        String decryptPassword = encryptor.decrypt(encryptPassword);

        System.out.println(encryptor.decrypt("OT0qGOpXrr1Iog1W+fjOiIDCJdBjHyhy"));

        System.out.println("pwd = "+password);
        System.out.println("encryptPassword = "+encryptPassword);
        System.out.println("decryptPassword = "+decryptPassword);
    }
}
