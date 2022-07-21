import java.util.LinkedList;
import java.util.Queue;
import java.lang.Thread;

public class Track
{
    private Queue<WAR> track;
    private String trackName;
    private String warStatus;
    private String destination;
    private Track oppositeTrack;

    // Constructor
    public Track(String trackName, String warStatus, String destination)
    {
        this.track = new LinkedList<>();
        this.trackName = trackName;
        this.warStatus = warStatus;
        this.destination = destination;
    }

    // Adding to Queue
    public void add(WAR w)
    {
        System.out.format("WAR-%d (%s) Waiting at the Intersection. Going towards %s.\n", 
            w.getId(), this.warStatus, this.destination);
        track.add(w);
    }

    public WAR remove()
    {
        WAR w;
        try
        {
            w = track.remove();
        }
        catch (Exception e) {
            return null;
        }
        System.out.format("WAR-%d (%s) Crossing intersection Checkpoint 1.\n", 
            w.getId(), this.warStatus);

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            //TODO: handle exception
            }

        System.out.format("WAR-%d (%s) Crossing intersection Checkpoint 2.\n", 
            w.getId(), this.warStatus);

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        return w;
    }

    public WAR peek()
    {
        return track.peek();
    }

    public String getTrackName()
    {
        return this.trackName;
    }

    public void setOppositeTrack(Track t)
    {
        this.oppositeTrack = t;
    }

    public void addToOppositeTrack(WAR w)
    {
        if (this.oppositeTrack != null)
        {
            this.oppositeTrack.add(w);
        }
    }

    public String getWarStatus()
    {
        return this.warStatus;
    }

    // Observer that tracks background information without showing it
    // Intersection does not allow multiple WARS
    // Storage 1 & 2
    // Dock 1 & 2
    // WARs can be loaded and unloaded
    // Track 1 & 2
    // Threads simulate concurrent WARs
    // Semaphores to implement algortihm to prevent deadlock
    // Loading/Unloading is instant
    // 3 checkpoints in the intersection, 200ms delay after each checkpoint, 
    // tracker of WARs passing

}