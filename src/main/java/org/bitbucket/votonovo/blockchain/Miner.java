package org.bitbucket.votonovo.blockchain;

import java.io.IOException;
import java.util.stream.IntStream;

import org.bitbucket.votonovo.util.text.TextOf;

public interface Miner {
    
    Block mineBlock(int difficulty) throws IOException;
    
    public static final class Sequential implements Miner {
        
        private final Block block;
        
        public Sequential(final Block block) {
            this.block = block;
        }

        @Override
        public Block mineBlock(int difficulty) throws IOException {
            final String target = new TextOf(difficulty, '0').toString();
            
            Block signed;
            
            Block theBlock = block;
            
//            do {
                signed = IntStream.rangeClosed(0, Integer.MAX_VALUE)
                                 .mapToObj(nonce -> new Block.Signed(theBlock, nonce))
                                 .filter(s -> s.id().substring(0, difficulty).equals(target))
                                 .findFirst()
                                 .get();
//            } while(signed == null);
//            
            return signed;
        }
    }
    
    public static final class Parallel implements Miner {
        
        private final Block block;
        
        public Parallel(final Block block) {
            this.block = block;
        }

        @Override
        public Block mineBlock(int difficulty) throws IOException {
            final String target = new TextOf(difficulty, '0').toString();
            
            Block signed;
            
            Block theBlock = block;
            
//            do {
                signed = IntStream.rangeClosed(0, Integer.MAX_VALUE)
                                 .parallel()
                                 .mapToObj(nonce -> new Block.Signed(theBlock, nonce))
                                 .filter(s -> s.id().substring(0, difficulty).equals(target))
                                 .findFirst()
                                 .get();
//            } while(signed == null);
//            
            return signed;
        }
    }
}
