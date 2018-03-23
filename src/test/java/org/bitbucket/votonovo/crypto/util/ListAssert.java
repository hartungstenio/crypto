package org.bitbucket.votonovo.crypto.util;

import java.util.Iterator;

import org.bitbucket.votonovo.crypto.ByteSource;
import org.bitbucket.votonovo.crypto.encoding.StringEncoded;
import org.junit.Assert;

/**
 * Helper class to test String encodings
 * 
 * @author Christian Hartung <hartung@live.com>
 *
 */
public final class ListAssert {
    
    private final Iterable<ByteSource> inputs;
   
    /**
     * Ctor.
     * 
     * @param inputs The source sample data
     */
    public ListAssert(final Iterable<ByteSource> inputs) {
        this.inputs = inputs;
    }
    
    /**
     * Compares the sample data.
     * 
     * @param encode How to encode the data
     * @param outputs The sample data to compare the input
     */
    public void expected(Transformed.Mapping<ByteSource, StringEncoded> encode, final Iterable<String> outputs) {
        Iterator<ByteSource> inputIterator = this.inputs.iterator();
        Iterator<String> outputIterator = outputs.iterator();
        
        while(inputIterator.hasNext()) {
            Assert.assertTrue("Missing outputs values", outputIterator.hasNext());
            
            Assert.assertEquals(outputIterator.next(), encode.map(inputIterator.next()).toString());
        }
    }
}
