package org.bitbucket.votonovo.crypto.encoding;

import java.io.IOException;

import org.bitbucket.votonovo.crypto.Data;

/**
 * Base16 (hexadecimal) encoding
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public class Base16Encoded implements StringEncoded {
    
    private final Data bytes;
    
    /**
     * Ctor.
     * 
     * @param bytes The data to encode
     */
    public Base16Encoded(final Data bytes) {
        this.bytes = bytes;
    }
    
    public String asString() throws IOException {
        StringBuilder hexStr = new StringBuilder();
        
        for(byte b : this.bytes.getBytes()) {
            String bHex = Integer.toHexString(b & 0xff);
            if(bHex.length() == 1) hexStr.append("0");
            hexStr.append(bHex);
        }
        
        return hexStr.toString();
    }
    
    public String toString() {
        try {
            return asString();
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
