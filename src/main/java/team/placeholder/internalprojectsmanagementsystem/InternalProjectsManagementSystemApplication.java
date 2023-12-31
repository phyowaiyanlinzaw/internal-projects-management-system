package team.placeholder.internalprojectsmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InternalProjectsManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternalProjectsManagementSystemApplication.class, args);
    }

}
