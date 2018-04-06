package org.bitbucket.votonovo.crypto.encoding;

import java.io.IOException;

import org.bitbucket.votonovo.crypto.Data;

/**
 * Base32 encoded data
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public class Base32Encoded implements StringEncoded {
    
    private static final String BASE32_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";


    private final Data bytes;
    
    /**
     * Ctor.
     * 
     * @param bytes The data to encode
     */
    public Base32Encoded(final Data bytes) {
        this.bytes = bytes;
    }
    
    public String asString() throws IOException {
        final byte[] rawData = this.bytes.getBytes();
        
        StringBuilder buff = new StringBuilder();
        
        for(int i = 0; i < rawData.length; i += 5) {
            int len = 5;
            if(i + len > rawData.length) len = rawData.length - i;
            
            encodeBlock(rawData, i, len, buff);
        }
        
        return buff.toString();
    }
    
    private void encodeBlock(final byte[] data, final int offset, final int len, final Appendable sb) throws IOException {
        final int length = (int)Math.ceil(8 * len / 5.0);
        
        // 40 bits at once
        long octets = 0;
        
        for(int i = 0; i < len; i++) {
            octets |= (long)data[offset + i] << (32 - (8 * i));
        }
        
        for(int i = 0; i < 8; i++) {
            int q = (int)(octets >> (35 - (5 * i))) & 0x1f;
            sb.append(i < length ? BASE32_TABLE.charAt(q) : '=');
        }
    }
    
    public String toString() {
        try {
            return asString();
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
