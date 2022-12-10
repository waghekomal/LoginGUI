
public class absimplement extends abstractclass {
	
	

	public static void main(String[] args) {
		absimplement ab=new absimplement();
		ab.m1();
		ab.m2();
		ab.m3();
		
	}

	@Override
	public void m2() {
		System.out.println("abstrct M2 Method");
		
	}

	@Override
	public void m3() {
	System.out.println("Abstract M3 method");
		
	}

}
