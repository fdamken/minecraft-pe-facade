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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;

/**
 * The channel initializer for the Minecraft Pocket Edition facade server.
 *
 */
@Component
public class ServerChannelInitializer extends ChannelInitializer<DatagramChannel> {
    /**
     * The channel handler of the server.
     * 
     */
    @Autowired
    private ServerChannelHandlerAdapter serverChannelHandler;

    /**
     * {@inheritDoc}
     *
     * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
     */
    @Override
    protected void initChannel(final DatagramChannel channel) throws Exception {
        channel.pipeline().addLast(this.serverChannelHandler);
    }
}
