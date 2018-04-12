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
