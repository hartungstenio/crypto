package org.bitbucket.votonovo.crypto.encoding;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.Data;
import org.bitbucket.votonovo.crypto.TextData;
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
            new Transformed<String, Data>(
                new Transformed.Mapping<String, Data>() {
                    public Data map(String input) {
                        return new TextData(input);
                    }
                },
                Arrays.asList(INPUTS)
            )
        ).expected(
            new Transformed.Mapping<Data, StringEncoded>() {
                public StringEncoded map(Data input) {
                    return new Base16Encoded(input);
                }
            },
            Arrays.asList(OUTPUTS)
        );
    }
}
