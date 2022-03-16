package Demo;




import java.util.concurrent.Semaphore;

public class Semaphors {

    public Semaphore mutex       = new Semaphore(1);       // semaphores to use
    public Semaphore barrier     = new Semaphore(4);
    public Semaphore hackerQueue = new Semaphore(0);
    public Semaphore employQueue   = new Semaphore(0);

    public int hackers = 0;                                // số hacker đợi xử lý
    public int employee   = 0;                                // số nhân viên đợi xử lý

}
