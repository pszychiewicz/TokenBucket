import org.jnetpcap.packet.JPacket;

interface TokenBucket {
    public abstract boolean consumePacket(JPacket packet);
}
