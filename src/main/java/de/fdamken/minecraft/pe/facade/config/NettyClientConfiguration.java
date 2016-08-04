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
package de.fdamken.minecraft.pe.facade.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the Netty client for the communication with the Minecraft server
 * to facade.
 *
 */
@Configuration
public class NettyClientConfiguration {
    /**
     * The logger.
     * 
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientConfiguration.class);

    /**
     * The host of the Minecraft server to work as a facade for.
     * 
     */
    @Value("${minecraft.server.host}")
    private String host;
    /**
     * The port of the Minecraft server to work as a facade for.
     * 
     */
    @Value("${minecraft.server.port}")
    private int port;

    /**
     * Invoked by Spring after this bean was created.
     * 
     * <b> NOTE: You must not invoke this method directly! </b>
     *
     */
    @PostConstruct
    void onPostConstruct() {
        NettyClientConfiguration.LOGGER.info("Creating client for the Minecraft server running at {}:{}", this.host, this.port);
    }
}
