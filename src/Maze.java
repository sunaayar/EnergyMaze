
public class Maze {

	// ATTRIBUTES
	private String name;
	private Items item = new Items();
	public static Object[][] mazearray = new Object[21][55];
	public Items[] itemarray = new Items[20];// items which is inside of the
												// maze.
	private CircularQueue InputList = new CircularQueue(10);
	private Human h_player = new Human();
	private int counter = 0;
	private static Computer c_player = new Computer();
	private int difference_X = 0;
	private int difference_Y = 0;
	private static Stack tempx = new Stack(1000);
	private static Stack tempy = new Stack(1000);

	// CONSTRUCTOR
	public Maze(String name, Items item, Object[][] mazearray, Items[] itemarray, CircularQueue inputList,
			Human h_player, int counter) {
		this.name = name;
		this.item = item;
		this.mazearray = mazearray;
		this.itemarray = itemarray;
		InputList = inputList;
		this.h_player = h_player;
		this.counter = counter;
	}

	// EMPTY CONSTRUCTOR
	public Maze() {

	}

	// GETTER-SETTER

	public Object[][] getMazearray() {
		return mazearray;
	}

	public void setMazearray(Object[][] mazearray) {
		this.mazearray = mazearray;
	}

	public Items[] getItemarray() {
		return itemarray;
	}

	public void setItemarray(Items[] itemarray) {
		this.itemarray = itemarray;
	}

	public Human getH_player() {
		return h_player;
	}

