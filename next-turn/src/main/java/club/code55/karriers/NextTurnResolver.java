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
public class NextTurnResolver {

  public static final Logger log = LoggerFactory.getLogger(NextTurnResolver.class);
  private static final Long MAX_AMOUNT = 10000L;
  private NextTurnProcessor processor;

  @Autowired
  public NextTurnResolver(NextTurnProcessor processor) {
    this.processor = processor;
  }

  @StreamListener(NextTurnProcessor.APPLICATIONS_IN)
  public void resolveNextTurn(GameSession gamesession) {

    if (gamesession.getCurrentTurn()<gamesession.getMaxTurns()){
      log.info("NEXT_TURN -> ${} - {} / {}", gamesession.getUuid(), gamesession.getCurrentTurn(), gamesession.getMaxTurns());
      gamesession.setCurrentTurn(gamesession.getCurrentTurn()+1);
      processor.postNextTurn().send(message(gamesession));
    }else{
      log.info("STOP -> ${} - {} / {}", gamesession.getUuid(), gamesession.getCurrentTurn(), gamesession.getMaxTurns());
      processor.postGameSessionStop().send(message(gamesession));
    }



  }

  private static final <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }
}
