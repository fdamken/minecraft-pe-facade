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
package de.fdamken.minecraft.pe.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import de.fdamken.minecraft.pe.facade.client.MinecraftClient;
import de.fdamken.minecraft.pe.facade.server.MinecraftPEServer;
import de.fdamken.minecraft.pe.facade.server.exception.ServerExecutionException;

/**
 * The main class of the Minecraft Pocket Edition Facade.
 * 
 * <b> NOTE: This class does not contain any configuration as the configuration
 * is in a dedicated package called
 * <code>de.fdamken.minecraft.pe.facade.config</code>. </b>
 *
 */
@SpringBootApplication
public class MinecraftPocketEditionFacade {
    /**
     * The main method of the Minecraft Pocket Edition Facade.
     *
     * @param args
     *            The command line arguments.
     * @throws ServerExecutionException
     *             Id an error occurs whilst executing the MCPE server.
     */
    public static void main(final String[] args) throws ServerExecutionException {
        // The application context closes itself on shutdown.
        @SuppressWarnings("resource")
        final ApplicationContext applicationContext = SpringApplication.run(MinecraftPocketEditionFacade.class, args);

        applicationContext.getBean(MinecraftClient.class).start();
        applicationContext.getBean(MinecraftPEServer.class).start();
    }
}
