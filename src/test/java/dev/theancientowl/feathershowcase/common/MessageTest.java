/**
 * ------------------------------------------------------------------------------ *
 *                     Copyright (c) by FeatherShowcase 2025                      *
 * ------------------------------------------------------------------------------ *
 * @license https://github.com/TheAncientOwl/feather-showcase/blob/main/LICENSE
 *
 * @file MessageTest.java
 * @author Alexandru Delegeanu
 * @version 0.1
 * @test_unit Message#version
 * @description Unit tests for Message
 */

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
