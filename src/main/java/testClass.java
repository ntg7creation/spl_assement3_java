
public class testClass {

	public static void main(String agr[])
	{
		System.out.println("stuff");
		
		int fill =0;
		byte[] b = new byte[1<<10];
		String word ="somthing";
		for (byte c : word.getBytes()) {
			b[fill]= c;
			fill++;
		}
		
		b[fill]= '\0';
		fill++;
		
		String word2 ="one else thing";
		for (byte c : word2.getBytes()) {
			b[fill]= c;
			fill++;
		}
		
		int start =0;
		int end = 0;
		
		while(end<b.length && b[end]!='\0')
			end++;
		System.out.println(new String(b,start,end));
		end++;
		start = end;
		while(end<b.length && b[end]!='\0')
			end++;
		System.out.println(new String(b,start,end));
	//	HHHH
	}
}
