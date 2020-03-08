package club.code55.kafka;

import club.code55.karriers.domain.v1.GameSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class GameSessionStartProducer {

    @Autowired
    private KafkaTemplate<String, GameSession> gameSessionKafkaTemplate;

    @Value(value = "${kafka.gameSessionStart.topic.name}")
    private String gameSessionTopicName;

    public void sendGameSessionStartMessage(GameSession gs) {
        gameSessionKafkaTemplate.send(gameSessionTopicName, gs);
    }
}
