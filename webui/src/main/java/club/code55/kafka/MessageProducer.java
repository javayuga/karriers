package club.code55.kafka;

import club.code55.karriers.domain.v1.GameSession;
import club.code55.web.rest.WebuiKafkaResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
public class MessageProducer {

    private final Logger log = LoggerFactory.getLogger(WebuiKafkaResource.class);

    @Autowired
    private KafkaTemplate<String, GameSession> gameSessionKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> stringMessageKafkaTemplate;

    @Value(value = "${kafka.gameSessionStart.topic.name}")
    private String gameSessionTopicName;

    public void sendGameSessionStartMessage(GameSession gs) {
        gameSessionKafkaTemplate.send(gameSessionTopicName, gs);
    }

    public void sendStringMessageToTopic(String topic, String key, String value) {

        ListenableFuture<SendResult<String, String>> future;

        if (key!=null){
            future = stringMessageKafkaTemplate.send(topic, key, value);

        }else{
            future = stringMessageKafkaTemplate.send(topic, value);

        }

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("Sent message=[" + value + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + value + "] due to : " + ex.getMessage());
            }
        });

    }
}
