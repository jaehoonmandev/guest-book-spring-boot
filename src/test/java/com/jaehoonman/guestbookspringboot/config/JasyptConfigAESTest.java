package com.jaehoonman.guestbookspringboot.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;

class JasyptConfigAESTest {

    @Test
    void stringEncryptor() {
        String url = "db_url";
        String username = "jaehoonman";
        String password = "Q!w2e3r4";

        System.out.println(jasyptEncoding(url));
        System.out.println(jasyptEncoding(username));
        System.out.println(jasyptEncoding(password));
//        ZaSKTZeLUZgjD97LsIJBuraOJiQBUqCYJanh8nCSyRx+tvqGcXU5lmjwNPGQqDvi
//        e9o/MQco15mNOF6baawm+rOoF76SXSf72QPVn4G5/Ktb77/HDKu+yvVpfN3lpvgk
//        OvVpePRkS5sr+8yHGFc5ATzrV4l3tBYLF23z40aD818qgrre+O1YiGaSSZEXJWCf
    }

    public String jasyptEncoding(String value) {

        String key = "jaehoonman";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setPassword(key);
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        return pbeEnc.encrypt(value);
    }

}