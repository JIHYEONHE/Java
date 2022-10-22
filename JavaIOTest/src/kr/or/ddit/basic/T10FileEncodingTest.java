package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

//저장해보자
public class T10FileEncodingTest {
/*
	OutputStreamWriter 객체 => OutputStream(바이트기반 출력용 스트림객체)
	 을 Writer(문자기반의 출력용 객체)로 변환하는 보조 스트림 객체
	 		=> 이 객체도 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.
 */
	//보조스트림은 기본기능이 없기때문에 =>항상 바이트기반 스트림과 함께 해야함 
	//보조스트림은 기능추가 목적 
	public static void main(String[] args) throws IOException {
		// 키보드로 입력한 내용을 파일로 저장하는데
		//out_utf8.txt파일은 'utf-8' 인코딩 방식으로
		//out_ansi.txt파일은 'ms949'인코딩 방식으로  저장
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//파일 출력용
		//바이트기반 스트림
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		//보조스트림 , 인코딩방식 커스텀 ,기반스트림 필요로함
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1,"utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2,"ms949");
		
		int data =0;
		
		System.out.println("아무거나 입력하세요.");
		while((data = isr.read())!= -1) { //ctrl+z 치기전까지 엔터치면 한줄한줄 저장
			osw1.write(data);
			osw2.write(data);
		}
		System.out.println("작업 완료...");
		
		isr.close();
		osw1.close();
		osw2.close();
	}
}
