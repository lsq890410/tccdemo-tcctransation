package org.tccdemo.hmily.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({"org.tccdemo.hmily","com.hmily.tcc"})
@ImportResource({"classpath:applicationContext.xml"})
public class DubboTccPayApplication {
	 public static void main(String[] args) {
	        SpringApplication.run(DubboTccPayApplication.class, args);
	 }
}
