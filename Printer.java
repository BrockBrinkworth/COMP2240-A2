public class Printer {
    private PrintType mode = PrintType.MONOCHROME;
    private Job stream1 = null;
    private Job stream2 = null;
    private Job stream3 = null;
    private int internalClock = 0;

    public Printer() {
        // do nothing
    }

    public boolean hasNoJobs() {
        if (stream1 == null && stream2 == null && stream3 == null) {
            return true;
        }

        return false;
    }

    public boolean stream1HasNoJob() {
        if (stream1 == null) {
            return true;
        }

        return false;
    }

    public void setStream1(Job job) {
        this.stream1 = job;
        System.out.format("(%d) %s uses head 1 (time: %d)\n", internalClock, stream1.getId(), stream1.getProcessTime());
    }

    public boolean stream2HasNoJob() {
        if (stream2 == null) {
            return true;
        }

        return false;
    }

    public void setStream2(Job job) {
        this.stream2 = job;
        System.out.format("(%d) %s uses head 2 (time: %d)\n", internalClock, stream2.getId(), stream2.getProcessTime());
    }

    public boolean stream3HasNoJob() {
        if (stream3 == null) {
            return true;
        }

        return false;
    }

    public void setStream3(Job job) {
        this.stream3 = job;
        System.out.format("(%d) %s uses head 3 (time: %d)\n", internalClock, stream3.getId(), stream3.getProcessTime());
    }

    public PrintType getMode() {
        return mode;
    }

    public void setMode(PrintType mode) {
        this.mode = mode;
    }

    public void update() {

        internalClock++;
        /*
         * try { Thread.sleep(900); } catch (Exception e) { // do nothing }
         */

        if (stream1 != null) {
            stream1.update();

            if (stream1.isDone()) {
                stream1 = null;
            }
        }

        if (stream2 != null) {
            stream2.update();

            if (stream2.isDone()) {
                stream2 = null;
            }
        }

        if (stream3 != null) {
            stream3.update();

            if (stream3.isDone()) {
                stream3 = null;
            }
        }
    }

    public void printEnd() {
        System.out.format("(%d) DONE\n", internalClock);
    }
}