import org.jnetpcap.packet.JPacket;


public class DualTokenBucketWithCBPolicing implements TokenBucket{
    SingleTokenBucket bcBucket;
    SingleTokenBucket beBucket;

    public DualTokenBucketWithCBPolicing(SingleTokenBucket bcBucket, SingleTokenBucket beBucket) {
        this.bcBucket = bcBucket;
        this.beBucket = beBucket;
    }

    public boolean consumePacket(JPacket packet) {
        boolean result = false;
        if(bcBucket.consumePacketWithoutRefill(packet)) {
            result =  true;
        } else if(beBucket.consumePacketWithoutRefill(packet)) {
            result =  true;
        }
        long rest = bcBucket.refill(packet.getCaptureHeader().timestampInMillis());
        beBucket.refillwithTokens(rest);
        return result;
    }
}
