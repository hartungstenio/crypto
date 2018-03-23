package org.bitbucket.votonovo.crypto.encoding;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.ByteSource;
import org.bitbucket.votonovo.crypto.TextBytes;
import org.junit.Test;

public class Base64Test {
    
    private static final String[] INPUTS = { "pleasure.", "leasure.", "easure.", "asure.", "sure." };
    private static final String[] OUTPUTS = { "cGxlYXN1cmUu", "bGVhc3VyZS4=", "ZWFzdXJlLg==", "YXN1cmUu", "c3VyZS4=" };

    @Test
    public void base64EncodeTest() {
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
                    return new Base64Encoded(input);
                }
            },
            Arrays.asList(OUTPUTS)
        );
    }
}
