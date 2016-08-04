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
package de.fdamken.minecraft.pe.facade.server;

import java.net.SocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.fdamken.minecraft.pe.facade.server.exception.ServerExecutionException;
import io.netty.bootstrap.Bootstrap;

/**
 * The MCPE (Minecraft Pocket Edition) server handles the Netty bootstrap and
 * binds it.
 *
 */
@Component
public class MinecraftPEServer {
    /**
     * The configured Netty {@link Bootstrap} for UDP.
     * 
     */
    @Autowired
    @Qualifier("serverBootstrap")
    private Bootstrap serverBootstrap;
    /**
     * The address the server should bind to.
     * 
     */
    @Autowired
    @Qualifier("serverAddress")
    private SocketAddress serverAddress;

    /**
     * Holds the state whether the server is started.
     * 
     */
    private boolean started = false;

    /**
     * Starts the Minecraft Pocket Edition server.
     *
     * @throws ServerExecutionException
     *             If any error occurs during the server execution.
     */
    public void start() throws ServerExecutionException {
        if (this.started) {
            throw new IllegalStateException("The server is already started!");
        }

        this.started = true;

        try {
            this.serverBootstrap.bind(this.serverAddress).await();
        } catch (final InterruptedException cause) {
            throw new ServerExecutionException("Failed to execute the MCPE server!", cause);
        }
    }
}
