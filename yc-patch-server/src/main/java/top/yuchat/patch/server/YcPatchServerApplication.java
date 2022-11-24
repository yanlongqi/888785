package top.yuchat.patch.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.yuchat.patch")
public class YcPatchServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YcPatchServerApplication.class, args);
    }
}
