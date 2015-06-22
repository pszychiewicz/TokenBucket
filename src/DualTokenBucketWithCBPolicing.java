import org.jnetpcap.packet.JPacket;


public class DualTokenBucketWithCBPolicing implements TokenBucket{
    SingleTokenBucket bcBucket;
    SingleTokenBucket beBucket;

    public DualTokenBucketWithCBPolicing(SingleTokenBucket bcBucket, SingleTokenBucket beBucket) {
        this.bcBucket = bcBucket;
        this.beBucket = beBucket;
    }

    public boolean consumePacket(JPacket packet) {
        boolean result = bcBucket.consumePacketWithoutRefill(packet) && beBucket.consumePacketWithoutRefill(packet);
        long rest = bcBucket.refill(packet.getCaptureHeader().timestampInMillis());
        beBucket.refillwithTokens(rest);
        return result;
    }
}
