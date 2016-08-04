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
 * A data type is a fix defined type of data that can be read from a
 * {@link ByteBuf buffer} and can be written to a {@link ByteBuf buffer}.
 * 
 * @param <T>
 *            The Java type that this type represents.
 */
public interface DataType<T> {
    /**
     *
     * @return The stored value as a regular Java type.
     */
    T get();

    /**
     * Sets the stored value.
     *
     * @param value
     *            The value to set.
     * @return The {@link DataType} instance.
     */
    DataType<T> set(T value);

    /**
     * Reads the given {@link ByteBuf buffer} and sets the value of this type.
     *
     * @param buffer
     *            The {@link ByteBuf buffer} to read.
     * @return The newly stored value.
     */
    T read(ByteBuf buffer);

    /**
     * Writes the value of this date into the given {@link ByteBuf buffer}.
     *
     * @param buffer
     *            The {@link ByteBuf buffer} to write into.
     */
    void write(ByteBuf buffer);
}
