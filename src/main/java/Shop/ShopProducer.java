package Shop;
//import util.properties packages

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

//import simple producer packages
//import KafkaProducer packages
//import ProducerRecord packages

//Create java class named “SimpleProducer”
public class ShopProducer {
    public Producer<String, Long> producer;
    public String topicName;

    public ShopProducer(String topicName) {
        //Assign topicName to string variable
        this.topicName = topicName;
        // create instance for properties to access producer configs
        Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.LongSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void sendReply(, long quantity) {

        producer.send(new ProducerRecord<String, Long>(topicName,id,quantity));

        System.out.println("Message sent successfully to topic " + topicName);
        //producer.close();

    }

}