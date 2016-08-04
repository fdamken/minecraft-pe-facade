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

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.fdamken.minecraft.pe.facade.client.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
     * The number of threads the worker group should use.
     * 
     */
    @Value("${minecraft.server.worker-threads}")
    private int workerThreadCount;

    /**
     * The channel initializer.
     * 
     */
    @Autowired
    private ClientChannelInitializer channelInitializer;

    /**
     * Invoked by Spring after this bean was created.
     * 
     * <b> NOTE: You must not invoke this method directly! </b>
     *
     */
    @PostConstruct
    private void onPostConstruct() {
        NettyClientConfiguration.LOGGER.info("Creating client for the Minecraft server running at {}:{}", this.host, this.port);
    }

    /**
     * Initializes and configures Netty's TCP bootstrap client.
     *
     * @return The bootstrap client.
     */
    @Bean
    public Bootstrap clientBootstrap() {
        return new Bootstrap() //
                .group(this.clientWorkerGroup()) //
                .channel(NioSocketChannel.class) //
                .handler(this.channelInitializer) //
                .option(ChannelOption.SO_KEEPALIVE, true);
    }

    /**
     * Initializes the worker group for Netty using with
     * {@link #workerThreadCount} threads.
     *
     * @return The worker group.
     */
    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup clientWorkerGroup() {
        return new NioEventLoopGroup(this.workerThreadCount);
    }

    /**
     * Wraps the {@link #host} and the {@link #port} into a single
     * {@link SocketAddress}.
     *
     * @return The address of the server to connect to.
     */
    @Bean
    public SocketAddress clientAddress() {
        return new InetSocketAddress(this.host, this.port);
    }
}
