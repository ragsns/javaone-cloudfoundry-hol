package com.pivotal.example.xd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

public class OrderGenerator implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    private boolean generating = false;
    private boolean stopped = false;

    public void startGen() {
        this.generating = true;
    }

    public void stopGen() {
        this.generating = false;
    }

    @Override
    public void run() {

        RabbitClient client = RabbitClient.getInstance();
        while (!stopped) {
            if (generating) {
                Random random = new Random();
                String state = HeatMap.states[random.nextInt(HeatMap.states.length)];
                int value = (1 + random.nextInt(4)) * 10;
                Order order = new Order();
                order.setAmount(value);
                order.setState(state);
                try {
                    client.post(order);
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            } else {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    return;
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                return;
            }
        }
    }

    public void shutdown() {
        stopped = true;
    }
}
