package com.jaehoonman.guestbookspringboot.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정값 암호화를 위한 복호화 모듈
@Configuration
@EnableEncryptableProperties
public class JasyptConfigAES {

    @Value("${jasypt.encryptor.key}")
    private String key;

    @Bean("jasyptEncryptorAES")
    public StringEncryptor stringEncryptor() {

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(key); // 암호화키
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256"); // 알고리즘
        config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
        config.setPoolSize("1"); // 인스턴스 pool
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64"); //인코딩 방식
        encryptor.setConfig(config);
        return encryptor;
    }

}
