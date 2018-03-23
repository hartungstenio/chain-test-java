package org.bitbucket.votonovo.blockchain;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.bitbucket.votonovo.util.text.TextOf;

public class TestChain {
    
    public static final int DIFFICULTY = 4;
    
    public static void main(String[] args) {
        Miner miner = new Miner.Parallel(Block.of("Block #1", new TextOf(64, '0').toString()));

        System.out.println("Mining...");
        
        try {
            Instant start = Instant.now();
            Block block = miner.mineBlock(DIFFICULTY);
            Instant end = Instant.now();
            
            System.out.printf("Block mined in %d millis %s", Duration.between(start, end).toMillis(), System.lineSeparator());
            System.out.println(new BlockText(block).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static final class BlockText {
        
        private final Block block;
        
        public BlockText(final Block block) {
            this.block = block;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("id").append("=").append(this.block.id()).append(System.lineSeparator())
                    .append("previous").append("=").append(this.block.previousHash()).append(System.lineSeparator())
                    .append("timestamp").append("=").append(this.block.timestamp()).append(System.lineSeparator())
                    .append("nonce").append("=").append(this.block.nonce()).append(System.lineSeparator())
                    .append("data").append("=").append(this.block.data())
                    .toString();
                    
        }
        
    }
}
