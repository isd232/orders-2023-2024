package pl.edu.wszib.application.order;

import java.util.UUID;

public interface OrderIdProvider {
    String create();
}

class TestOrderIdProvider implements OrderIdProvider {

    @Override
    public String create() {
        return UUID.randomUUID() + "-TEST";
    }
}
