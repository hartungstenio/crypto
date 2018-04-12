/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.bitbucket.votonovo.crypto.encoding;

import java.io.IOException;

import org.bitbucket.votonovo.crypto.Data;

/**
 * Base64 encoded data
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public class Base64Encoded implements StringEncoded {
    
    private static final String BASE64_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";


    private final Data bytes;
    
    /**
     * Ctor.
     * 
     * @param bytes The data to encode
     */
    public Base64Encoded(final Data bytes) {
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
        sb.append(len > 1 ? BASE64_TABLE.charAt(i) : '=');
        
        i = octets & 0x3f;
        sb.append(len > 2 ? BASE64_TABLE.charAt(i) : '=');
    }
    
    public String toString() {
        try {
            return asString();
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
