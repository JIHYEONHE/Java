package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티스레드를 활용한 카운트다운 처리
 * 
 * @author PC-03
 */
public class T06TreadTest { //메인은 실행까지
	// 입력여부를 확인하기 위한 변수 선언(모든 스레드에서 공통으로 사용할 변수)
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		DataInput input = new DataInput();
		CountDown count = new CountDown();
		
		input.start();
		count.start();
	}
}
//사용자 입력 스레드, 카운트다운 스레드

/**
 * 데이터 입력을 받는 스레드
 */
class DataInput extends Thread { //얘가 제일 마지막에 죽음
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		T06TreadTest.inputCheck = true;

		System.out.println("입력한 값은 " + str + "입니다.");
	}
}
/*
 * 카운트다운을 처리하는 스레드 클래스
 */

class CountDown extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			if(T06TreadTest.inputCheck) {
				return;
			}
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 10초가 경과디었는데도 입력이 없으면 프로그램을 종료한다
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); //프로그램을 종료시키는 메서드 / 정상종료 0 비정상종료 -1
	}
}//모든스레드가 다 죽어야 프로그램 종료됨