public class Wizard{

	private String type;

		public Wizard(String type){

			this.type = type;

		}

		public String toString(){
			return "The magic type is " + type;
		}

		public static class Wand{

			private String core;

			public Wand(){
				core = "Dragon Heart";
			}

			public String toString(){
				return "The core is " + core;
			}
		}
}