public class WritingData{

	public WritingData(){


		try{
			OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file,
			Context.MODE_PRIVATE));
			writer.write(dataToWrite);
			writer.close();
		}catch(Exception e){

		}


	}


	public static void main(String[]args){

	WritingData app = new WritingData();

	}

}