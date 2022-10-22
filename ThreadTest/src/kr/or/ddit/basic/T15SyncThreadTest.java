package kr.or.ddit.basic;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkThread th1 = new WorkThread("1번 스레드", sObj);
		WorkThread th2 = new WorkThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 하는 방법 1: 메서드 자체에 동기화 설정하기 
	//synchronized = 동기화 -> 한스레드가 접근하면 다른스레드 접근 못함
	//synchronized public void add() { //합계구하기
		
	public void add() { 
		
		//동기화 하는 방법 2: 동기화 블럭으로 설정하기 
		//synchronized (this) {
	
		for(int i=0;i<1000000000;i++) {} //동기화 전까지 시간벌기용
			
		int n = sum;
		
		n += 10;
		
		sum = n; //sum값에 10 누적
		
		System.out.println(Thread.currentThread().getName()
				+" 합계: "+ sum); //최종값 출력
		//}
	}
}

//작업을 수행하는 스레드 클래스 
class WorkThread extends Thread{
	private ShareObject sObj; //생성자의 파라미터
	
	public WorkThread(String name, ShareObject sObj) {
		super(name); //스레드의 이름부여 
		this.sObj = sObj;
	}
	
	//동기화 하는 방법 3: 실행블럭을 통해서 동기화 
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			synchronized (sObj) {
				sObj.add(); //add 메소드 10번 호출 
				
			}
 		}
	}
}
