package kr.or.ddit.basic;

public class T10ThreadStateTest {
/*
 *  <스레드의 상태>  
 *  -NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
 *  -RUNNABLE : 실행 중 또는 실행 가능한 상태
 *  -BLOCKED : 동기화 블럭에 의해서 일시정지 된 상태(Lock 이 풀릴때까지 기다리는 상태)
 *  
 *  -WAITING, TIMED_WAITING 
 *  : 스레드의 작업이 종료되진 않았지만 실행가능하지 않은 일시 정지 상태.
 *  : (TIMED_WAITING은 일시정지 시간이 지정된 경우임)
 *  
 *  -TERMINATED : 스레드의 작업이 종료된 상태
 */
//START->RUNNABLE->RUNNABLE->WAITING->RUNNABLE -> TERMINATED
	public static void main(String[] args) {	
		
		TargetThread targetTh = new TargetThread();
		
		StatePrintThread spTh = new StatePrintThread(targetTh);
		
		spTh.start();
	}
}
//Target용 스레드 
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1;i<=1000000000L;i++) {} //시간 지연용
		
		try {
			Thread.sleep(1500); //1.5초 잠들어라 //TIMED WAITING상태,깨면 RUNNABLE
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		for(long i=1;i<=1000000000L;i++) {} //시간 지연용
	}
}
// 스레드의 상태를 출력하기 위한 스레드 클래스
class StatePrintThread extends Thread{
	private Thread targetThread; //상태변화를 관찰할 스레드 저장 변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	@Override
	public void run() {
		while(true) {
			//스레드의 상태 구하기 (getState() 이용)
			Thread.State state = targetThread.getState();
			System.out.println("타겟 스레드의 상태값: "+ state);
			
			//NEW 상태인지 검사하여 스레드 구동시키기 
			if(state == Thread.State.NEW) {
				targetThread.start(); //new ->runnable로 바뀜
			}
			
			//종료 상태인지 검사하여 무한루프 빠져나가기
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);  //terminated되지 않았으면 0.5초 sleep 하고 다시 올라가서 runnable
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}