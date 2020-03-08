package club.code55.web.rest;

import club.code55.config.KafkaProperties;
/**
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import club.code55.karriers.domain.v1.GameSession;

@RestController
@RequestMapping("/api/webui-kafka")
public class WebuiKafkaResource {

    private final Logger log = LoggerFactory.getLogger(WebuiKafkaResource.class);

    private final KafkaProperties kafkaProperties;
    //private KafkaProducer<String, String> producer;
    private ExecutorService sseExecutorService = Executors.newCachedThreadPool();
    //private KafkaProducer<String, GameSession> gameSessionKafkaProducer;


    public WebuiKafkaResource(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
        //this.producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
    }

    @PostMapping("/publish/{topic}")
    public PublishResult publish(@PathVariable String topic, @RequestParam String message, @RequestParam(required = false) String key) throws ExecutionException, InterruptedException {
        log.debug("REST request to send to Kafka topic {} with key {} the message : {}", topic, key, message);
        //RecordMetadata metadata = producer.send(new ProducerRecord<>(topic, key, message)).get();
        //return new PublishResult(metadata.topic(), metadata.partition(), metadata.offset(), Instant.ofEpochMilli(metadata.timestamp()));
        return new PublishResult("",0,0, Instant.now());
    }

    @GetMapping("/startGameSession")
    public PublishResult startGameSession()  throws ExecutionException, InterruptedException {
        log.debug("REST request to start a game session");

        GameSession gamesession = new GameSession(UUID.randomUUID().toString(), 0, 10);

//        RecordMetadata metadata = gameSessionKafkaProducer.send(new ProducerRecord<>("output", gamesession.getUuid(), gamesession)).get();
//        return new PublishResult(metadata.topic(), metadata.partition(), metadata.offset(), Instant.ofEpochMilli(metadata.timestamp()));
        return new PublishResult("",0,0, Instant.now());
    }


    @GetMapping("/consume")
    public SseEmitter consume(@RequestParam("topic") List<String> topics, @RequestParam Map<String, String> consumerParams) {
        log.debug("REST request to consume records from Kafka topics {}", topics);
        Map<String, Object> consumerProps = kafkaProperties.getConsumerProps();
        consumerProps.putAll(consumerParams);
        consumerProps.remove("topic");

        SseEmitter emitter = new SseEmitter(0L);
        sseExecutorService.execute(() -> {
//            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
//            emitter.onCompletion(consumer::close);
//            consumer.subscribe(topics);
            boolean exitLoop = false;
            while(!exitLoop) {
                try {
//                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
//                    for (ConsumerRecord<String, String> record : records) {
//                        emitter.send(record.value());
//                    }
                    emitter.send(SseEmitter.event().comment(""));
                } catch (Exception ex) {
                    log.trace("Complete with error {}", ex.getMessage(), ex);
                    emitter.completeWithError(ex);
                    exitLoop = true;
                }
            }
//            consumer.close();
            emitter.complete();
        });
        return emitter;
    }

    private static class PublishResult {
        public final String topic;
        public final int partition;
        public final long offset;
        public final Instant timestamp;

        private PublishResult(String topic, int partition, long offset, Instant timestamp) {
            this.topic = topic;
            this.partition = partition;
            this.offset = offset;
            this.timestamp = timestamp;
        }
    }
}
