package club.code55.karriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import club.code55.karriers.domain.v1.GameSession;

@SpringBootApplication
public class GameSessionStartSourceApplication {

  private static final Logger log = LoggerFactory.getLogger(GameSessionStartSourceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(GameSessionStartSourceApplication.class, args);
    log.info("The GameSessionStart source Application has started...");
  }

  @Bean
  public Supplier<GameSession> supplyGameSession() {
    return () -> {
      GameSession gamesession = new GameSession(UUID.randomUUID().toString(), 0, 10);
      log.info("${} - {} / {}", gamesession.getUuid(), gamesession.getCurrentTurn(), gamesession.getMaxTurns());
      return gamesession;
    };
  }
}
