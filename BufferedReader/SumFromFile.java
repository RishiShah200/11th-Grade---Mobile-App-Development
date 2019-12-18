import java.io.*;

public class SumFromFile {

	static BufferedWriter writer;
	static BufferedReader second;
	static BufferedWriter alphabet;
	static int sum;
	static int[] arr = new int [101];
	static final String LETTERS = "abcdefghijklmnopqrstuwvxyz";

	static Thread read = new Thread("Sum Thread"){
		public void run(){

			try{
			   second = new BufferedReader (new FileReader("file2.txt"));
			  // first = new BufferedReader (new FileReader("file1.txt"));
			   String text = second.readLine();
			   while(text!=null){
				 sum += Integer.parseInt(text);
				 text = second.readLine();

			   }

			}catch (Exception e){

			}
			System.out.println(sum + ": " + read.getName());
		}
	};

	static Thread write = new Thread("Write Thread"){
		public void run(){

			try{
			   writer = new BufferedWriter(new FileWriter("C:\\Users\\rishi\\Documents\\SBHS\\11th\\Mobile App Development\\11th-Grade---Mobile-App-Development\\BufferedReader\\writinghere.txt"));
				for(int x = 1;x<=arr.length-1;x++){
					arr[x] = x;
				}
				for(int x = 1;x<arr.length;x++){
					writer.write(String.valueOf(arr[x]));
				}
				writer.close();


			}catch (Exception e){
				System.out.print(e);
			}
			System.out.println(write.getName() + "YEBOi");
		}
	};


	static Thread alpha = new Thread("Alphabet Writer"){
		public void run(){

			try{
			   write.join();
			   alphabet = new BufferedWriter(new FileWriter("C:\\Users\\rishi\\Documents\\SBHS\\11th\\Mobile App Development\\11th-Grade---Mobile-App-Development\\BufferedReader\\itsstuff.txt"));
			   for(int x = 0;x<LETTERS.length();x++){
				   alphabet.write(LETTERS.charAt(x));
			   }
				alphabet.close();


			}catch (Exception e){
				System.out.print(e);
			}
			System.out.println(alpha.getName() + "YEBOi");
		}
	};




  public static void main(String[] args) {
    SumFromFile app = new SumFromFile();
    read.start();
    write.start();
    alpha.start();
  }
}