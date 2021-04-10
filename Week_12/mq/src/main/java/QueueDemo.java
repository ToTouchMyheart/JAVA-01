import javax.jms.JMSException;

/**
 * @Author zhurui
 * @Date 2021/4/10 12:17 下午
 * @Version 1.0
 */
public class QueueDemo {


    public static void main(String[] args) {
        QueueDemo.Producer producer = new QueueDemo.Producer("生产者:伊利");
        producer.start();
        Consumer consumer = new Consumer("消费者:A");
        Consumer consumer1 = new Consumer("消费者:B");
        Consumer consumer2 = new Consumer("消费者:C");
        consumer.start();
        consumer1.start();
        consumer2.start();

    }


    static class Producer extends Thread {
        private String name;

        private static QueueClient queueClient = null;

        static {
            try {
                queueClient = new QueueClient("tcp://172.20.49.84:61616", "queue");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(name + " 正在生产消息:" + i);
                    queueClient.send(String.valueOf(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private String name;
        private static QueueClient queueClient = null;

        static {
            try {
                queueClient = new QueueClient("tcp://172.20.49.84:61616", "queue");
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
                queueClient.get(name);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
