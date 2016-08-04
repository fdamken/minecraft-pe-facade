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
package de.fdamken.minecraft.pe.facade.protocol;

import io.netty.buffer.ByteBuf;

/**
 * A packet represents either a TCP packet or a UDP datagram that was sent or
 * will be send using Netty. Packets can be decoded from a {@link ByteBuf
 * buffer} by using the appropriate {@link PacketDecoder}. Please lookup
 * {@link PacketDecoder} to see how you can load one.
 *
 */
public interface Packet {
    /**
     * Encodes this packet into a {@link ByteBuf buffer} that can be send over
     * Netty.
     *
     * @return This encoded packet.
     */
    ByteBuf encode();

    /**
     *
     * @return The protocol of this packet.
     */
    Protocol getProtocol();

    /**
     *
     * @return The direction the package is bound to. Please lookup
     *         {@link BoundDirection} for more information.
     */
    BoundDirection getDirection();

    /**
     * Packets are bound to one of these protocols.
     *
     */
    public static enum Protocol {
        /**
         * The classic protocol that is used by regular Minecraft servers and
         * clients.
         * 
         */
        CLASSIC,
        /**
         * The pocket edition protocol that is used by Minecraft Pocket Edition
         * servers and clients.
         * 
         */
        POCKET;
    }

    /**
     * Packets have a direction as they are either send from the client to the
     * server (server-bound) or from the server to the client (client-bound).
     *
     */
    public static enum BoundDirection {
        /**
         * Represents the direction where the client sends a packet to the
         * server.
         * 
         */
        SERVER_BOUND,
        /**
         * Represents the direction where the server sends a packet to the
         * client.
         * 
         */
        CLIENT_BOUND;
    }
}
