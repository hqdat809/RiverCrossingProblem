package Demo;

public class Hacker extends Person implements Runnable {

    public Hacker(String ad, Semaphors smphrs) {
        super(ad, smphrs);
    }

    @Override
    public void run() {
        this.HackerArrives();
    }

    public void HackerArrives() {
        try {

            Thread.sleep((int) (Math.random() * 10));                   // Increases randomness for each thread

            smphrs.mutex.acquire();                                      // chỉ cho phép một thread tại một thời điểm

            smphrs.hackers++;                                        // tăng số lượng hacker đang chờ xử lý

            if (smphrs.hackers == 4) {                                 // Nếu số lượng hacker là bốn, điều kiện được đáp ứng, không được phép nhập thêm.
                smphrs.hackerQueue.release(4);                       // người tham gia thứ tư giải phóng các tin tặc đang chờ xử lý khi điều kiện được đáp ứng.
                smphrs.hackers = 0;                                  // Số lượng chờ được đặt lại, vì tất cả các tin tặc đang chờ đều được sử dụng
                isCaptain = true;                                    // Cờ hiệu

            } else if (smphrs.hackers == 2 && smphrs.employee >= 2) {       // đáp ứng điều kiện, ko cho phép lên thuyền nữa.
                smphrs.hackerQueue.release(2);                       // người bước vào thứ tư giải phóng hai tin tặc từ đuôi hackerQueue.
                smphrs.employQueue.release(2);                         // ngườibước vào thứ tư giải phóng hai nhân viên từ đuôi employQueue..
                smphrs.employee -= 2;                                   // sô nhân viên đợi giảm đi 2
                smphrs.hackers = 0;                                  //số hacker đợi giảm về 0, vì tất cả nhân viên đợi đều đã lên thuyền
                isCaptain = true;                                    //Cờ hiệu 

            } else {
                smphrs.mutex.release();                              // Nếu điều kiện không đáp ứng thì được thêm một người nữa và add vào hàng đợi
            }                                                        

            smphrs.hackerQueue.acquire();                                // Các hacker đã sẵn sàng chờ đợi trong hàng đợi ở đây, người cuối cùng có thể được thêm vào ở đk if ở trên

            System.out.println(ad + " Boarded on the Boat");

            Board();                                                    

            smphrs.barrier.acquire();                                    // lấp đầy 1 vị trí trên thuyền

            if (this.isCaptain) {                                        // Calls rowBoat function to incoming thread captan
                Thread.sleep(1000);                   
                rowBoat();
                smphrs.barrier.release(4);                                  // xoá sạch vị trí trên thuyền để chở 4 người tiếp theo
                smphrs.mutex.release();                                  // Nó đảm bảo rằng thread đầu tiên đi qua để bốn thread tiếp theo có thể bắt đầu và thread cuối cùng cũng kết thúc.
            }

        } catch (Exception e) {
        }

    }

}
