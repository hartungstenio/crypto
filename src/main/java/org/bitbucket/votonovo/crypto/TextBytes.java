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
package org.bitbucket.votonovo.crypto;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.w3c.dom.Text;

/**
 * Extracts bytes from a string
 * 
 * @author Christian Hartung <hartung@live.com>
 * @since 0.0.1
 */
public final class TextBytes implements ByteSource {
    
    private final CharSequence text;
    private final Charset charset;
    
    /**
     * Ctor.
     * 
     * <p>By default, assumes UTF-8 encoding</p>
     * 
     * @param text The text
     */
    public TextBytes(final CharSequence text) {
        this(text, StandardCharsets.UTF_8);
    }
    
    /**
     * Ctor.
     * 
     * @param text The text
     * @param charset The encoding
     */
    public TextBytes(final CharSequence text, final Text charset) {
        this(text, Charset.forName(charset.toString()));
    }
    
    /**
     * Ctor.
     * 
     * @param text The text
     * @param charset The encoding
     */
    public TextBytes(final CharSequence text, final CharSequence charset) {
        this(text, Charset.forName(charset.toString()));
    }
    
    /**
     * Ctor.
     * 
     * @param text The text
     * @param charset The encoding
     */
    public TextBytes(final CharSequence text, final Charset charset) {
        this.text = text;
        this.charset = charset;
    }

    public byte[] getBytes() throws IOException {
        return this.text.toString().getBytes(this.charset);
    }

}
