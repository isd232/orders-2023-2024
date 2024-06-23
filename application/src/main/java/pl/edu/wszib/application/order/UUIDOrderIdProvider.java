package pl.edu.wszib.application.order;

import java.util.UUID;

public class UUIDOrderIdProvider implements OrderIdProvider {

    @Override
    public String create() {
        return UUID.randomUUID().toString();
    }
}
