package kr.or.ddit.basic;

public class T02ThreadTest {
	public static void main(String[] args) {
		//멀티 스레드 프로그램 방식
//스레드는 스택의 방식을 사용 -> main 에서 start() 을 하면 새로 생성하는 스레드의 스택이 생성되고 거기서 -> void run메소드 실행 
//실행이 끝나면 다시 main thread로 돌아옴   // run이 끝나면 각자의 스레드는 사라짐 Main thread 같은 경우 main 이 완전히 끝나면 사라짐
		
		 //메인스레드 mythread1 ,2 ,3 총 4개의 스레드가 있음
		//스레드 생성 
		//방법 1 : Thread 클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의
		//       start()메서드를 실행한다.
		MyThread1 th1 = new MyThread1(); //MyThread1 스레드 생성
		th1.start();
		
		//방법 2 : Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
		//       이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 파라미터로
		//       넘겨준다. 이때 생성된 Thread 객체의 인스턴스의 start() 실행한다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r); //runnable 객체 이용 쓰레드 객체 만듬 
		th2.start(); //실행 
		
		//방법 3 : 익명클래스를 이용하는 방법 
		//       Runnable 인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할때
		//       매개변수로 넘겨준다.
		//두번째 방법은 runnable 해서 mythread2를 만든거고 3번째 방법은 익명클래스 이용
		Thread th3 = new Thread(new Runnable /*어나니머스 Runnable선택 */() {
			
			@Override
			public void run() {
				for(int i=1; i<200; i++) {
					System.out.print("@");
					
					try {
						//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
						//                     시간은 밀리세컨드 단위를 사용.
						//                     즉, 1000은 1초를 의미한다.
						Thread.sleep(100); //0.1초
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
	th3.start();
	}
}

class MyThread1 extends Thread { 
	
	@Override  //run치고 override선택해서 생성
	public void run() {
		for(int i=1; i<200; i++) {
			System.out.print("*");
			
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				//                     시간은 밀리세컨드 단위를 사용.
				//                     즉, 1000은 1초를 의미한다.
				Thread.sleep(100); //0.1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable{

	@Override //Runnable은 void run을 가지고 있는 객체
	public void run() {
		for(int i=1; i<200; i++) {
			System.out.print("$");
			
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				//                     시간은 밀리세컨드 단위를 사용.
				//                     즉, 1000은 1초를 의미한다.
				Thread.sleep(100); //0.1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}