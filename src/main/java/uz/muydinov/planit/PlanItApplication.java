package uz.muydinov.planit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "uz.muydinov.planit.entity")
public class PlanItApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanItApplication.class, args);
    }

}
