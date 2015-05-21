package provider.impl;

import provider.IdProvider;

import java.util.Random;

/**
 * Created by mmatosevic on 21.5.2015.
 */
public class RandomIdProvider implements IdProvider {

    private static final Random random = new Random(System.currentTimeMillis());

    @Override
    public long getId() {
        return random.nextLong();
    }

}
