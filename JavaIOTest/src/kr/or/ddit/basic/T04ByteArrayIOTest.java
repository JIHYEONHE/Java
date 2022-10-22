package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
//write(byte[]b)
//스트림 정리해랴이이이이~~~~
public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[]temp = new byte[4]; //데이터를 읽을때 사용할 배열
		
		//스트림 객체 생성하기 
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len = 0; //버퍼로 읽어드린 byte 수 변수  
		
		//read() 메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		//				 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((len = bais.read(temp))!= -1) { //몇바이트 읽어서 저장했는지 알려줌
			
			System.out.println("temp => "+Arrays.toString(temp));
			
			System.out.println("len => " +len);
			baos.write(temp,0,len); //출력하기
		}
		 //버퍼는 4바이트씩 읽어옴 //아까꺼는 1바이트씩 읽어서 지금것이 더 빠름 
		 //버퍼를 크게 잡을수록 메모리 잡아먹음 ㅂㅇㅂㅇ~~
		 //메모리 줄이고 느릴꺼냐 , 크게잡고 빨리할꺼냐 ?!
		
		//출력된 스트림 값들을 배열로 반환해서 반환하는 메서드
		//toByteArray()
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => "+Arrays.toString(inSrc));
		System.out.println("outSrc => "+Arrays.toString(outSrc));
		
		
		
		
		
		
	}
}
