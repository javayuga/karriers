package club.code55.karriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import club.code55.karriers.domain.v1.GameSession;

@Component
public class GameSessionChecker {

  public static final Logger log = LoggerFactory.getLogger(GameSessionChecker.class);
  private static final Long MAX_AMOUNT = 10000L;
  private GameSessionProcessor processor;

  @Autowired
  public GameSessionChecker(GameSessionProcessor processor) {
    this.processor = processor;
  }

  @StreamListener(GameSessionProcessor.APPLICATIONS_IN)
  public void loadScenario(GameSession gamesession) {
    log.info("${} - {} / {}", gamesession.getUuid(), gamesession.getCurrentTurn(), gamesession.getMaxTurns());

    processor.next_turn().send(message(gamesession));


  }

  private static final <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }
}
