package org.bitbucket.votonovo.crypto.encoding;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.ByteSource;
import org.bitbucket.votonovo.crypto.TextBytes;
import org.bitbucket.votonovo.crypto.util.ListAssert;
import org.bitbucket.votonovo.crypto.util.Transformed;
import org.junit.Test;

public class Base16Test {
    
    private static final String[] INPUTS = {
            "",
            "f",
            "fo",
            "foo",
            "foob",
            "fooba",
            "foobar",
            
            "H",
            "He",
            "Hel",
            "Hell",
            "Hello",
            "Hello\0"
    };
    
    private static final String[] OUTPUTS = {
            "",
            "66",
            "666f",
            "666f6f",
            "666f6f62",
            "666f6f6261",
            "666f6f626172",
            
            "48",
            "4865",
            "48656c",
            "48656c6c",
            "48656c6c6f",
            "48656c6c6f00"
    };

    @Test
    public void encodeTest() {
        new ListAssert(
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
