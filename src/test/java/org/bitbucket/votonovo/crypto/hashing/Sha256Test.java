package org.bitbucket.votonovo.crypto.hashing;

import java.util.Arrays;

import org.bitbucket.votonovo.crypto.Data;
import org.bitbucket.votonovo.crypto.TextData;
import org.bitbucket.votonovo.crypto.encoding.Base16Encoded;
import org.bitbucket.votonovo.crypto.encoding.StringEncoded;
import org.bitbucket.votonovo.crypto.util.ListAssert;
import org.bitbucket.votonovo.crypto.util.Transformed;
import org.junit.Test;

public class Sha256Test {
    
    private static final String[] INPUTS = {
            "",
            "The quick brown fox jumps over the lazy dog",
            
            "abc",
            "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq",
            "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu"
        };
    private static final String[] OUTPUTS = {
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
            "d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592",
            
            "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad",
            "248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1",
            "cf5b16a778af8380036ce59e7b0492370b249b11e8f07a51afac45037afee9d1"
        };
    
    @Test
    public void hashTest() {
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
                    return new Base16Encoded(new Sha256Of(input));
                }
            },
            Arrays.asList(OUTPUTS)
        );
    }
}
