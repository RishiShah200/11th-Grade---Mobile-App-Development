public class SuperheroRelated{

	private String type;

	public SuperheroRelated(String type){
		this.type = type;
	}
	public String toString(){
		return "This superhero is a " + type;
	}

	public static class Quickness{

		private int speed;

		public Quickness(){
			speed = 200;		//mph
		}
		public String toString(){
			return "The Superhero has a speed of "+speed+" mph";
		}

	}

}