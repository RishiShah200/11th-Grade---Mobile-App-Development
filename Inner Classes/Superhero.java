import java.util.ArrayList;
public class Superhero{

	public static void main(String[]args){
		Superhero.Hero hero = new Superhero.Hero();
		hero.attacked();
		hero.isVulnerable();
		System.out.println(hero);

		SuperheroRelated first = new SuperheroRelated("Villain");
		System.out.println(first);


		SuperheroRelated.Quickness ability = new SuperheroRelated.Quickness();
		System.out.println(ability);

		ArrayList<Hero> list = new ArrayList<Hero>();
		for(int i = 0; i < 5;i++){
			list.add(new Hero());
		}
		System.out.println(list);
		for(int x = 0;x<list.size();x++){
			list.get(x).attacked();
			list.get(x).isVulnerable();
		}
		//add sorting algorithm


	}

	public static class Hero implements Comparable<Hero>{
		private String power;
		private int hp;
		private String name;
		private boolean vulnerable;
		private String crit;

		public Hero(){

			power = "100";
			hp = 200;
			name = "Superman";
		}

		public String toString(){

			return "The Superhero's name is " + name + " and has "+ hp + " hp, " + power + " power, and is "+ crit;
		}

		public String getPower(){
			return power;
		}
		public int getHP(){
			return hp;
		}
		public boolean getVulnerable(){
			return vulnerable;
		}
		public String getName(){
			return name;
		}
		public void isVulnerable(){
			if(hp<80){
				vulnerable = true;
				crit = "vulnerable";
			}
			else{
				vulnerable = false;
				crit = "not vulnerable";
			}
		}
		public void attacked(){
			hp = (int)(Math.random()*100)+1;
		}

		public int compareTo(Hero obj){
			if (hp > obj.getHP())
				return  1;
			else if(hp < obj.getHP())
				return -1;
			else
				return 0;
		}

	}
}
