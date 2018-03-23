package org.bitbucket.votonovo.blockchain;

import java.time.Instant;

import org.bitbucket.votonovo.crypto.TextBytes;
import org.bitbucket.votonovo.crypto.encoding.Base16Encoded;
import org.bitbucket.votonovo.crypto.hashing.Sha256Of;
import org.bitbucket.votonovo.util.crypto.Hashable;
import org.bitbucket.votonovo.util.text.JoinedText;

public interface Block {

    String id();

    String previousHash();

    String data();

    long timestamp();
    
    int nonce();
    
    public static Block of(final String data, final String previousHash) {
        return new Block.New(data, previousHash);
    }
    
    public static final class New implements Block {
        
        private final String previousHash;
        private final String data;
        private final long timestamp;
        
        public New(final String data, final String previousHash) {
            this.data = data;
            this.previousHash = previousHash;
            this.timestamp = Instant.now().getEpochSecond();
        }

        @Override
        public String id() {
            return null;
        }

        @Override
        public String previousHash() {
            return this.previousHash;
        }

        @Override
        public String data() {
            return this.data;
        }

        @Override
        public long timestamp() {
            return this.timestamp;
        }
        
        @Override
        public int nonce() {
            return 0;
        }
    }

    public static final class Signed implements Block, Hashable {

        private final String id;
        private final long timestamp;
        private final int nonce;
        private final String data;
        private final String previousHash;
        
        public Signed(final String id, final long timestamp, final int nonce, final String data, final String previousHash) {
            this.id = id;
            this.timestamp = timestamp;
            this.nonce = nonce;
            this.data = data;
            this.previousHash = previousHash;
        }

        public Signed(final long timestamp, final int nonce, final String data, final String previousHash) {
            this.timestamp = timestamp;
            this.nonce = nonce;
            this.data = data;
            this.previousHash = previousHash;
            this.id = hash();
        }
        
        public Signed(final Block block, final int nonce) {
            this(block.timestamp(), nonce, block.data(), block.previousHash());
        }

        @Override
        public String id() {
            return this.id;
        }

        @Override
        public String previousHash() {
            return this.previousHash;
        }

        @Override
        public String data() {
            return this.data;
        }

        @Override
        public long timestamp() {
            return this.timestamp;
        }
        
        @Override
        public int nonce() {
            return this.nonce;
        }

        @Override
        public String hash() {
            return new Base16Encoded(
                    new Sha256Of(
                     new TextBytes(
                      new JoinedText("",
                       previousHash(),
                       Long.toString(timestamp()), 
                       Integer.toString(nonce()),
                       data()
                      ).toString()
                     )
                    )
                   ).toString();
        }
    }
}
