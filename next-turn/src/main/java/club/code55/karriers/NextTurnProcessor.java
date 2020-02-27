package club.code55.karriers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface NextTurnProcessor {

  String APPLICATIONS_IN = "next_turn";

  @Input(APPLICATIONS_IN)
  SubscribableChannel read_next_turn();

  @Output
  MessageChannel postNextTurn();

  @Output
  MessageChannel postGameSessionStop();
}
