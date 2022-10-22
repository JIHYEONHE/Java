package hw;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
Scanner scan = new Scanner(System.in);
int lottonumber;
int change;

public void lottoStart() {
	while(true) {
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("==========================");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택: ");
		int menu = scan.nextInt();
		switch (menu) {
		case 1:
			lottoGame();
			break;
		case 2:
			System.out.println("감사합니다 프로그램을 종료합니다.");
			return;
		default:
			System.out.println("잘못 입력했습니다. 다시 입력하세요.");
		}
	}
}

private void lottoGame() {
	System.out.println("Lotto 구입 시작");
	System.out.println();
	System.out.println("(1000원에 로또번호 하나입니다.)");
	System.out.print("금액 입력: ");
	int price = scan.nextInt();
	lottonumber = price / 1000;  //횟수
	change = price % 1000;  //나머지
	System.out.println();
	System.out.println("행운의 로또번호는 아래와 같습니다.");
	lottoNum(); //로또 번호 출력
	System.out.println();
	System.out.println("구매 장수는 " + lottonumber + "개 이고 거스름돈은 " + change + "원입니다.");
	
}

private void lottoNum() {
	for (int j = 0; j < lottonumber; j++) {
		Set<Integer> intRnd = new HashSet<Integer>(); //Hashset사용 중복x
		while (intRnd.size() < 6) {//1~45까지의 6개 숫자 출력
			int num = (int) (Math.random() * 45 + 1);
			intRnd.add(num);
		}
		System.out.println("로또번호 " + (j+1) + " : " + intRnd);
		//0부터 시작하기 때문에 j+1해서 1번부터 나오게 해줌
	
}
}

public static void main(String[] args) {
	new Lotto().lottoStart();
}
}

