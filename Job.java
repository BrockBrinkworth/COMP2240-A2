public class Job {
    private String id;
    private int processTime;
    private PrintType printType;
    private int currentTimeLeft = 0;
    private int startTime = 0;

    public Job(String id, int processTime) {
        this.id = id;
        this.processTime = processTime;
        this.currentTimeLeft = processTime;

        if (id.contains("C")) {
            this.printType = PrintType.COLOUR;
        } else {
            this.printType = PrintType.MONOCHROME;
        }
    }

    public void update() {
        if (this.currentTimeLeft > 0) {
            this.currentTimeLeft--;
        }
    }

    public boolean isDone() {
        if (this.currentTimeLeft > 0) {
            return false;
        }

        return true;
    }

    public String getId() {
        return id;
    }

    public int getProcessTime() {
        return processTime;
    }

    PrintType getPrintType() {
        return this.printType;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    
}