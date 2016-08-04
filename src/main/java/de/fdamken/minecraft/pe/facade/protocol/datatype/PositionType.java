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

import de.fdamken.minecraft.pe.facade.model.Position;
import io.netty.buffer.ByteBuf;

/**
 * Represents a {@link Position}.
 *
 */
public class PositionType extends AbstractDataType<Position> {
    /**
     * {@inheritDoc}
     *
     * @see de.fdamken.minecraft.pe.facade.protocol.datatype.DataType#read(io.netty.buffer.ByteBuf)
     */
    @Override
    public Position read(final ByteBuf buffer) {
        final long val = buffer.readLong();

        final int x = (int) (val >> 38);
        final int y = (int) (val >> 26 & 0x0FFF);
        final int z = (int) (val & 0x03FFFFFF);

        final Position value = new Position(x, y, z);

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
        final Position value = this.get();
        final int x = value.getX();
        final int y = value.getY();
        final int z = value.getZ();

        buffer.writeLong((x & 0x03FFFFFF) << 38 | (y & 0x0FFF) << 26 | z & 0x03FFFFFF);
    }
}
