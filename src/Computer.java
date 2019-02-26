public class Computer {

	public static int energy = 100, coorX, coorY, speed, x_coord, y_coord;
	private char name = 'C';
	public static Stack temp_pathX = new Stack(1200);
	public static Stack temp_pathY = new Stack(1200);
	public static Stack pathX = new Stack(1200);
	public static Stack pathY = new Stack(1200);
	public static Stack markpathX = new Stack(1200);
	public static Stack markpathY = new Stack(1200);
	private int[][] tempMaze = new int[21][55];
	private int difference_X = 0, difference_Y = 0, humanX, humanY, sizeTemp;
	boolean IsGameOver = true;

	// CONSTRUCTOR
	public Computer(int energy, int coorX, int coorY, int speed, char name) {
		this.energy = energy;
		this.coorX = coorX;
		this.coorY = coorY;
		this.speed = speed;
		this.name = name;
	}

	// EMPTY CONSTRUCTOR
	public Computer() {

	}

	// GETTER-SETTER
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getcoorX() {
		return coorX;
	}

	public void setcoorX(int coorX) {
		this.coorX = coorX;
	}

	public int getcoorY() {
		return coorY;
	}

	public void setcoorY(int coorY) {
		this.coorY = coorY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Stack getTemp_pathX() {
		return temp_pathX;
	}

	public void setTemp_pathX(Stack temp_pathX) {
		this.temp_pathX = temp_pathX;
	}

	public Stack getTemp_pathY() {
		return temp_pathY;
	}

	public void setTemp_pathY(Stack temp_pathY) {
		this.temp_pathY = temp_pathY;
	}

	public Stack getPathX() {
		return pathX;
	}

	public void setPathX(Stack pathX) {
		this.pathX = pathX;
	}

	public Stack getPathY() {
		return pathY;
	}

	public void setPathY(Stack pathY) {
		this.pathY = pathY;
	}

	public int[][] getTempMaze() {
		return tempMaze;
	}

	public void setTempMaze(int[][] tempMaze) {
		this.tempMaze = tempMaze;
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

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	// METHODS

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

	public void FillingMaze(Object[][] maze) {

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 55; j++) {
				if (maze[i][j] == (Object) ' ') {
					tempMaze[i][j] = 0;
				} else if (maze[i][j] == (Object) '1' || maze[i][j] == (Object) '2' || maze[i][j] == (Object) '3'
						|| maze[i][j] == (Object) '4' || maze[i][j] == (Object) '.' || maze[i][j] == (Object) '#') {
					tempMaze[i][j] = 1;
				}
			}

		}
		// humanýn olduðu yer boþ.
		tempMaze[humanY][humanX] = 0;
	}

	public void PathFinding(int hy, int hx) {
		// stackleri boþaltma.
		int sizetemp = temp_pathX.size();
		for (int i = 0; i < sizetemp; i++) {
			temp_pathX.pop();
			temp_pathY.pop();
		}
		sizetemp = pathX.size();
		for (int i = 0; i < sizetemp; i++) {
			pathY.pop();
			pathX.pop();
		}
		// maze'in içindeki noktalarý siler.
		Maze.ClearDot();
		// maze arrayini kopyalýyor.
		FillingMaze(Maze.mazearray);

		coorX = getX_coord();
		coorY = getY_coord();

		// human's coordinates.
		humanX = hx;
		humanY = hy;
		// difference between human and computer.
		difference_X = coorX - hx;
		difference_Y = coorY - hy;
		tempMaze[hy][hx] = 0;
		while (difference_X != 0 || difference_Y != 0) {
			// computer'ýn olduðu yeri dolu yapmak için.
			tempMaze[coorY][coorX] = 1;

			if (!temp_pathX.isEmpty()) {
				sizeTemp = temp_pathX.size();
			}

			// If the computer is right and below of the human.
			if (difference_X > 0 && difference_Y > 0) {

				// BOTTOM
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);

				}
				// RIGHT
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);
				}
				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);
				}
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);
				}

			}

			// If the computer is right and above of the human.
			else if (difference_X > 0 && difference_Y < 0) {

				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);

				}

				// RIGHT
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);

				}
				// BOTTOM
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);

				}
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);

				}
			}

			// computer humanýn solunda ve aþaðýsýndaysa.
			else if (difference_X < 0 && difference_Y > 0) {

				// BOTTOM(last)
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);

				}
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);

				}
				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);

				}
				// RIGHT(FIRST)
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);
				}
			}

			// If the computer is left and above of the human.
			else if (difference_X < 0 && difference_Y < 0) {
				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);

				}
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);

				}
				// BOTTOM
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);

				}

				// RIGHT(FIRST)
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);
				}
			}

			// If the computer is below of the human.

			else if (difference_X == 0 && difference_Y > 0) {
				// BOTTOM(last)
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);

				}
				// // LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);

				}
				// // RIGHT
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);

				}

				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);
				}
			}

			// If the computer is above the human.
			else if (difference_X == 0 && difference_Y < 0) {
				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);

				}
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);

				}

				// RIGHT
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);

				}

				// BOTTOM(first)
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);
				}
			}

			// If the computer is right of the human.
			else if (difference_X > 0 && difference_Y == 0) {
				// RIGHT(FIRST)
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);

				}
				// UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);

				}
				// // BOTTOM
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);
				}
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);
				}
			}
			// If the computer is left of the human.
			else if (difference_X < 0 && difference_Y == 0) {
				// LEFT
				if (tempMaze[coorY][coorX - 1] == 0) {
					temp_pathX.push(coorX - 1);
					temp_pathY.push(coorY);

				}
				// // UP
				if (tempMaze[coorY - 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY - 1);

				}
				// // BOTTOM
				if (tempMaze[coorY + 1][coorX] == 0) {
					temp_pathX.push(coorX);
					temp_pathY.push(coorY + 1);

				}

				// RIGHT(FIRST)
				if (tempMaze[coorY][coorX + 1] == 0) {
					temp_pathX.push(coorX + 1);
					temp_pathY.push(coorY);
				}

			}

			MarkAll();
			// Pushing the Path Stack.
			if (!temp_pathX.isEmpty() && !temp_pathY.isEmpty()) {
				pathX.push(temp_pathX.pop());
				pathY.push(temp_pathY.pop());
			}
			// If there is no way to go.
			else if (sizeTemp == temp_pathX.size()) {
				if (!pathX.isEmpty() && !pathY.isEmpty()) {
					pathX.pop();
					pathY.pop();
				}
			}
			if (!pathX.isEmpty()) {
				coorY = (int) pathY.peek();
			}
			if (!pathY.isEmpty()) {
				coorX = (int) pathX.peek();
			}
			difference_X = coorX - hx;
			difference_Y = coorY - hy;
		}
		// humanýn olduðu yer.
		pathX.pop();
		pathY.pop();
		// To draw the dots.
		Maze.MarkDot(pathY, pathX);
		
	}

	private void MarkAll() {
		int size = pathX.size();
		for (int i = 0; i < size; i++) {
			markpathX.push(pathX.pop());
			markpathY.push(pathY.pop());
			tempMaze[(int) markpathY.peek()][(int) markpathX.peek()] = 1;
		}
		size = markpathX.size();
		for (int i = 0; i < size; i++) {
			pathX.push(markpathX.pop());
			pathY.push(markpathY.pop());
		}

	}

	public void MarkMaze(int y, int x) {
		tempMaze[y][x] = 1;
	}

	public void EnergyChange() {
		if (energy == 0) {
			speed = speed % 2;
		}
	}

}
