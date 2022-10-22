package kr.or.ddit.basic;

public class T11DisplayCharacterTest {
    //3개(명)의 스레드가 각각 알파벳 대문자를 출력햐는데
	//출려을 끝낸 순서대로 결과를 나타내는 프로 그램 삭정하기.
	static String strRank = "";  //순위 정보 담기위한 변수 선언

	public static void main(String[] args) {
	
		DisplayCharacter[]disChars = new DisplayCharacter[] {
				 new DisplayCharacter("홍길동"),
				 new DisplayCharacter("변학도"),
				 new DisplayCharacter("일지매")
		 };
		 for(DisplayCharacter dc : disChars) {
			 dc.start();
		 }
		 for(DisplayCharacter dc : disChars) {
			 try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		 
		 System.out.println("경기 끝...");
		 System.out.println("------------------");
		 System.out.println();
		 System.out.println("경기 결과");   //본인의 클래스라서 그냥 strRank라고 해도됌
		 System.out.println("순위: " + T11DisplayCharacterTest.strRank);
	}
}

//알파벳 대문자를 출력하는 스레드 클래스
class DisplayCharacter extends Thread{
	private String name;
	
	public DisplayCharacter(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for(char ch='A'; ch<='Z';ch++) {
			System.out.println(name + "의 출력 문자 : "+ ch);
			
			try {
				//sleep()  메서드의 값을 200~500사이의 난수로 한다
				Thread.sleep((int)(Math.random()+301+200));
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(name + "출력 끝 . . .");
		
		T11DisplayCharacterTest.strRank += name + " ";
	}
	
}
