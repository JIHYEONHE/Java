package homework;


public class T01EnumHw {
 public enum Planet //생성자 파라미터 이용해서 반지름 넣어줌 
 {수성(2439),금성(6052),지구(6371),성(3390),목성(69911),토성(85232),천왕성(25362),해왕성(24622);
 
 //괄호속의 값 저장될 변수 선언
 private double radius;
 
 //생성자 만들기
 Planet(double data){
	 radius = data;
 }
 //값을 반환하는 메서드 작성
 public double getRadius() { //면적구하기
	 return 4*Math.PI*radius*radius;
 }
}
 public static void main(String[] args) {
	 //열거형이름.values() => 데이터를 배열로 가져온다.
	 Planet[] enumArr = Planet.values();
	 for(int i=0; i<enumArr.length;i++) {
		 System.out.println(enumArr[i].name()+":"
				 +enumArr[i].getRadius());
	 }
}
}