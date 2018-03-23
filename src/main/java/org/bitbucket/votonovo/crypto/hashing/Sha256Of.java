package org.bitbucket.votonovo.crypto.hashing;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bitbucket.votonovo.crypto.ByteSource;

/**
 * Hash tha data using SHA-256
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public final class Sha256Of implements ByteSource {
    
    private final ByteSource input;
    
    /**
     * Ctor.
     * 
     * @param input The data to hash
     */
    public Sha256Of(final ByteSource input) {
        this.input = input;
    }

    public byte[] getBytes() throws IOException {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(this.input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }
}
