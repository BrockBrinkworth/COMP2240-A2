import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Thread;

public class P1 {
    public static void run(String path) {
        Scanner scan;

        try {
            File file = new File(path);
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println(path);
            return;
        }

        int nNorthWAR = 0;
        int nSouthWAR = 0;
        int nEastWAR = 0;
        int nWestWAR = 0;

        while (scan.hasNext()) // Scan for tokens
        {
            String s = scan.next(); // Scanner
            String[] inputs = s.trim().split("="); // get 2 strings

            String direction = inputs[0];

            if (direction.equals("N")) {
                nNorthWAR = Integer.parseInt(inputs[1]);
            } else if (direction.equals("S")) {
                nSouthWAR = Integer.parseInt(inputs[1]);
            } else if (direction.equals("E")) {
                nEastWAR = Integer.parseInt(inputs[1]);
            } else if (direction.equals("W")) {
                nWestWAR = Integer.parseInt(inputs[1]);
            }
        }
        scan.close();

        if (nNorthWAR + nSouthWAR + nWestWAR + nEastWAR <= 0) {
            System.out.println("Input given is wrong");
            return;
        }

        Track northTrack = new NorthTrack();
        Track eastTrack = new EastTrack();
        Track southTrack = new SouthTrack();
        Track westTrack = new WestTrack();

        northTrack.setOppositeTrack(eastTrack);
        eastTrack.setOppositeTrack(northTrack);
        southTrack.setOppositeTrack(westTrack);
        westTrack.setOppositeTrack(southTrack);

        Queue<Track> tracks = new LinkedList<>();

        int id = 1;

        while (nNorthWAR + nEastWAR + nSouthWAR + nWestWAR > 0) {
            if (nNorthWAR > 0) {
                northTrack.add(new WAR(id));
                tracks.add(northTrack);
                nNorthWAR--;
                id++;
            }

            if (nWestWAR > 0) {
                westTrack.add(new WAR(id));
                tracks.add(westTrack);
                nWestWAR--;
                id++;
            }

            if (nEastWAR > 0) {
                eastTrack.add(new WAR(id));
                tracks.add(eastTrack);
                nEastWAR--;
                id++;
            }

            if (nSouthWAR > 0) {
                southTrack.add(new WAR(id));
                tracks.add(southTrack);
                nSouthWAR--;
                id++;
            }

        }

        int nTrack1 = 0;
        int nTrack2 = 0;

        Track prevTrack = null;
        WAR prevWar = null;

        int MAX_1 = 150;
        int MAX_2 = 150;

        while (nTrack1 < MAX_1 || nTrack2 < MAX_2) {
            Track curTrack = tracks.remove();

            /*
             * if (curTrack.getTrackName().equals("Track1") && nTrack1 >= MAX_1) {
             * tracks.add(curTrack); continue; } else if
             * (curTrack.getTrackName().equals("Track2") && nTrack2 >= MAX_2) {
             * tracks.add(curTrack); continue; }
             */

            WAR curWar = curTrack.remove();

            if (prevTrack != null) {
                prevTrack.addToOppositeTrack(prevWar);
            }

            if (curWar != null) {
                System.out.format("WAR-%d (%s) Crossing intersection Checkpoint 3.\n", curWar.getId(),
                        curTrack.getWarStatus());
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else {
                prevTrack = null;
                prevWar = null;
                tracks.add(curTrack);
                continue;
            }

            if (curTrack.getTrackName().equals("Track1")) {
                nTrack1++;
            } else {
                nTrack2++;
            }

            System.out.format("Total crossed in Track1 %d Track2 %d\n", nTrack1, nTrack2);

            prevTrack = curTrack;
            prevWar = curWar;
            tracks.add(curTrack);
        }

        return;
    }

    public static void main(String[] args) {
        // run("P1-1in.txt"); // For testing :/

        for (String filePath : args) {
            run(filePath);
        }

    }

}