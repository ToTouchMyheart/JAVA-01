import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Optional;

/**
 * @Author zhurui
 * @Date 2021/4/10 1:04 下午
 * @Version 1.0
 */
public class TopicClient extends ActiveMessageQueueClient {

    private String queueName;

    public TopicClient(String url, String queueName) throws JMSException {
        super(url);
        this.queueName = queueName;
        super.connect();
    }

    @Override
    void initDes() throws JMSException {
        destination = session.createTopic(queueName);
    }
}
