
public class Items {
	private char type;// (1-4 or *)
	private int x_coord, y_coord, time = 100;
	private boolean push = false;
	

	// CONSTRUCTOR
	public Items(char type, int x_coord, int y_coord, int time, boolean push) {
		this.type = type;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.time = time;
		this.push = push;
	
	}

	// EMPTY CONSTRUCTOR
	public Items() {
	}

	public Items(char type) {
		this.type = type;
	}

	// GETTER-SETTER

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isPush() {
		return push;
	}

	public void setPush(boolean push) {
		this.push = push;
	}

	

	// METHODS
	

	public static Items GenerateItem() {
		char[] energy_items = { '*', '1', '2', '3', '4' };
		int rnd = (int) ((Math.random() * (2)));// 0 or 1.
		// if rnd==0 the item is '*'.
		if (rnd > 0)// 1-4.
		{
			rnd = (int) ((Math.random() * (4)));// 1,2,3,4
		}
		Items i = new Items();
		i.setType(energy_items[rnd]);
		return i;
	}
}
