/*
 * #%L
 * Minecraft Pocket Edition Facade
 * %%
 * Copyright (C) 2016 - 2016 Fabian Damken
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package de.fdamken.minecraft.pe.facade.client;

import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.Bootstrap;

/**
 * The Minecraft Client that is used to connect to the Minecraft server.
 *
 */
@Component
public class MinecraftClient {
    /**
     * The configured Netty {@link Bootstrap} for TCP connections to the server.
     * 
     */
    @Autowired
    @Qualifier("clientBootstrap")
    private Bootstrap clientBootstrap;
    /**
     * The address of the server to connect to.
     * 
     */
    @Autowired
    @Qualifier("clientAddress")
    private SocketAddress clientAddress;

    /**
     * Holds the state whether the client is started.
     * 
     */
    private boolean started = false;

    /**
     * Starts the Minecraft client.
     *
     */
    public void start() {
        if (this.started) {
            throw new IllegalStateException("The client is already started!");
        }

        this.started = true;

        this.clientBootstrap.connect(this.clientAddress);
    }
}
