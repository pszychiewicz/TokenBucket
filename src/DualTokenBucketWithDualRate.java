import org.jnetpcap.packet.JPacket;

public class DualTokenBucketWithDualRate implements TokenBucket{
    SingleTokenBucket bcBucket;
    SingleTokenBucket beBucket;

    public DualTokenBucketWithDualRate(SingleTokenBucket bcBucket, SingleTokenBucket beBucket) {
        this.bcBucket = bcBucket;
        this.beBucket = beBucket;
    }

    public boolean consumePacket(JPacket packet) {
        if(bcBucket.consumePacket(packet)) {
            beBucket.refill(packet.getCaptureHeader().timestampInMillis());
            return true;
        } else return beBucket.consumePacket(packet);
    }
}
