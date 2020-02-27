package club.code55.karriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(NextTurnProcessor.class)
public class NextTurnApplication {

  public static final Logger log = LoggerFactory.getLogger(NextTurnApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(NextTurnApplication.class, args);
    log.info("The NextTurn Application has started...");
  }
}
