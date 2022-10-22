package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05FileStreamTest {
	public static void main(String[] args) {
		
		//파일 읽기위한 스트림 객체 생성
		FileInputStream fis = null;
		
		try {//FileInputStream & .read 바이트단위로 읽음 
			fis = new FileInputStream("d:D_Other/test.txt");
			//파일로 부터 1byte씩 읽음 
			//FileOutputStream 도 있음 
			int data;
			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미
			while((data = fis.read())!= -1) {//다읽으면 -1이라서 !-1 즉 -1전까지 읽음
				//읽어온 자료 출력하기
				System.out.print((char)data);
			}
			fis.close(); //작업 완료 후 스트림 닫기
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
