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
package de.fdamken.minecraft.pe.facade.protocol.datatype;

import java.util.UUID;

import io.netty.buffer.ByteBuf;

/**
 * Represents a {@link UUID}.
 *
 */
public class UuidType extends AbstractDataType<UUID> {
    // Name: UUID
    // Size (bytes): 16
    // Encodes: A UUID
    // Notes: Encoded as an unsigned 64-bit integer
    // Implementation Notes: N/A

    /**
     * {@inheritDoc}
     *
     * @see de.fdamken.minecraft.pe.facade.protocol.datatype.DataType#read(io.netty.buffer.ByteBuf)
     */
    @Override
    public UUID read(final ByteBuf buffer) {
        final long msb = new LongType().read(buffer);
        final long lsb = new LongType().read(buffer);

        final UUID value = new UUID(msb, lsb);

        this.set(value);

        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.fdamken.minecraft.pe.facade.protocol.datatype.DataType#write(io.netty.buffer.ByteBuf)
     */
    @Override
    public void write(final ByteBuf buffer) {
        final UUID value = this.get();

        final long msb = value.getMostSignificantBits();
        final long lsb = value.getLeastSignificantBits();

        new LongType().set(msb).write(buffer);
        new LongType().set(lsb).write(buffer);
    }
}
