package org.bitbucket.votonovo.util.text;

public class TextOf {
    
    private final int repeats;
    private final char value;
    
    public TextOf(final int repeats, final char value) {
        this.repeats = repeats;
        this.value = value;
    }

    @Override
    public String toString() {
        return new String(new char[this.repeats]).replace('\0', value);
    }

}
