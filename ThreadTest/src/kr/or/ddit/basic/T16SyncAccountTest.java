package kr.or.ddit.basic;

/*
 * 은행의 입출금을 스레드로 처리하는 예제
 * (synchronized 를 이용한 동기화)
 */
public class T16SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000);
		
		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);
		
		bth1.start();
		bth2.start();
	}
}

// 은행의 입출금을 관리하는 클래스 (공유 객체)
class SyncAccount{
	private int balance; //잔액이 저장될 변수

	synchronized public int getBalance() {
		return balance;
	}
	
	//입금 처리를 수행하는 메서드
	public void deposit(int money) {
		balance +=money; //입금하면 잔액에 누적
	}
	
	//출금을 처리하는 메서드 (출금 성공: true, 출금 실패 : false 반환)
	//동기화 영역에서는 호출하는 메서드도 동기화 처리를 해 주어야한다. 
	//=>synchronized한 메소드 영역에서만 동기화 되고 , 다른메소드 호출하는순간에는 동기화가 안됨
	//=>호출한 메서드도 동기화 해줘야함 
	//synchronized 동기화 처리 해줌 ->동기화 처리를 하면 느려지지만 정확성이 더 중요하다.
	synchronized public boolean wihdraw(int money) {
		if(balance >=money) { //잔액이 충분할 경우 인출가능 
			
			for(int i=1; i<=1000000000;i++) {} //시간 때우기(critical section임계영역)
			
			balance -= money;
			System.out.println("메서드 안에서 balace = " + getBalance());
			
			return true; //출금 성공
		}else {
			return false; //출금 실패 
		}
	}
}

// 은행 업무를 처리하는 스레드 
class BankThread extends Thread{
	private SyncAccount sAcc; //공유객체
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc; //생성자의 파라미터로 공유객체 받음 
	}
	
	@Override
	public void run() {
		boolean result = sAcc.wihdraw(6000); //6000원 인출
		System.out.println("스레드 안에서 result = " + result
				+", balace = "+sAcc.getBalance());
	}
}
