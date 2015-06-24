import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.util.PcapPacketArrayList;

import java.util.ArrayList;

public class TokenBucketFilter {

    public static void main(String[] args) {
        final String FILENAME = "videoUDP.pcap";
        final StringBuilder errbuf = new StringBuilder();

        final Pcap pcap = Pcap.openOffline(FILENAME, errbuf);
        if (pcap == null) {
            System.err.println(errbuf); // Error is stored in errbuf if any
            return;

        }
        PcapPacketArrayList packets = new PcapPacketArrayList();
        pcap.loop(Pcap.LOOP_INFINITE, packets, null);

        //doMeasurements(packets);

       // meanThroughput(packets);
//        test1(packets);
        peakBitrateX(packets);
        peakBitrate(packets);

       // findMinFillRate(packets, findMinCapacity(packets, 100000000000000l, 0, 1), 1);
       // findMinCapacity(packets, 28663, 0, 1);
//        videoSoundUDP(packets);

        /*findMinCapacity(packets, 28700, 0, 1);
        findMinCapacity(packets, 28750, 0, 1);
        findMinCapacity(packets, 28800, 0, 1);
        findMinCapacity(packets, 28850, 0, 1);
        findMinCapacity(packets, 28900, 0, 1);
        findMinCapacity(packets, 29000, 0, 1);
        findMinCapacity(packets, 29100, 0, 1);
        findMinCapacity(packets, 29150, 0, 1);
        findMinCapacity(packets, 29200, 0, 1);
        findMinCapacity(packets, 29250, 0, 1);
        findMinCapacity(packets, 29300, 0, 1);
        findMinCapacity(packets, 29400, 0, 1);
        findMinCapacity(packets, 29500, 0, 1);
        findMinCapacity(packets, 29600, 0, 1);
        findMinCapacity(packets, 29700, 0, 1);
        findMinCapacity(packets, 29800, 0, 1);
        findMinCapacity(packets, 29900, 0, 1);
        findMinCapacity(packets, 30000, 0, 1);
        findMinCapacity(packets, 30100, 0, 1);
        findMinCapacity(packets, 30200, 0, 1);
        findMinCapacity(packets, 30300, 0, 1);
        findMinCapacity(packets, 30400, 0, 1);
        findMinCapacity(packets, 30500, 0, 1);
        findMinCapacity(packets, 30700, 0, 1);
        findMinCapacity(packets, 30800, 0, 1);
        findMinCapacity(packets, 30900, 0, 1);*/
        /*findMinCapacity(packets, 26000, 0, 1);
        findMinCapacity(packets, 28000, 0, 1);
        findMinCapacity(packets, 30000, 0, 1);
        findMinCapacity(packets, 32000, 0, 1);
        findMinCapacity(packets, 34000, 0, 1);
        findMinCapacity(packets, 36000, 0, 1);
        findMinCapacity(packets, 38000, 0, 1);
        findMinCapacity(packets, 40000, 0, 1);*/
      /*  findMinCapacity(packets, 300000, 0, 1);
        findMinCapacity(packets, 326000, 0, 1);
        findMinCapacity(packets, 330000, 0, 1);
        findMinCapacity(packets, 380000, 0, 1);
        findMinCapacity(packets, 400000, 0, 1);
        findMinCapacity(packets, 440000, 0, 1);
        findMinCapacity(packets, 450000, 0, 1);*/

//        findSecondBucketParams(packets, 16900, 0, 100, 1600, 285000);
//        findMinFillRate(packets, 2000, 1000);
//        findMinFillRate(packets, 5000, 1000);
//        findMinFillRate(packets, 10000, 1000);
//        findMinFillRate(packets, 20000, 1000);
      /*  findMinFillRate(packets, 40000, 1000);
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
        findMinFillRate(packets, 100000000, 1);*/



     pcap.close();

    }

    private static void doMeasurements(PcapPacketArrayList packets) {
        ArrayList<String> list = new ArrayList<String>();
        double mean = meanThroughput(packets);
        long fillrate = findMinFillRate(packets, findMinCapacity(packets, 1000000000000000l, 0, 1), 893000, 1);

        for(int i = (int)mean; i <= fillrate + 40000; i+=20000) {
            String result = i + ", " + findMinCapacity(packets, i, 0, 1);
            list.add(result);
        }

        System.out.println("FILLRATE, CAPACITY");
        for(String s : list) {
            System.out.println(s);
        }
    }

