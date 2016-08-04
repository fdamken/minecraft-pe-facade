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

import de.fdamken.minecraft.pe.facade.server.ServerChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;

/**
 * Configures the Netty server that works as the Minecraft Pocket Edition
 * server.
 *
 */
@Configuration
public class NettyServerConfiguration {
    /**
     * The logger.
     * 
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerConfiguration.class);

    /**
     * The IP to bind the facade server to.
     * 
     */
    @Value("${minecraft.pocket-edition.ip}")
    private String ip;
    /**
     * The port to bind the facade server to.
     * 
     */
    @Value("${minecraft.pocket-edition.port}")
    private int port;

    /**
     * The number of threads the worker group should use.
     * 
     */
    @Value("${minecraft.pocket-edition.worker-threads}")
    private int workerThreadCount;

    /**
     * The channel initializer.
     * 
     */
    @Autowired
    private ServerChannelInitializer channelInitializer;

    /**
     * Invoked by Spring after this bean was created.
     * 
     * <b> NOTE: You must not invoke this method directly! </b>
     *
     */
    @PostConstruct
    private void onPostConstruct() {
        NettyServerConfiguration.LOGGER.info("Creating Minecraft Pocket Edition server as a facade at {}:{}", this.ip, this.port);
    }

    /**
     * Initializes and configures Netty's UDP bootstrap server.
     *
     * @return The bootstrap server.
     */
    @Bean
    public Bootstrap serverBootstrap() {
        return new Bootstrap() //
                .group(this.serverWorkerGroup()) //
                .channel(EpollDatagramChannel.class) //
                .handler(this.channelInitializer) //
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
    }

    /**
     * Initializes the worker group for Netty using with
     * {@link #workerThreadCount} threads.
     *
     * @return The worker group.
     */
    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup serverWorkerGroup() {
        return new EpollEventLoopGroup(this.workerThreadCount);
    }

    /**
     * Wraps the {@link #ip} and the {@link #port} into a single
     * {@link SocketAddress}.
     *
     * @return The server address.
     */
    @Bean
    public SocketAddress serverAddress() {
        return new InetSocketAddress(this.ip, this.port);
    }
}
