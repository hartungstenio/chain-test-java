package org.bitbucket.votonovo.util.text;

import java.util.Arrays;
import java.util.StringJoiner;

public class JoinedText {

    private final String delimiter;
    private final Iterable<String> values;
    
    public JoinedText(final String delimiter, String... values) {
        this(delimiter, Arrays.asList(values));
    }
    
    public JoinedText(final String delimiter, Iterable<String> values) {
        this.delimiter = delimiter;
        this.values = values;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(this.delimiter);
        
        for(String text : this.values) {
            joiner.add(text);
        }
        
        return joiner.toString();
    }
}
