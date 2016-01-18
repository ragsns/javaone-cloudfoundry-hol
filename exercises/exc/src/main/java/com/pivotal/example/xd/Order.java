package com.pivotal.example.xd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Order implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    private String state;
    private int amount;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public byte[] toBytes() {
        byte[] bytes;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            oos.reset();
            bytes = baos.toByteArray();
            oos.close();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public static Order fromBytes(byte[] body) {
        Order obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = (Order) ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
        } catch (ClassNotFoundException ex) {
            LOG.error(ex.getLocalizedMessage(), ex);
        }
        return obj;
    }

}
