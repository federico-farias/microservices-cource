package com.bintics.orders.gateway;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsRetryer implements Retryer {

    private final int WAIT_TIME = 1000;
    private final int MAX_NUM_ATTEMPS = 5;
    private int count = 0;

    @Override
    public void continueOrPropagate(RetryableException e) {
        try {
            this.count++;
            int jitter = calculateJitter();
            Thread.sleep(WAIT_TIME * jitter); // Exponential back-off
            if (this.count == MAX_NUM_ATTEMPS) {
                throw new ProductsGatewayException("the maximum number of retries has been reached");
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }
    
    @Override
    public Retryer clone() {
        return new ProductsRetryer();
    }

    private int calculateJitter() {
        int min = 1; // Min value
        int max = 5; // Max value
        int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return randomInt;
    }

}
