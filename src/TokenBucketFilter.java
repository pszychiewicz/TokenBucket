import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.util.PcapPacketArrayList;

public class TokenBucketFilter {

    public static void main(String[] args) {
        final String FILENAME = "soundUDP.pcap";
        final StringBuilder errbuf = new StringBuilder();

        final Pcap pcap = Pcap.openOffline(FILENAME, errbuf);
        if (pcap == null) {
            System.err.println(errbuf); // Error is stored in errbuf if any
            return;

        }
        PcapPacketArrayList packets = new PcapPacketArrayList();
        pcap.loop(Pcap.LOOP_INFINITE, packets, null);

        meanThroughput(packets);
        findMinCapacity(packets, 100000000000000l, 0, 100);
        findMinFillRate(packets, 2000, 1000);
        findMinFillRate(packets, 5000, 1000);
        findMinFillRate(packets, 10000, 1000);
        findMinFillRate(packets, 20000, 1000);
        findMinFillRate(packets, 40000, 1000);
        findMinFillRate(packets, 60000, 1000);
        findMinFillRate(packets, 80000, 1000);
        findMinFillRate(packets, 100000, 1000);
        findMinFillRate(packets, 200000, 1000);
        findMinFillRate(packets, 300000, 1000);
        findMinFillRate(packets, 500000, 1000);
        findMinFillRate(packets, 5000000, 1000);
        findMinFillRate(packets, 10000000, 1000);
        findMinFillRate(packets, 20000000, 1000);
        findMinFillRate(packets, 30000000, 1);
        findMinFillRate(packets, 50000000, 1);
        findMinFillRate(packets, 100000000, 1);



     pcap.close();

    }

    private static void meanThroughput(PcapPacketArrayList packets) {
        long startTime = packets.get(0).getCaptureHeader().timestampInMillis();
        long endTime = packets.get(packets.size() - 1).getCaptureHeader().timestampInMillis();
        long sizeSum = 0;
        for (JPacket packet : packets) {
            sizeSum += packet.getPacketWirelen();
        }
        long mean = sizeSum / ((endTime - startTime) / 1000);
        System.out.println("Srednia przeplywnosc [B/s] : " + mean);
    }

    //wazne zeby podac initailCapacity takie kotre jest za male bo inaczej nie znajdziemy najlepszego
    private static void findMinCapacity(PcapPacketArrayList packets, long fillrate, int capacityIncrementVal) {
        findMinCapacity(packets, fillrate, 0, capacityIncrementVal);
    }

    private static void findMinCapacity(PcapPacketArrayList packets, long fillrate, int initialCapacity, int capacityIncrementVal) {
        long capacity = initialCapacity;
        while (true) {
            capacity += capacityIncrementVal;
            SingleTokenBucket tokenBucket = new SingleTokenBucket(capacity, fillrate);
            boolean allConsumed = true;
            for (JPacket packet : packets) {
                if (!tokenBucket.consumePacket(packet)) {
                    allConsumed = false;
                    break;
                }
            }
            //System.out.println("Zakonczono petle dla pojemnosci "+capacity);
            if (allConsumed) {
                System.out.println("Znaleziono najmniejsza pojemnosc dla fillRate == "+fillrate+": " + capacity);
                break;
            }
        }
    }

    private static void findMinFillRate(PcapPacketArrayList packets, int capacity, int fillRateInctrementVal) {
        findMinFillRate(packets, capacity, 0, fillRateInctrementVal);
    }

    private static void findMinFillRate(PcapPacketArrayList packets, int capacity, long initialFillRate, int fillRateInctrementVal) {
        long fillRate = initialFillRate;
        while (true) {
            fillRate += fillRateInctrementVal;
            SingleTokenBucket tokenBucket = new SingleTokenBucket(capacity, fillRate);
            boolean allConsumed = true;
            for (JPacket packet : packets) {
                if (!tokenBucket.consumePacket(packet)) {
                    allConsumed = false;
                    break;
                }
            }
            //System.out.println("Zakonczono petle dla fillRate "+fillRate);
            if (allConsumed) {
                System.out.println("Minimalny fillRate dla capacity == "+capacity+": " + fillRate);
//                System.out.println(capacity);
//                System.out.println(fillRate);
                break;
            }
        }
    }

}
