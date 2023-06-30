package cn.goldencis.auxiliary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuxiliaryApplication {

    public static void main(String[] args) {
        System.getProperty("s");
        SpringApplication.run(AuxiliaryApplication.class, args);
    }

}
