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
 * A packet decoder is used to decode {@link ByteBuf buffers} into POJOs which
 * than can be easily processed.
 *
 * Packet decoders must be populate in the Spring context, where the names of
 * the beans follow this format: <code>pd-{protocol}-{id}</code> where
 * <code>{protocol}</code> is replaced with the appropriate protocol (either
 * <code>classic</code> or <code>pocket</code>) and <code>{id}</code> is
 * replaced by the packet number in lower case hexadecimal (i.e
 * <code>pd-classic-1e</code>).
 *
 * @param <T>
 *            The type of the packet this decoder is responsible for.
 */
public interface PacketDecoder<T extends Packet> {
    /**
     * Decodes the given {@link ByteBuf buffer} into a packet POJO.
     *
     * @param buffer
     *            The {@link ByteBuf buffer} to decode.
     * @return The decoded {@link ByteBuf buffer}.
     */
    T decode(ByteBuf buffer);
}
