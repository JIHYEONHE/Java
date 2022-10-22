package kr.or.ddit.basic;

public class T08ThreadPriorityTest {
	public static void main(String[] args) {
		//MAX_PRIORITY 상수값 선언 MAX 10, NORM 5 MIN 1
		//따로 선언안하면 default NORM_PRIORITY
		System.out.println("최대 우선순위:  "+ Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위:  "+ Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위:  "+ Thread.NORM_PRIORITY);
	
		Thread[] ths = new Thread[] { //스레드 배열  //스레드 총6개
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest1(),
				new ThreadTest2()
		};
		//우선 순위는 start()메서드를 호출하기 전에 설정해야 한다.
		for(int i=0; i<ths.length;i++) {
			if(i == 5) {
				ths[i].setPriority(10); //Min_PRIORITY 의 값을 10으로 바꿔서 소문자 먼저 실행되게 함
			}else { //NORM 을 MIN으로 바꿈 
				ths[i].setPriority(Thread.MIN_PRIORITY);
			}
		}
		//우선순위 출력
		for(Thread th: ths) {
			System.out.println(th.getName()+"의 우선순위:"
						+th.getPriority());
		}
		//스레드 시작 
		for(Thread th:ths) {
			th.start();
		}
		for(Thread th :ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//대문자로 출력하는 스레드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z';ch++) { //A부터 Z까지 찍음
			System.out.println(ch);
			
			//아무것도 하지 않는 반복문 (시간때우기용)
			for(long i=1;i<=1000000000L;i++) {}
		}
	}
}
//소문자를 출력하는 스레드
class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z';ch++) { //A부터 Z까지 찍음
			System.out.println(ch);
			
			//아무것도 하지 않는 반복문 (시간때우기용)
			for(long i=1;i<=1000000000L;i++) {}
		}
	}
}