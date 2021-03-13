package com.matthewlowe.testmod;

import net.minecraftforge.eventbus.api.IEventBus;

public class ClientEvents {
    private final IEventBus eventBus;

    public ClientEvents(IEventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void registerClientEvents() {
        eventBus.register(ClientEvents.class);
    }
}
