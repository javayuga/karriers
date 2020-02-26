package club.code55.karriers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface GameSessionProcessor {

  String APPLICATIONS_IN = "output";
  String NEXT_TURN = "next_turn";

  @Input(APPLICATIONS_IN)
  SubscribableChannel sourceOfGameSessionStartApplications();

  @Output(NEXT_TURN)
  MessageChannel next_turn();
}
