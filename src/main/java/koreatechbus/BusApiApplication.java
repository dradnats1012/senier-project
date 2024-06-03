package koreatechbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BusApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusApiApplication.class, args);
    }
}
