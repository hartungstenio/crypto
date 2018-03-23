package org.bitbucket.votonovo.crypto.encoding;

import java.io.IOException;

import org.bitbucket.votonovo.crypto.ByteSource;

/**
 * Base64 encoded data
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public class Base64Encoded implements StringEncoded {
    
    private static final String BASE64_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";


    private final ByteSource bytes;
    
    /**
     * Ctor.
     * 
     * @param bytes The data to encode
     */
    public Base64Encoded(final ByteSource bytes) {
        this.bytes = bytes;
    }
    
    public String asString() throws IOException {
        final byte[] rawData = this.bytes.getBytes();
        
        StringBuilder buff = new StringBuilder();
        
        for(int i = 0; i < rawData.length; i += 3) {
            int len = 3;
            if(i + len > rawData.length) len = rawData.length - i;
            
            encodeBlock(rawData, i, len, buff);
        }
        
        return buff.toString();
    }
    
    private void encodeBlock(final byte[] data, final int offset, final int len, final Appendable sb) throws IOException {
        int octets = data[offset] << 16;
        if(len > 1) octets = octets | (data[offset+1] << 8);
        if(len > 2) octets = octets | (data[offset+2]);
        
        int i = (octets >> 18) & 0x3f;
        sb.append(BASE64_TABLE.charAt(i));
        
        i = (octets >> 12) & 0x3f;
        sb.append(BASE64_TABLE.charAt(i));
        
        i = octets >> 6 & 0x3f;
        sb.append(i > 0 ? BASE64_TABLE.charAt(i) : '=');
        
        i = octets & 0x3f;
        sb.append(i > 0 ? BASE64_TABLE.charAt(i) : '=');
    }
    
    public String toString() {
        try {
            return asString();
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
