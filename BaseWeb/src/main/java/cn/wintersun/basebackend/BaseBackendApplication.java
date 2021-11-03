package cn.wintersun.basebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.wintersun.basebackend.mapper")
public class BaseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseBackendApplication.class, args);
	}

}
