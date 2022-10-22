package Homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StuList {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("01", "허지현", 100, 90, 80));
		stuList.add(new Student("06", "윤민석", 100, 90, 80));
		stuList.add(new Student("02", "김강산", 90, 80, 70));
		stuList.add(new Student("03", "이준혁", 80, 70, 60));
		stuList.add(new Student("04", "이승연", 70, 60, 50));
		stuList.add(new Student("05", "김연수", 60, 50, 40));
		
	
		for (int i = 0; i < stuList.size(); i++) {
	         int rank = 1;
	         for (int j = 0; j < stuList.size(); j++) {  
	                if (stuList.get(i).getTotal() < stuList.get(j).getTotal()) {
	                    rank++;
	                }
	            }
	            stuList.get(i).setRank(rank);
	      }
		
		
		
		System.out.println("정렬 전:");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("==========================================");

		Collections.sort(stuList);
		System.out.println("학번 오름차순 정렬 후 ");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("==========================================");
		
		
		Collections.sort(stuList, new SortTotalDesc());
		System.out.println("총점의 역순 :");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("================================================");
		

	
	}
}
class SortTotalDesc implements Comparator<Student> {

	@Override
	public int compare(Student stutotal1, Student stutotal2) {
		if(stutotal1.getTotal() > stutotal2.getTotal()) {
			return -1; //내림차순위해서 음수를 리턴
		}else if (stutotal1.getTotal()== stutotal2.getTotal()) {
			return -1;
		}else {
			return 1;
		}
		
	}

}



//=====================================================//

class Student implements Comparable<Student> {
	private String stunum;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int total;
	private int rank;

	public Student() {}

	public Student(String stunum, String name, int korean, int english, int math) {
		super();
		this.stunum = stunum;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.total = korean+english+math;
	}

	public String getStunum() {
		return stunum;
	}

	public void setStunum(String stunum) {
		this.stunum = stunum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "학생 [학번=" + stunum + ", 이름=" + name + ", 국어=" + korean + ", 영어=" + english + ", 수학=" + math + ", 총점="
				+ total + ", 등수=" + rank + "]";
	}

	@Override
	public int compareTo(Student stu) {
		return this.getStunum().compareTo(stu.getStunum());
	}

}

