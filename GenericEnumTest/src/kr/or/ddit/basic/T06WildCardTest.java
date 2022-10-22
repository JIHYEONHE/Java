package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
	/*
	 * 와일드 카드에 대하여 ...
	 * 
	 * 와일드카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의 인수(Argument)로서, 함수선언, 객체생성 및
	 * 메서드 정의할 때 사용된다.
	 * 
	 * <? extends T> => 와일드카드의 상한제한. T와 그 자손들만 가능 <? super T> => 와일드카드의 하한제한, T와 그
	 * 조상들만 가능 <?> => 모든타입이 가능 <? extends Object>와 동일
	 */
	public static void main(String[] args) {

		FruitBox<Fruit> fruitBox = new FruitBox<>(); // 과일담을 과일상자
		FruitBox<Apple> appleBox = new FruitBox<>(); // 사과담을 사과상자
		FruitBox<Gabage> gabageBox = new FruitBox<>();
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape()); // 사과,포도를 과일상자에 담아줌

		appleBox.add(new Apple()); // 사과상자 사과담기
		appleBox.add(new Apple());
		// appleBox.add(new Grape()); //사과상자는 제너릭을 이용해 <apple>로 제한했기 때문에 에러발생

		gabageBox.add(new Gabage());
		gabageBox.add(new Gabage());
		gabageBox.add(new Gabage());
		
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox); 
		//makeJuice 메소드안에는 fruit만 오도록 정의 했었지만 모두 가능하게 makeJuice에 제너릭사용
		//Juicer.makeJuice(gabageBox);
	}
}

class Juicer {
	//static void makeJuice(FruitBox<Fruit> box) { //Fruit를 담고 있는 FruitBox 제너릭클래스
	//static <T extends Fruit> void makeJuice(FruitBox<T> box) {	//제한된 타입파라미터 fruit과 그친구들가능
		static void makeJuice(FruitBox<? extends Fruit> box) { //제너릭을 이용한 일반메소드
		String fruitListStr = " "; // 과일목록

		int cnt = 0;

		for (Object f : box.getFruitList()) {
			if (cnt == 0) {
				fruitListStr += f;
			} else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		
		System.out.println(fruitListStr + "=> 쥬스 완성! ");
	}
}

class Fruit {
	private String name; // 과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}
}

class Apple extends Fruit {

	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit {

	public Grape() {
		super("포도");
	}
}
class Gabage{
	@Override
	public String toString() {
		return "쓰레기";
	}
}

class FruitBox<T> {

	private List<T> fruitList = new ArrayList<>(); // <T>()jdk1.7 부터 T생략가능

	public List<T> getFruitList() {
		return fruitList;
	}

	public void add(T fruit) {
		fruitList.add(fruit);
	}
}