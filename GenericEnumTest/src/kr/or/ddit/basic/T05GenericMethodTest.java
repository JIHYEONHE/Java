package kr.or.ddit.basic;
				  //T에는 number의 하위타입이 와야함 
class Util2{     //제한된 타입 파라미터  <T extends 제한타입> 
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue(); 
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

public class T05GenericMethodTest {
 public static void main(String[] args) {
	 					
	 int result1 = Util2.<Integer>compare(10, 20);  //<Integer> 생략가능
	 System.out.println(result1);//10 (앞의값 보다 )20(뒤에값)이 더커서 -1출력
	 
	 int result2 = Util2.<Number>compare(3.14, 3);
	 System.out.println(result2);//앞의 값이 더 크기때문에 1 출력
	 
	//Util2.compare("C", "JAVA"); 
	//Number로 타입제한했는데  문자열이 들어와서 에러발생 //Generic을 사용하면 type safe한 코딩 가능
}
}
