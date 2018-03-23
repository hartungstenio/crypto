package org.bitbucket.votonovo.crypto.encoding;

import java.io.IOException;

/**
 * For data encoded in a string
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public interface StringEncoded {
    
    /**
     * Get the string representation
     * 
     * @return The string
     * @throws IOException If fails (reading the data or encoding it)
     */
    String asString() throws IOException;
}
