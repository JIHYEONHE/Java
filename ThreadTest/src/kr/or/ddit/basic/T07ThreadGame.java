package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07ThreadGame {
 
	public static boolean inputCheck = false; //입력여부 확인 변수
	
	public static void main(String[] args) {
		Random random = new Random();
		
		String[] com= {"가위","바위","보"};
		String comResult = com[random.nextInt(3)]; //0부터2까지의 랜덤숫자 0은 가위 1은 바위 2는 보
		
		Count count = new Count();
		
		//사용자의 변수
		String gamer = null; 
		
		//카운트 다운 쓰레드 실행
		count.start();
		do{gamer = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요 : ");
		}while(!gamer.equals("가위") && !gamer.equals("바위") && !gamer.equals("보"));
		T07ThreadGame.inputCheck = true;  //입력 확인
		//처리
		String result="";
		if (gamer.equals(comResult)) {
			result = "무승부";
		} else if ((gamer.equals("가위") && comResult.equals("보")) || (gamer.equals("바위") &&comResult.equals("가위"))
				|| (gamer.equals("보") && comResult.equals("바위"))) {
			result = "당신이 승리하였습니다!";
		} else {
			result = "컴퓨터 승리!";
			System.exit(0);
		}//결과
		System.out.println("=====결과=====");
		System.out.println("컴퓨터: " + comResult);
		System.out.println("당신: "+ gamer);
		System.out.println("결과: " + result);
	}
}//데이터 입력 받는 스레드
	
//카운트다운 
class Count extends Thread{
	@Override
	public void run() {
		for (int i = 5; i >0; i--) {
			if(T07ThreadGame.inputCheck ) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
		System.out.println("5초가지나 시간초과로 당신이 졌습니다.");
		System.exit(0); //프로그램을 종료시키는 메서드 / 정상종료 0 비정상종료 -1
		
}
}
