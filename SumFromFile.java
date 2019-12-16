import java.io.*;

class Main {


static BufferedReader second;
static int sum;
  
  public static void main(String[] args) {
    try{
       second = new BufferedReader (new FileReader("file2.txt"));
       String text = second.readLine();
       while(text!=null){
         sum += Integer.parseInt(text);
         text = second.readLine();
         
       }
        
         


    }catch (Exception e){

    }
    System.out.print(sum);
    

    
  }
}