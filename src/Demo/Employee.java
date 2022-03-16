package Demo;




public class Employee extends Person implements Runnable {

    public Employee(String ad, Semaphors smphrs) {
        super(ad, smphrs);
    }


    @Override
    public void run() {
        this.EmployeeArrives();
    }
    
    public void EmployeeArrives() {
        try {

            Thread.sleep((int) (Math.random() * 10));                  

            smphrs.mutex.acquire();                                    

            smphrs.employee++;                                        

            if (smphrs.employee == 4) {                                  
                smphrs.employQueue.release(4);                       
                smphrs.employee = 0;                                 
                isCaptain = true;                                  
            } else if (smphrs.employee == 2 && smphrs.hackers >= 2) {      

                smphrs.employQueue.release(2);                       
                smphrs.hackerQueue.release(2);                     
                smphrs.hackers -= 2;                                
                smphrs.employee = 0;                                  
                isCaptain = true;                                   
            } else {
                smphrs.mutex.release();                            
            }                                                      

            smphrs.employQueue.acquire();                                 

            System.out.println(ad + " Boarded on the Boat");
            Board();                                                   

            smphrs.barrier.acquire();                                  

            if (this.isCaptain) {                                         

                Thread.sleep(1000);                                    
                rowBoat();
                smphrs.barrier.release(4);                              
                smphrs.mutex.release();                                
            }                                                          

        } catch (Exception e) {
        }

    }


}
