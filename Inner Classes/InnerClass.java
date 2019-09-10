public class InnerClass{

	public static void main(String[]args){

	SortingHat hat = new SortingHat();
	System.out.println(hat);

	Wizard wiz = new Wizard("fire");
	System.out.println(wiz);

	//code
	Wizard.Wand myWand = new Wizard.Wand();
	System.out.println(myWand);
	}

	public static class SortingHat{

		private String house;
		private String name;

		public SortingHat(){

			house = "Gryffindor";
			name = "Harry Potter";

		}

		public String toString(){
			return name+"'s house is "+ house;
		}

	}
}