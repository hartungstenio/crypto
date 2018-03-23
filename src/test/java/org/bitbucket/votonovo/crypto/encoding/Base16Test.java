package org.bitbucket.votonovo.crypto.encoding;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.ByteSource;
import org.bitbucket.votonovo.crypto.TextBytes;
import org.junit.Test;

public class Base16Test {
    
    private static final String[] INPUTS = { "pleasure.", "leasure.", "easure.", "asure.", "sure." };
    private static final String[] OUTPUTS = { "706c6561737572652e", "6c6561737572652e", "6561737572652e", "61737572652e", "737572652e" };

    @Test
    public void encodeTest() {
        new StringEncodingTest(
            new Transformed<String, ByteSource>(
                new Transformed.Mapping<String, ByteSource>() {
                    public ByteSource map(String input) {
                        return new TextBytes(input);
                    }
                },
                Arrays.asList(INPUTS)
            )
        ).expected(
            new Transformed.Mapping<ByteSource, StringEncoded>() {
                public StringEncoded map(ByteSource input) {
                    return new Base16Encoded(input);
                }
            },
            Arrays.asList(OUTPUTS)
        );
    }
}
