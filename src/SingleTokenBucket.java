import org.jnetpcap.packet.JPacket;

public class SingleTokenBucket implements TokenBucket{
    private long maxCapacity;   //bytes
    private long refillRate;    //byte/s

    private long lastTimestamp = -1;
    private long capacity;

    public SingleTokenBucket(long maxCapacity, long refillRate) {
        this.maxCapacity = maxCapacity;
        this.refillRate = refillRate;
        capacity = maxCapacity;
    }

    public boolean consumePacket(JPacket packet) {
        boolean result = consumePacketWithoutRefill(packet);
        refill(packet.getCaptureHeader().timestampInMillis());
        return result;
    }

    public boolean consumePacketWithoutRefill(JPacket packet) {
        boolean result = false;
        //System.out.println("Capacity before: "+capacity);
        if(capacity >= packet.getPacketWirelen()) {
            capacity -= packet.getPacketWirelen();
            result = true;
        }
        //System.out.println("Packet exceeds capacity: "+!result);
        //System.out.println("Capacity after packet: "+capacity);
        return result;
    }

    protected long refill(long timestamp) {
        if(lastTimestamp == -1) {
            lastTimestamp = timestamp;
            return 0;
        }
        long delta = timestamp - lastTimestamp;
        lastTimestamp = timestamp;

        long newCapacity = capacity + Math.round(delta*refillRate/1000);
        long deltaTokens = newCapacity - maxCapacity;

        if(deltaTokens > 0) {
            capacity = maxCapacity;
            return deltaTokens;
        } else {
            capacity = newCapacity;
            return 0;
        }

        //capacity = Math.min(maxCapacity, capacity + Math.round(delta*refillRate/1000));


        //System.out.println("Capacity after refill: "+capacity);
    }

    protected void refillwithTokens(long numTokens) {
        if(numTokens > 0) {
            capacity = Math.min(maxCapacity, capacity + numTokens);
        }
    }

}
