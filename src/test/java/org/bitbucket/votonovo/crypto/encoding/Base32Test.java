package org.bitbucket.votonovo.crypto.encoding;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.Data;
import org.bitbucket.votonovo.crypto.TextData;
import org.bitbucket.votonovo.crypto.util.ListAssert;
import org.bitbucket.votonovo.crypto.util.Transformed;
import org.junit.Test;

public class Base32Test {
    
    private static final String[] INPUTS = {
            "",
            "f",
            "fo",
            "foo",
            "foob",
            "fooba",
            "foobar"
    };
    private static final String[] OUTPUTS = {
            "",
            "MY======",
            "MZXQ====",
            "MZXW6===",
            "MZXW6YQ=",
            "MZXW6YTB",
            "MZXW6YTBOI======"
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
                    return new Base32Encoded(input);
                }
            },
            Arrays.asList(OUTPUTS)
        );
    }
}
