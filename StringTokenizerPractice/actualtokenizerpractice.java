import java.util.StringTokenizer;
import java.util.ArrayList;


public class actualtokenizerpractice
{


	public static void main(String[] args) {
		first();
		second();
		third();
		fourth();
		tokenizer();		//last section of practice
	}

	public static void first(){


        try{
            String a = "Rishi";     //StringIndexOutOfBoundsException
            String b = "Rachit";
            String c = a.substring(-11);
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("There is an error");
        }

    }

    public static void second(){
        int[] arr = {1,2,3};

        try{
            arr[4] = 5;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("This is also an error");
        }
    }

    public static void third(){

        try{
            String z = null;
            System.out.print(z.length());
        }catch(NullPointerException e){
            System.out.println("This is an additional error");
        }


    }

    public static void fourth(){

        try{
            String ab = "Rishi";
            int abc =Integer.parseInt(ab);
        }catch(NumberFormatException e){
            System.out.println("This is the last exception");
        }
    }

    public static void tokenizer(){
        String sentence = "My name is Rishi Shah. My nam?e is Rach?it. My name i?s Aka?sh.";
        String punctdelim = ".,?-!";
        String sentdelim = ".";
        String worddelim = " ";
        String vowel = "aeiouAEIOU";
        int punctcnt = 0;
        int sentcnt = 0;
        int wordcnt = 0;
        int wordspersentence = 0;
        int vowelcnt = 0;
        ArrayList<String> punctlist = new ArrayList<String>();
        ArrayList<String> sentlist = new ArrayList<String>();
        ArrayList<String> wordlist = new ArrayList<String>();
     	StringTokenizer puncttokenizer = new StringTokenizer(sentence,punctdelim,true);
     	StringTokenizer senttokenizer = new StringTokenizer(sentence,sentdelim,true);
     	StringTokenizer wordtokenizer = new StringTokenizer(sentence,worddelim,true);
        while(puncttokenizer.hasMoreTokens()){		//return boolean
            String currentWord = puncttokenizer.nextToken();
            punctlist.add(currentWord);
        }

        while(senttokenizer.hasMoreTokens()){		//return boolean
			String currentWord = senttokenizer.nextToken();
			sentlist.add(currentWord);
        }

        while(wordtokenizer.hasMoreTokens()){		//return boolean
			String currentWord = wordtokenizer.nextToken();
			wordlist.add(currentWord);
        }

        for(int x = 0;x<punctlist.size();x++){
			if(punctdelim.contains(punctlist.get(x)))
				punctcnt++;
		}

		for(int x = 0;x<sentlist.size();x++){
			if(sentdelim.contains(sentlist.get(x)))
				sentcnt++;
		}

		for(int x = 0;x<wordlist.size();x++){
			if(!(wordlist.get(x).equals(worddelim)))
				wordcnt++;
		}


		System.out.println("Number of punctuation marks " + punctcnt + "\n" + "# of Sentences " + sentcnt + "Number of words in  sentence " + wordcnt);		//number of puncuation  marks




    }

}
