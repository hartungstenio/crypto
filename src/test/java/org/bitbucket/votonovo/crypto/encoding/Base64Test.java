package org.bitbucket.votonovo.crypto.encoding;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.Data;
import org.bitbucket.votonovo.crypto.TextData;
import org.bitbucket.votonovo.crypto.util.ListAssert;
import org.bitbucket.votonovo.crypto.util.Transformed;
import org.junit.Test;

public class Base64Test {
    
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
            "Hello\0",
            "Hello?>>>"
    };
    private static final String[] OUTPUTS = {
            "",
            "Zg==",
            "Zm8=",
            "Zm9v",
            "Zm9vYg==",
            "Zm9vYmE=",
            "Zm9vYmFy",
            
            "SA==",
            "SGU=",
            "SGVs",
            "SGVsbA==",
            "SGVsbG8=",
            "SGVsbG8A",
            "SGVsbG8/Pj4+"
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
                    return new Base64Encoded(input);
                }
            },
            Arrays.asList(OUTPUTS)
        );
    }
}
