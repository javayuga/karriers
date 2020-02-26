package club.code55.karriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(GameSessionProcessor.class)
public class GameSessionStartApplication {

  public static final Logger log = LoggerFactory.getLogger(GameSessionStartApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(GameSessionStartApplication.class, args);
    log.info("The GameSessionStart Application has started...");
  }
}
