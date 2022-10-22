package kr.or.ddit.basic;

public class T12ThreadYieldTest {
/*
 *  yield() 메서드에 대하여..
 *  1.현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게 실행 기회를 제공한다.(양보)
 *  2.현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다. - yield하면 cpu양보 실행대기 runnable로 바꾼다
 *  (Waiting 이나 Blocked 상태로  바뀌지 않는다.)
 *  3.yield() 메서드를 실행한다고 해서 현재 실행중인 스레드가
 *    곧바로 Runnable상태로 전이 된다고 확신 할 수 없다.     //무조건 양보한다고 확신x 확률만 좀 더있어.,.
 */
	
	public static void main(String[] args) {
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		
		th1.start();
		th2.start();
	}
}

class YieldThreadEx1 extends Thread{
	public YieldThreadEx1() {
		super("양보 스레드"); //생성자에 string 값줘서 이름 부여 
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {     //.getName()현재 스레드의 이름 가지고옴
			System.out.println(Thread.currentThread().getName()
					+":"+i);
			Thread.yield(); //양보하기
		}
	}
}

//양보안하는 스레드
class YieldThreadEx2 extends Thread{
	public YieldThreadEx2() {
		super("비양보 스레드"); //생성자에 string 값줘서 이름 부여 
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++) {     //.getName()현재 스레드의 이름 가지고옴
			System.out.println(Thread.currentThread().getName()
					+":"+i);
		}
	}
}
