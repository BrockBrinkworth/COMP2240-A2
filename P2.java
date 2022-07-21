import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P2 {

    private static void run(String path) {
        Scanner scan;

        try {
            File file = new File(path);
            scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println(path);
            return;
        }

        int nJobs = Integer.parseInt(scan.nextLine().trim());

        Queue<Job> jobs = new LinkedList<>();

        for (int i = 0; i < nJobs; i++) {
            String[] lineSplit = scan.nextLine().trim().split(" ");

            String id = lineSplit[0];
            int processTime = Integer.parseInt(lineSplit[1]);

            jobs.add(new Job(id, processTime));
        }

        Printer printer = new Printer();

        while (!jobs.isEmpty() || !printer.hasNoJobs()) {
            if (printer.hasNoJobs()) {
                printer.setMode(jobs.peek().getPrintType());
            }

            if (printer.stream1HasNoJob() && !jobs.isEmpty() && jobs.peek().getPrintType() == printer.getMode()) {
                printer.setStream1(jobs.remove());
            }

            if (printer.stream2HasNoJob() && !jobs.isEmpty() && jobs.peek().getPrintType() == printer.getMode()) {
                printer.setStream2(jobs.remove());
            }

            if (printer.stream3HasNoJob() && !jobs.isEmpty() && jobs.peek().getPrintType() == printer.getMode()) {
                printer.setStream3(jobs.remove());
            }

            printer.update();
        }

        printer.printEnd();

        scan.close();

    }

    public static void main(String[] args) {
        // run("P2-1in.txt");
        // run("./extra_datafile/test.txt"); // extra testing
        // For testing :/

        for (String filePath : args) {
            run(filePath);
        }
    }
}