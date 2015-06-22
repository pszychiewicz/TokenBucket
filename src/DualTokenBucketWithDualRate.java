import org.jnetpcap.packet.JPacket;

public class DualTokenBucketWithDualRate implements TokenBucket{
    SingleTokenBucket bcBucket;
    SingleTokenBucket beBucket;

    public DualTokenBucketWithDualRate(SingleTokenBucket bcBucket, SingleTokenBucket beBucket) {
        this.bcBucket = bcBucket;
        this.beBucket = beBucket;
    }

    public boolean consumePacket(JPacket packet) {
        return bcBucket.consumePacket(packet) && beBucket.consumePacket(packet);
    }
}
