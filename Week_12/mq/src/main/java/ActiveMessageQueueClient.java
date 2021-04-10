import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Optional;

/**
 * @Author zhurui
 * @Date 2021/4/10 12:50 下午
 * @Version 1.0
 */
public abstract class ActiveMessageQueueClient {

    private String url;

    Destination destination;

    Session session;

    MessageProducer producer;

    MessageConsumer consumer;

    public ActiveMessageQueueClient(String url) {
        this.url = url;
    }

    public void get(String name) throws JMSException {
        this.consumer = Optional.ofNullable(consumer).orElse(session.createConsumer(destination));
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(name + "接收消息" + textMessage.getText());
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void send(String msg) throws JMSException {
        this.producer = Optional.ofNullable(producer).orElse(session.createProducer(destination));
        TextMessage textMessage = session.createTextMessage(msg);
        producer.send(textMessage);
        System.out.println("已发送消息" + msg);
    }

    void connect() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        this.initDes();
    }

    abstract void initDes() throws JMSException;

}
