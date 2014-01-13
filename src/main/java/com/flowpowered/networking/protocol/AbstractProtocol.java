/*
 * This file is part of Flow Networking, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flowpowered.networking.protocol;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowpowered.networking.Message;
import com.flowpowered.networking.MessageHandler;

/**
 * A {@code AbstractProtocol} stores to what port the protocol should be bound to.
 */
public abstract class AbstractProtocol implements Protocol {
    private final String name;
    private final int defaultPort;
    private final Logger logger;

    public AbstractProtocol(String name, int defaultPort) {
        this(name, defaultPort, LoggerFactory.getLogger("Protocol." + name));
    }

    /**
     * @param name
     * @param defaultPort
     * @param maxPackets this is one more than the maximum packet id
     * @param logger
     */
    public AbstractProtocol(String name, int defaultPort, Logger logger) {
        this.name = name;
        this.defaultPort = defaultPort;
        this.logger = logger;
    }

    /**
     * Gets the name of the AbstractProtocol
     *
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * The default port is the port used when autogenerating default bindings for this protocol and in the client when no port is given.
     *
     * @return The default port
     */
    public int getDefaultPort() {
        return defaultPort;
    }

    /**
     * Returns the logger for this protocol.
     * 
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Allows applying a wrapper to messages with dynamically allocated id's, in case this protocol needs to provide special treatment for them.
     *
     * @param dynamicMessage The message with a dynamically-allocated codec
     * @return The new message
     */
    public <T extends Message> Message getWrappedMessage(T dynamicMessage) throws IOException {
        return dynamicMessage;
    }

    public abstract <T extends Message> MessageHandler<T> getMessageHandle(Class<T> message);
}