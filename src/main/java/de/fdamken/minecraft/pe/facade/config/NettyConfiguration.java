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
 * Configures Netty.
 *
 */
@Configuration
public class NettyConfiguration {
    /**
     * The logger.
     * 
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyConfiguration.class);

    /**
     * The host of the Minecraft server to work as a facade for.
     * 
     */
    @Value("${minecraft.server.host}")
    private String serverHost;
    /**
     * The port of the Minecraft server to work as a facade for.
     * 
     */
    @Value("${minecraft.server.port}")
    private int serverPort;

    /**
     * The IP to bind the facade server to.
     * 
     */
    @Value("${minecraft.pocket-edition.ip}")
    private String bindIp;
    /**
     * The port to bind the facade server to.
     * 
     */
    @Value("${minecraft.pocket-edition.port}")
    private int bindPort;

    /**
     * Is invoked after this bean was constructed.
     * 
     * <b> NOTE: You must not invoke this method directly! </b>
     *
     */
    @PostConstruct
    public void onPostConstruct() {
        NettyConfiguration.LOGGER.info("Creating facade for the Minecrafte server at {}:{} on {}:{}", this.serverHost,
                this.serverPort, this.bindIp, this.bindPort);
    }
}
