package kr.or.ddit.basic;

public class T13ThreadStopTest {
   public static void main(String[] args) {
//      ThreadStopEx1 th = new ThreadStopEx1();
////      Thread th = new ThreadStopEx1(); <= 일때는 Thread안에 setStop이 없어서 안됨 그래서 ThreadStopEx1이걸로 바꿔줌
//      // ThreadStopEx1 안에 setStop을 만들어 줘으니깐 
//
//      
//      th.start();
//      
//      try {
//         Thread.sleep(1000);
//      } catch (InterruptedException ex) {
//         ex.printStackTrace();
//      }
//      
////      th.stop();//강제스탑시키는거 앞으로 이거 쓰지마세요 디프리케이트
//      th.setStop(true);
      
      ThreadStopEx2 th2 = new ThreadStopEx2();
      th2.start();
      
      try {
         Thread.sleep(1000);
      } catch (InterruptedException ex) {
         ex.printStackTrace();
      }
      th2.interrupt();
   }
}

class ThreadStopEx1 extends Thread {
   
   private boolean stop;//false 임
      
   public void setStop(boolean stop) {
      this.stop = stop;
   }

   @Override
   public void run() {
      while(!stop) {//true 임
         System.out.println("스레드 처리 중~");
      }
      
      System.out.println("자원 정리 중~");
      System.out.println("실행 종료.");
   }
}//인터럭트 방해하다라는 뜻
// interrupt() 메소드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread {
   
    @Override
   public void run() {
       /*
       // 방법 1 => sleep()메소드나 join()메소드 등을 사용했을때 interrupt()메소드를
       //             호출하면 InterruptedException 이 발생한다.
       try {
          while(true) {
             System.out.println("스레드 처리중...");
             Thread.sleep(1);
          }
          
       }catch(InterruptedException ex) {}
       
       System.out.println("자원 정리 중~");
       System.out.println("실행 종료.");
       */
       
       // 방벙 2 => interrupt()메소드가 호출되었는지 검사하기
       while(true) {
          System.out.println("스레드 처리중...");
          
//          // 검사방법 1 => 스레드객체의 인스턴스 메소드를 이용하는 방법
//          if(this.isInterrupted()) {//interrupt() 호출되면 true 리턴 인터럭트 걸렸는지 확인하는거
//             System.out.println("인스턴스 메소드 Interrupted 호출됨");
//             break;
//          }
          
          	//검사방법 2 => 스레드의 정적 메서드를 이용하는 방법
           if(Thread.interrupted()) {
        	  System.out.println("정적 메서드 interrupted 호출됨");
        	  break;
          }
       }
       System.out.println("자원 정리 중~");
       System.out.println("실행 종료.");
   } 
}