    private static void videoSoundUDP(PcapPacketArrayList packets) {
        ArrayList<String> list = new ArrayList<String>();

        for(int i = 26000; i <= 28000; i+=50) {
            String result = i + ", " + findMinCapacity(packets, i, 0, 1);
            list.add(result);
        }
        System.out.println("FILLRATE, CAPACITY");
        for(String s : list) {
            System.out.println(s);
        }
    }

        private static void soundUDP(PcapPacketArrayList packets) {
        findMinFillRate(packets, findMinCapacity(packets, 100000000000000l, 1450, 1), 1);
        findMinCapacity(packets, 16868, 0, 1);
        findMinCapacity(packets, 20000, 0, 1);
        findMinCapacity(packets, 50000, 0, 1);
        findMinCapacity(packets, 80000, 0, 1);
        findMinCapacity(packets, 100000, 0, 1);
        findMinCapacity(packets, 150000, 0, 1);
        findMinCapacity(packets, 200000, 0, 1);
        findMinCapacity(packets, 250000, 0, 1);
        findMinCapacity(packets, 285000, 0, 1);
        findMinCapacity(packets, 300000, 0, 1);
        findMinCapacity(packets, 326000, 0, 1);
        findMinCapacity(packets, 3300000, 0, 1);
    }

    private static double meanThroughput(PcapPacketArrayList packets) {
        long startTime = packets.get(0).getCaptureHeader().timestampInNanos();
        long endTime = packets.get(packets.size() - 1).getCaptureHeader().timestampInNanos();
        long sizeSum = 0;
        for (JPacket packet : packets) {
            sizeSum += packet.getPacketWirelen();
        }
        long deltaTime = endTime - startTime;
        double mean = sizeSum * 1000000000l / deltaTime;

        System.out.println("Srednia przeplywnosc : " + mean + "  [B/s] | " + mean / 1000000 + "[MB/s]");
        return mean;

    }

    private static void peakBitrate1(PcapPacketArrayList packets) {
        int counter = 0;
        long lastDifferentTimestamp = -1;
        long lastTimestamp = -1;
        long peak = 0;
        long deltaTime = 0;
        for (JPacket packet : packets) {
            long currentTimestamp = packet.getCaptureHeader().timestampInNanos();
            //System.out.println("CurrentTimestamp: " + currentTimestamp);
            if(lastTimestamp == -1) {
                lastTimestamp = currentTimestamp;
            } else {
                if(currentTimestamp == lastTimestamp) {
                    counter++;
                    if(lastDifferentTimestamp == -1)
                        continue;
                    deltaTime = currentTimestamp - lastDifferentTimestamp;
//                    continue;
                } else {
                    lastDifferentTimestamp = lastTimestamp;
                    deltaTime = currentTimestamp - lastTimestamp;
                }
                long tmpPeak = packet.getPacketWirelen() * 1000000000l / deltaTime ;

//                System.out.println("Szczytowa szybkość TMP [B/s] : " + tmpPeak + " | " + tmpPeak/1000000l + "[MB/s]");

                if (tmpPeak > peak)
                    peak = tmpPeak;
                lastTimestamp = currentTimestamp;
            }
        }
        System.out.println("Szczytowa szybkość [B/s] : " + peak + " | " + peak/1000000l + "[MB/s]");
        System.out.println("counter "+counter);
    }

    private static void test(PcapPacketArrayList packets) {
        double sumBytes = 0;
        double sumTime = 0;
        double lastTimestamp = -1;
        double peak = 0;

        for (JPacket packet : packets) {
            if(lastTimestamp == -1) {
                lastTimestamp = packet.getCaptureHeader().timestampInNanos();
            } else {
                sumTime += packet.getCaptureHeader().timestampInNanos() - lastTimestamp;
                lastTimestamp = packet.getCaptureHeader().timestampInNanos();
                sumBytes += packet.getPacketWirelen();
                if (sumTime >= 1000000) {
//                long peak = sumBytes * 1000000000l / sumTime ;
                    double tmpNS = sumBytes / sumTime; //[B/ns]
                    double tmpS = tmpNS * 1000000000; //[B/s]

                    if(tmpS > peak)
                        peak = tmpS;
                    sumBytes = 0;
                    sumTime = 0;
                }
            }
        }
        System.out.println("Szczytowa szybkość : " + peak + "  [B/s] | " + peak / 1000000 + "[MB/s]");

    }

