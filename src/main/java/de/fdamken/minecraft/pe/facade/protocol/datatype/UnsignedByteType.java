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

import io.netty.buffer.ByteBuf;

/**
 * Represents an unsigned {@link Byte} using a {@link Short}.
 *
 */
public class UnsignedByteType extends AbstractDataType<Short> {
    // Name: Unsigned Byte
    // Size (bytes): 1
    // Encodes: An integer between 0 and 255
    // Notes: Unsigned 8-bit integer
    // Implementation Notes: Represented as a Short as Java does not have
    // unsigned numbers.

    /**
     * {@inheritDoc}
     *
     * @see de.fdamken.minecraft.pe.facade.protocol.datatype.DataType#read(io.netty.buffer.ByteBuf)
     */
    @Override
    public Short read(final ByteBuf buffer) {
        final short value = buffer.readUnsignedByte();

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
        buffer.writeByte(this.get());
    }
}
