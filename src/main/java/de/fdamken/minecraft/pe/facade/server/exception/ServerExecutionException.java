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
package de.fdamken.minecraft.pe.facade.server.exception;

/**
 * The {@link ServerExecutionException} is thrown if any error occurs during the
 * server execution phase.
 *
 */
public class ServerExecutionException extends ServerException {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 5643839974652961448L;

    /**
     * Constructor of ServerExecutionException.
     *
     * @param message
     *            A detailed error message.
     * @param cause
     *            The causing error.
     */
    public ServerExecutionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of ServerExecutionException.
     *
     * @param message
     *            A detailed error message.
     */
    public ServerExecutionException(final String message) {
        super(message);
    }
}
