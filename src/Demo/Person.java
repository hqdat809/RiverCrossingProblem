package Demo;



public class Person {                                      //Hackers and serfs are derived from this class.

    public String ad;                                      //common instance variables for hackers and serfs
    public Semaphors smphrs;
    public boolean isCaptain = false;

    public Person(String ad, Semaphors smphrs) {          //constructor
        this.ad = ad;
        this.smphrs = smphrs;
    }
    
    public void Board() throws InterruptedException {     // Board() function that every thread should refer to

    }

    public void rowBoat() throws InterruptedException {   // rowBoat() function that only one thread should refer to, ie the captain

        System.out.println( "rowBoat() function worked\n" +
                            "#########################################\n\n\n");
    }
}
