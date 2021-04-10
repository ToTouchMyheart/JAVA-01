import javax.jms.JMSException;

/**
 * @Author zhurui
 * @Date 2021/4/10 12:17 下午
 * @Version 1.0
 */
public class TopicDemo {


    public static void main(String[] args) {
        Consumer consumer = new Consumer("消费者:A");
        Consumer consumer1 = new Consumer("消费者:B");
        Consumer consumer2 = new Consumer("消费者:C");
        consumer.start();
        consumer1.start();
        consumer2.start();
        TopicDemo.Producer producer = new TopicDemo.Producer("生产者:伊利");
        producer.start();

    }


    static class Producer extends Thread {
        private String name;

        private static TopicClient client = null;

        static {
            try {
                client = new TopicClient("tcp://172.20.49.84:61616", "zero-topic");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        public Producer(String name) {
            this.name = name;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(name + " 正在生产消息:" + i);
                    client.send(String.valueOf(i));
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private String name;
        private static TopicClient client = null;

        static {
            try {
                client = new TopicClient("tcp://172.20.49.84:61616", "zero-topic");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                client.get(name);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
