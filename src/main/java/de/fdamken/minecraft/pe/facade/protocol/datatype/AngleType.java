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

/**
 * Represents the angel data type as a {@link Short}.
 *
 */
public class AngleType extends UnsignedShortType {
    // Name: Unsigned Byte
    // Size (bytes): 1
    // Encodes: A rotation angle in steps of 1/256 of a full turn
    // Notes: Whether or not this is signed does not matter, since the resulting
    // angles are the same.
    // Implementation Notes: It is treated as an unsigned byte that is
    // represented as a Short as Java does not have unsigned numbers.
}
