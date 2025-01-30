package dev.theancientowl.feathershowcase.common;

import org.junit.jupiter.api.Test;

class MessageTest {
    @Test
    @SuppressWarnings("unused")
    void dummyConstructor() {
        var Message = new Message(); // Message should contain only static fields
        var General = new Message.General(); // Message.General should contain only static fields
    }
}
