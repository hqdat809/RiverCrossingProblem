package Demo;




public class App {
    public static void main(String[] args) throws InterruptedException {
        
        
        int numberOfHacker = 8;                                                    
        int numberOfSerfs  = 8;                                                    

        Person [] hackers = new Hacker[numberOfHacker];                            
        Person [] serfs   = new Employee[numberOfSerfs];                               

        Thread [] thrdHackers = new Thread[numberOfHacker];                        
        Thread [] thrdSerfs   = new Thread[numberOfSerfs];
        
        Semaphors smphrs = new Semaphors();                                       

        for (int i = 0; i < hackers.length ; i++) {                                
            thrdHackers[i] = new Thread( new Hacker( ("hacker_" + i), smphrs) );   
        }

        for (int i = 0; i < serfs.length ; i++) {
            thrdSerfs[i] = new Thread( new Employee( ("employ_" + i), smphrs) );
        }

        for (int i = 0; i < Math.max(serfs.length, hackers.length); i++) {          
            if(i < hackers.length)
                thrdHackers[i].start();

            if(i < serfs.length)
                thrdSerfs[i].start();
        }

        for (int i = 0; i < Math.max(serfs.length, hackers.length); i++) {          
            if(i < hackers.length)
                thrdHackers[i].join();

            if(i < serfs.length)
                thrdSerfs[i].join();
        }

        System.out.println("All Passengers left by Boat !!!\n\n");
        
                    
    }
}