    private static void peakBitrateX(PcapPacketArrayList packets) {
        double deltaTime;
        double lastDifferentTimestamp = -1;
        double lastTimestamp = -1;
        double peak = 0;

        for (JPacket packet : packets) {
            if(lastTimestamp == -1) {
                lastTimestamp = packet.getCaptureHeader().timestampInNanos();
            } else {
                double currentTimestamp = packet.getCaptureHeader().timestampInNanos();
                if(packet.getCaptureHeader().timestampInNanos() == lastTimestamp) {
                    if(lastDifferentTimestamp == -1)
                        continue;
                    deltaTime = currentTimestamp - lastDifferentTimestamp;
                } else {
                    lastDifferentTimestamp = lastTimestamp;
                    deltaTime = currentTimestamp - lastTimestamp;
                }

                lastTimestamp = packet.getCaptureHeader().timestampInNanos();
                double bytes = packet.getPacketWirelen();

                    double tmpNS = bytes / deltaTime; //[B/ns]
                    double tmpS = tmpNS * 1000000000; //[B/s]

                    if(tmpS > peak)
                        peak = tmpS;
            }
        }
        System.out.println("Szczytowa szybkość : " + peak + "  [B/s] | " + peak / 1000000 + "[MB/s]");

    }

    private static void peakBitrate(PcapPacketArrayList packets) {
        double deltaTime;
        double previousPacketBytes = 0;
        double lastDifferentTimestamp = -1;
        double lastTimestamp = -1;
        double peak = 0;

        for (JPacket packet : packets) {
            if(previousPacketBytes == 0 && lastTimestamp == -1) {
                previousPacketBytes = packet.getPacketWirelen();
                lastTimestamp = packet.getCaptureHeader().timestampInNanos();
            } else {
                double currentTimestamp = packet.getCaptureHeader().timestampInNanos();
                if(currentTimestamp == lastTimestamp) {
                    if(lastDifferentTimestamp == -1)
                        continue;
                    deltaTime = currentTimestamp - lastDifferentTimestamp;
                } else {
                    lastDifferentTimestamp = lastTimestamp;
                    deltaTime = currentTimestamp - lastTimestamp;
                }
                lastTimestamp = packet.getCaptureHeader().timestampInNanos();

                double tmpNS = previousPacketBytes / deltaTime; //[B/ns]
                double tmpS = tmpNS * 1000000000; //[B/s]

                if(tmpS > peak)
                    peak = tmpS;

                previousPacketBytes = packet.getPacketWirelen();
            }
        }
        System.out.println("Szczytowa szybkość : " + peak + "  [B/s] | " + peak / 1000000 + "[MB/s]");

    }

    //wazne zeby podac initailCapacity takie kotre jest za male bo inaczej nie znajdziemy najlepszego
    private static long findMinCapacity(PcapPacketArrayList packets, long fillrate, int capacityIncrementVal) {
       return findMinCapacity(packets, fillrate, 0, capacityIncrementVal);
    }

    private static long findMinCapacity(PcapPacketArrayList packets, long fillrate, int initialCapacity, int capacityIncrementVal) {
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
                return capacity;
            }
        }
    }

    private static long findMinFillRate(PcapPacketArrayList packets, long capacity, int fillRateInctrementVal) {
        return findMinFillRate(packets, capacity, 0, fillRateInctrementVal);
    }

    private static long findMinFillRate(PcapPacketArrayList packets, long capacity, long initialFillRate, int fillRateInctrementVal) {
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
                return fillRate;
//                System.out.println(capacity);
//                System.out.println(fillRate);
//                break;
            }
        }
    }

    //Dual TB meanThroughput is the initial fillRate
    private static void findSecondBucketParams(PcapPacketArrayList packets, int fillrate, int initialCapacity, int capacityIncrementVal, int firstBucketCapacity, int firstBucketFillrate) {
        long capacity = initialCapacity;
        while (true) {
            capacity += capacityIncrementVal;
            DualTokenBucketWithDualRate dualTokenBucket = new DualTokenBucketWithDualRate(new SingleTokenBucket(firstBucketCapacity, firstBucketFillrate), new SingleTokenBucket(capacity, fillrate));
            boolean allConsumed = true;
            for (JPacket packet : packets) {
                if (!dualTokenBucket.consumePacket(packet)) {
                    allConsumed = false;
                    break;
                }
            }
            //System.out.println("Zakonczono petle dla pojemnosci "+capacity);
            if (allConsumed) {
                System.out.println("DUAL TB [cap,fill][" + firstBucketCapacity + ", " + firstBucketFillrate + "] - Znaleziono najmniejsza pojemnosc dla fillRate == "+fillrate+": " + capacity);
                break;
            }
        }
    }

}
