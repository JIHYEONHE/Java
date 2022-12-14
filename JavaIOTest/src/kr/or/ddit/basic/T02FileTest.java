package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) {
		//import java.io.File;
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		//.exists 있으면 true 없으면 false
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath()
					+"은 존재합니다.");
		}else{
			System.out.println(f1.getAbsolutePath() 
					+ "은 없는 파일입니다.");
			
			try { //파일이 없는 경우 //.createNewFile메소드 이용 생성 
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath()
							+"파일을 새로 만들었습니다.");
				}
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() 
					+"은 존재합니다.");
		}else {
			System.out.println(f2.getAbsolutePath()
					+" 은 없는 파일입니다.");
		}
		System.out.println("-------------------------------------------");
		
		File f3 = new File("d:/D_Other"); //file객체를 담은 배열
		File[] files = f3.listFiles();
		
		for(File f : files) {
			System.out.print(f.getName()+" => ");
			if(f.isFile()) { //파일인지 디렉토리인지 확인
				System.out.println("파일");
			}else if(f.isDirectory()) {
				System.out.println("디렉토리");
			}
		}
		System.out.println("============================================");
		String[] strFiles = f3.list(); //파일에 해당한는 list의 string값만 받음
		for(String fName : strFiles ) {
			System.out.println(fName);
		}
		System.out.println("-------------------------------------------");
		System.out.println();
		
		//==========================================================
		
		// 출력할 디렉토리 정보를 갖는 File 객체 생성하기
		File f4 = new File("d:/D_Other");
		
		displayFileList(f4); 
	}
	
	//지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath()
		+"] 디렉토리의 내용");
		
		//디렉토리 안의 모든 파일 목록 가져오기
		File[] files = dir.listFiles();
		
		//하위 디렉토리 정보를 저장할 ArrayList 생성(File 배열의 인덱슥밧 저장)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		//날짜를 출력하기 위한 포맷터 설정   //a 는 오전/오후
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i=0;i<files.length; i++) {
			String attr =""; //파일의 속성(읽기, 쓰기, 히든, 디렉토리 구분) 
			String size = ""; //파일 용량
			
			if(files[i].isDirectory()) {//디렉포리라면
				attr = "<DIR>"; //디렉토리라고 표시
				subDirList.add(i); //인덱스 값을 List에 추가하기
			}else { //파일이라면 
				size = files[i].length()+"";
				attr = files[i].canRead() ? "R": " ";
				attr +=files[i].canWrite() ? "W" : " ";
				attr +=files[i].isHidden() ? "H" : " ";
			}                  //%뒤 - 붙이면 좌측정렬  default는 우측정렬
			System.out.printf("%s %-5s %12s %s\n", //%뒤숫자들은 문자열의 길이지정
					sdf.format(             //.lastModified()
							new Date(files[i].lastModified())),
					attr,size,files[i].getName());
		}
		
		int dirCount = subDirList.size(); // 하위폴더 갯수 
		int fileCount = files.length - dirCount; //파일 갯수 :전체파일길이-하위폴더갯수
		
		System.out.println(fileCount +"개의 파일, "
				+dirCount + "개의 디렉토리 ");
		System.out.println();
		
		for(int i=0;i<subDirList.size();i++) {
			//하위 폴더의 내용들도 출력하기 위해 현재 메서드를 재귀호출하여 처리한다.
			displayFileList(files[subDirList.get(i)]); 
			//디렉토리갯수만큼 for문돔 - 디렉토리안에 또 하위있으면 마지막 하위까지 보여줌
		}
	}
}
