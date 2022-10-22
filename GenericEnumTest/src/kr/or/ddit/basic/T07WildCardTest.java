package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T07WildCardTest {
	//장바구니 항목조회를 위한 메서드 (음식류  아이템)  //cart가 제너릭타입이라 와일드카드 이용하여 타입알려줌
	public static void displayCartItemInfo(Cart<? extends Food> cart) {
		System.out.println("= 음식류 장바구니 항목 리스트 = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("==============================================");
	}
	
	//장바구니 항목조회를 위한 메서드 (음료류  아이템) 
	//<? extends T> 와일드카드의 상한 제한. T와 그 자손들만 가능
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
		System.out.println("= 음료류 장바구니 항목 리스트 = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj.toString());
		}
		System.out.println("==============================================");
	}
	
	//장바구니 항목조회를 위한 메서드 (고기류나 그상위  아이템) 
	//<? super T> 와일드 카드의 하한 제한. T와 그 조상들만 가능(Object 제외)
		public static void displayCartItemInfo3(Cart<? super Meat> cart) {
			System.out.println("= 고기류나 상위음식류 장바구니 항목 리스트 = ");
			for(Object obj : cart.getList()) {
				System.out.println(obj.toString());
			}
			System.out.println("==============================================");
		}
	
	public static void main(String[] args) {
		
		Cart<Food> foodCart = new Cart<>();
		foodCart.add(new Meat("돼지고기", 5000));
		foodCart.add(new Meat("소고기", 15000));
		foodCart.add(new Meat("삼계탕", 10000));
		foodCart.add(new Juice("오렌지쥬스", 900));
		foodCart.add(new Coffee("아메리카노", 1500));
	
		Cart<Meat> meatCart = new Cart<>();
		meatCart.add(new Meat("돼지고기", 5000));
		meatCart.add(new Meat("소고기", 15000));
		//meatCart.add(new Coffee("라떼", 3000)); //Meat 타입만 허용함
	
		Cart<Drink> drinkCart = new Cart<>();
		drinkCart.add(new Juice("오렌지쥬스", 900));
		drinkCart.add(new Coffee("아메리카노", 1500));
		//drinkCart.add(new Meat("소고기", 15000)); //Drink만 가능
		
		//음식류
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		//음료나 음료 하위
		//displayCartItemInfo2(foodCart);
		//displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		//고기류나 고기류의 상위
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
		//displayCartItemInfo3(drinkCart);
		
	}
}

//계층구조 
//  Food -Meat
      // -Drink -Juice
             // -Coffee

class Food {
	private String name; //음식이름
	private int price;   //음식가격
	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	@Override
	public String toString() {
		return this.name + "(" + this.price +"원)" ;
	}
	
}

class Meat extends Food{

	public Meat(String name, int price) {
		super(name, price);
	}
}

class Drink extends Food{

	public Drink(String name, int price) {
		super(name, price);
	}
}

class Juice extends Drink{

	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink{

	public Coffee(String name, int price) {
		super(name, price);
	}
}

class Cart<T> {
	private List<T> list = new ArrayList<>();

	public List<T> getList() {
		return list;
	}

	public void add(T item) {
		list.add(item);
	}
}