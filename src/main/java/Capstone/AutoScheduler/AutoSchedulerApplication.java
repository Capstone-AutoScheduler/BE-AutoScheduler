package Capstone.AutoScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AutoSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoSchedulerApplication.class, args);
		System.out.println("[Initiate Capstone Project AutoScheduler]");
	}

}
