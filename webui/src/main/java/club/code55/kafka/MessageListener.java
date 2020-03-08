package club.code55.kafka;

import club.code55.karriers.domain.v1.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageListener {

    private final Logger log = LoggerFactory.getLogger(MessageListener.class);

    private CountDownLatch latch = new CountDownLatch(3);

    private CountDownLatch gameSessionLatch = new CountDownLatch(1);

    @KafkaListener(topics = "foo", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        log.debug("Received Message in group 'foo': " + message);
        latch.countDown();
    }


    @KafkaListener(topics = "output", containerFactory = "gameSessionKafkaListenerContainerFactory")
    public void gameSessionStartListener(GameSession gs) {
        log.debug("Received gamesession start message: " + gs.toString());
        this.gameSessionLatch.countDown();
    }
}