	public void setH_player(Human h_player) {
		this.h_player = h_player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public CircularQueue getInputList() {
		return InputList;
	}

	public void setInputList(CircularQueue inputList) {
		InputList = inputList;
	}

	public Computer getC_player() {
		return c_player;
	}

	public void setC_player(Computer c_player) {
		this.c_player = c_player;
	}

	public int getDifference_X() {
		return difference_X;
	}

	public void setDifference_X(int difference_X) {
		this.difference_X = difference_X;
	}

	public int getDifference_Y() {
		return difference_Y;
	}

	public void setDifference_Y(int difference_Y) {
		this.difference_Y = difference_Y;
	}

	// METHODS
	public void DecreaseTime() {
		// 100 saniye dolunca mazeden silincekler.
		for (int i = 0; i < 20; i++) {
			itemarray[i].setTime(itemarray[i].getTime() - 1);
			// time sýfýrsa(100 saniye dolmuþ)mazeden silincek.
			if (itemarray[i].getTime() == 0) {
				DisappearItem(itemarray[i]);
				itemarray[i] = new Items();

			}
		}
	}

	public void DisappearItem(Items itm) {
		mazearray[itm.getY_coord()][itm.getX_coord()] = ' ';
		Items item = FindItem(itm.getY_coord(), itm.getX_coord());
		for (int i = 0; i < 20; i++) {
			if (item == itemarray[i]) {
				itemarray[i] = null;
				break;
			}
		}

		LessThan20();
	}

	public void PushItem(int y, int x, String direction) {

		if (direction.equals("right")) {

			if (mazearray[y][x] == (Object) '*') {
				h_player.setEnergy(h_player.getEnergy() + 25);
				mazearray[y][x] = mazearray[y][x - 1];
				mazearray[y][x - 1] = (Object) ' ';
			}

			else if (mazearray[y][x + 1] == (Object) ' ') {
				for (int i = 0; i < 20; i++) {
					if (itemarray[i].getX_coord() == x && itemarray[i].getY_coord() == y) {
						itemarray[i].setX_coord(x + 1);

					}
				}
				mazearray[y][x + 1] = mazearray[y][x];
				mazearray[y][x] = mazearray[y][x - 1];
				mazearray[y][x - 1] = ' ';
			} else {
				if (mazearray[y][x] == mazearray[y][x + 1]) {

					h_player.setEnergy(h_player.getEnergy() + 100);
					mazearray[y][x] = (Object) ' ';
					mazearray[y][x + 1] = (Object) ' ';
					int i = 0;
					for (i = 2; i < 4; i++) {
						if (mazearray[y][x + (i - 1)] == mazearray[y][x + i]) {
							// itemlerin bulunduðu yerleri boþluk yap.

							mazearray[y][x + (i - 1)] = (Object) ' ';
							mazearray[y][x + i] = (Object) ' ';

						} else {
							// döngü bir tur dönmüþ.(4 tane ayný item yanyana)
							if (i == 3) {
								h_player.setEnergy(h_player.getEnergy() + 400);
							} else if (i == 2) {
								h_player.setEnergy(h_player.getEnergy() + 200);
							}
							break;
						}

					}

				}
			}

		}

		else if (direction.equals("left")) {

			if (mazearray[y][x] == (Object) '*') {
				h_player.setEnergy(h_player.getEnergy() + 25);
				mazearray[y][x] = mazearray[y][x + 1];
				mazearray[y][x + 1] = (Object) ' ';
			}

			else if (mazearray[y][x - 1] == (Object) ' ') {
				for (int i = 0; i < 20; i++) {
					if (itemarray[i].getX_coord() == x && itemarray[i].getY_coord() == y) {
						itemarray[i].setX_coord(x - 1);

					}
				}
				mazearray[y][x - 1] = mazearray[y][x];
				mazearray[y][x] = mazearray[y][x + 1];
				mazearray[y][x + 1] = ' ';
			}

			else {
				if (mazearray[y][x] == mazearray[y][x - 1]) {

					h_player.setEnergy(h_player.getEnergy() + 100);
					mazearray[y][x] = (Object) ' ';
					mazearray[y][x - 1] = (Object) ' ';
					int i = 0;
					for (i = 2; i < 4; i++) {
						if (mazearray[y][x - (i - 1)] == mazearray[y][x - i]) {
							// itemlerin bulunduðu yerleri boþluk yap.

							mazearray[y][x - (i - 1)] = (Object) ' ';
							mazearray[y][x - i] = (Object) ' ';

						} else {
							// döngü bir tur dönmüþ.(4 tane ayný item yanyana)
							if (i == 3) {
								h_player.setEnergy(h_player.getEnergy() + 400);
							} else if (i == 2) {
								h_player.setEnergy(h_player.getEnergy() + 200);
							}
							break;
						}

					}

				}

			}
		}

		else if (direction.equals("up")) {

			if (mazearray[y][x] == (Object) '*') {
				h_player.setEnergy(h_player.getEnergy() + 25);
				mazearray[y][x] = mazearray[y + 1][x];
				mazearray[y + 1][x] = (Object) ' ';
			}

			else if (mazearray[y - 1][x] == (Object) ' ') {
				for (int i = 0; i < 20; i++) {
					if (itemarray[i].getX_coord() == x && itemarray[i].getY_coord() == y) {
						itemarray[i].setY_coord(y - 1);

					}
				}
				mazearray[y - 1][x] = mazearray[y][x];
				mazearray[y][x] = mazearray[y + 1][x];
				mazearray[y + 1][x] = ' ';
			}

			else {
				if (mazearray[y][x] == mazearray[y - 1][x]) {

					h_player.setEnergy(h_player.getEnergy() + 100);
					mazearray[y][x] = (Object) ' ';
					mazearray[y - 1][x] = (Object) ' ';
					int i = 0;
					for (i = 2; i < 4; i++) {
						if (mazearray[y - (i - 1)][x] == mazearray[y - i][x]) {
							// itemlerin bulunduðu yerleri boþluk yap.

							mazearray[y - (i - 1)][x] = (Object) ' ';
							mazearray[y - i][x] = (Object) ' ';

						} else {
							// döngü bir tur dönmüþ.(4 tane ayný item yanyana)
							if (i == 3) {
								h_player.setEnergy(h_player.getEnergy() + 400);
							} else if (i == 2) {
								h_player.setEnergy(h_player.getEnergy() + 200);
							}
							break;
						}

					}

				}
			}
		}

		else if (direction.equals("down")) {

			if (mazearray[y][x] == (Object) '*') {
				h_player.setEnergy(h_player.getEnergy() + 25);
				mazearray[y][x] = mazearray[y - 1][x];
				mazearray[y - 1][x] = (Object) ' ';
			}

			else if (mazearray[y + 1][x] == (Object) ' ') {
				for (int i = 0; i < 20; i++) {
					if (itemarray[i].getX_coord() == x && itemarray[i].getY_coord() == y) {
						itemarray[i].setY_coord(y + 1);

					}
				}
				mazearray[y + 1][x] = mazearray[y][x];
				mazearray[y][x] = mazearray[y - 1][x];
				mazearray[y - 1][x] = ' ';
			}

			else {
				if (mazearray[y][x] == mazearray[y + 1][x]) {

					h_player.setEnergy(h_player.getEnergy() + 100);
					mazearray[y][x] = (Object) ' ';
					mazearray[y + 1][x] = (Object) ' ';
					int i = 0;
					for (i = 2; i < 4; i++) {
						if (mazearray[y + i - 1][x] == mazearray[y + i][x]) {
							// itemlerin bulunduðu yerleri boþluk yap.

							mazearray[y + i - 1][x] = (Object) ' ';
							mazearray[y + i][x] = (Object) ' ';

						} else {
							// döngü bir tur dönmüþ.(4 tane ayný item yanyana)
							if (i == 3) {
								h_player.setEnergy(h_player.getEnergy() + 400);
							} else if (i == 2) {
								h_player.setEnergy(h_player.getEnergy() + 200);
							}
							break;
						}

					}

				}
			}
		}
	}

	public Items FindItem(int y, int x) {
		Items item = new Items();
		// item array'inde parametreyle gelen koordinatlý itemi bulmak için.
		for (int i = 0; i < 20; i++) {
			if (itemarray[i].getX_coord() == x && itemarray[i].getY_coord() == y) {
				item = itemarray[i];
			}
		}
		return item;
	}

	public void TakeItem(int y, int x) {

		if (mazearray[y][x] == (Object) ' ') {
			mazearray[y][x] = (Object) h_player.getBackpack().pop();

			for (int i = 0; i < 20; i++) {
				if (itemarray[i] == null) {
					itemarray[i] = (Items) mazearray[y][x];
					break;
				}
			}
		}
		// when the player take item from the backpack player's energy decrease
		// 100 points.
		h_player.setEnergy(h_player.getEnergy() - 100);

	}

	public void LessThan20() {
		counter = 0;
		for (int i = 0; i < 21; i++) {// finding number of items in the maze.
			for (int j = 0; j < 55; j++) {
				if (mazearray[i][j] == (Object) '1' || mazearray[i][j] == (Object) '2'
						|| mazearray[i][j] == (Object) '3' || mazearray[i][j] == (Object) '4'
						|| mazearray[i][j] == (Object) '*') {
					counter++;
				}
			}
		}
		counter = counter + h_player.getBackpack().size();
		if (counter < 20) {
			// inputlist için yeni bir item oluþturma.
			for (int i = 0; i <= 19 - counter; i++) {
				// inputlistten dequeue yapýlcak ekranýn random kýsmýna
				int y = (int) (Math.random() * 20);
				int x = (int) (Math.random() * 54);

				while (mazearray[y][x] != (Object) ' ') {
					x = (int) (Math.random() * 54);
					y = (int) (Math.random() * 20);
				}
				// maze'e konulan itemi itemsarray'e atmak için.
				int a = 0;
				while (itemarray[a] == null) {
					a++;
				}
				Items temp = (Items) InputList.dequeue();
				mazearray[y][x] = temp.getType();
				itemarray[a] = temp;
				InputList.enqueue(Items.GenerateItem());
			}

		}

	}

	public void EnergyChange(int y, int x) {
		if (mazearray[y][x] == (Object) '*') {
			// eðer * alýrsa enerjisi bir artar.
			h_player.setEnergy(h_player.getEnergy() + 1);
		}

	}

	public static void MarkDot(Stack y, Stack x) {
		int size = x.size();
		for (int i = 1; i < size; i++) {
			mazearray[Computer.y_coord][Computer.x_coord]=' ';
			
			mazearray[(int) y.peek()][(int) x.peek()] = (Object) '.';
			tempx.push(x.pop());
			tempy.push(y.pop());			
		}
		Computer.y_coord=(int)y.peek();
		Computer.x_coord=(int)x.peek();
		mazearray[Computer.y_coord][Computer.x_coord] = (Object) c_player;		
		size = tempx.size();
		for (int i = 0; i < size; i++) {
			y.push(tempy.pop());
			x.push(tempx.pop());
		}
	}

	public static void ClearDot() {
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 55; j++) {
				if (mazearray[i][j] == (Object) '.') {
					mazearray[i][j] = (Object) ' ';
				}

			}
		}
	}

	
}
