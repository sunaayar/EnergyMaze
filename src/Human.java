public class Human {

	// ATTRIBUTES
	private char name = 'H';
	private int energy = 100,  speed;
	public static int x_coord, y_coord;
	public Stack backpack = new Stack(5);
	private Stack temp = new Stack(5);

	// CONSTRUCTOR

	public Human(int energy, int x_coord, int y_coord, int speed, char name) {

		this.energy = energy;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.speed = speed;
		this.name = name;
	}

	// EMPTY CONSTRUCTOR
	public Human() {

	}

	// GETTER-SETTER
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getX_coord() {
		return x_coord;
	}

	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}

	public int getY_coord() {
		return y_coord;
	}

	public void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Stack getBackpack() {
		return backpack;
	}

	public void setBackpack(Stack backpack) {
		this.backpack = backpack;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	public Stack getTemp() {
		return temp;
	}

	public void setTemp(Stack temp) {
		this.temp = temp;
	}

	// METHODS

	public void AddBackpackItem(Items item) {	
		backpack.push(item.getType());	
	}
	public void PlayerSpeed(){
		if(energy==0){
			speed=speed%2;
		}
	}

}
