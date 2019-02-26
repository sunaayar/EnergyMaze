import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import enigma.console.TextAttributes;
import enigma.core.Enigma;
import enigma.event.TextMouseListener;

public class Game {
	private int game_time;
	private Maze maze = new Maze();
	private Items items = new Items();

	// ---------------
	public static TextMouseListener tmlis;
	public static KeyListener klis;

	// ------ Standard variables for mouse and keyboard ------
	public static int mousepr; // mouse pressed?
	public static int mousex, mousey; // mouse text coords.
	public static int keypr; // key pressed?
	public static int rkey; // key (for press/release)
	public static int time = 0;

	// CONSTRUCTOR
	public Game(int game_time, Maze maze) {
		this.game_time = game_time;
		this.maze = maze;
	}

	// GETTER-SETTER
	public int getGame_time() {
		return game_time;
	}

	public void setGame_time(int game_time) {
		this.game_time = game_time;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	// METHODS
	public void CountTime() {
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------

	enigma.console.Console cn = Enigma.getConsole("Energy Maze", 150, 50, false);

	// -------------------------------------------------------------------------
	Game() throws Exception {
		int option = 0;
		while (option != 1) {
			cn.getTextWindow().setCursorPosition(-1, -1);
			for (int i = 0; i < 30; i++) {
				for (int j = 0; j < 70; j++) {
					System.out.print(' ');
				}
			}
			cn.getTextWindow().setCursorPosition(-1, -1);
			TextAttributes attrs = new TextAttributes(Color.white, Color.black);
			cn.setTextAttributes(attrs);
			Scanner input = new Scanner(System.in);
			System.out.println("    --- Energy Maze ---");
			System.out.println();
			System.out.println(" Welcome to Energy Maze. Please choose an option:");
			System.out.println("1. Start game");
			System.out.println("2. Choose map");
			System.out.println("3. How to play");
			System.out.print("Your Option: ");

			option = (int) input.nextInt();
			int map = 1;
			switch (option) {
			case 1: {
				int counter = -1;

				try {
					if (map == 1) {
						BufferedReader in = new BufferedReader(new FileReader("maze.txt"));
						String line;

						// reads txt and put the Maze.mazearray.
						while ((line = in.readLine()) != null) {
							counter++;
							for (int i = 0; i < line.length(); i++) {
								Maze.mazearray[counter][i] = line.toCharArray()[i];
							}

						}

						// PRINTING SCREEN

						// generating items and put them into Maze.mazearray.
						ItemsIntoMaze();
						// printing Maze Array.
						PrintScreen();
						YanTaraf();
						PrintBackpack();

						in.close();
					}
					else if (map == 2) {
						BufferedReader in = new BufferedReader(new FileReader("maze2.txt"));
						String line;

						// reads txt and put the Maze.mazearray.
						while ((line = in.readLine()) != null) {
							counter++;
							for (int i = 0; i < line.length(); i++) {
								Maze.mazearray[counter][i] = line.toCharArray()[i];
							}

						}

						// PRINTING SCREEN

						// generating items and put them into Maze.mazearray.
						ItemsIntoMaze();
						// printing Maze Array.
						PrintScreen();
						YanTaraf();
						PrintBackpack();

						in.close();
					}
					else if (map == 3) {
						BufferedReader in = new BufferedReader(new FileReader("maze3.txt"));
						String line;

						// reads txt and put the Maze.mazearray.
						while ((line = in.readLine()) != null) {
							counter++;
							for (int i = 0; i < line.length(); i++) {
								Maze.mazearray[counter][i] = line.toCharArray()[i];
							}

						}

						// PRINTING SCREEN

						// generating items and put them into Maze.mazearray.
						ItemsIntoMaze();
						// printing Maze Array.
						PrintScreen();
						YanTaraf();
						PrintBackpack();

						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				enigma.console.Console cn1 = Enigma.getConsole("Mouse and Keyboard");

				// placing Human and Computer randomly.
				int c_rndy = (int) (Math.random() * 20);
				int c_rndx = (int) (Math.random() * 54);
				while (Maze.mazearray[c_rndy][c_rndx] != (Object) ' ') {
					c_rndx = (int) (Math.random() * 54);
					c_rndy = (int) (Math.random() * 20);
				}
				Maze.mazearray[c_rndy][c_rndx] = (Object) maze.getC_player();
				maze.getC_player().setX_coord(c_rndx);
				maze.getC_player().setY_coord(c_rndy);

				int h_rndx = (int) (Math.random() * 54);
				int h_rndy = (int) (Math.random() * 20);
				while (Maze.mazearray[h_rndy][h_rndx] != (Object) ' ') {
					h_rndx = (int) (Math.random() * 54);
					h_rndy = (int) (Math.random() * 20);
				}
				int px = h_rndx;
				int py = h_rndy;

				Maze.mazearray[py][px] = (Object) maze.getH_player();
				maze.getH_player().setX_coord(h_rndx);
				maze.getH_player().setY_coord(h_rndy);
				PrintScreen();

				System.out.println();
				klis = new KeyListener() {
					public void keyTyped(KeyEvent e) {
					}

					public void keyPressed(KeyEvent e) {
						if (keypr == 0) {
							keypr = 1;
							rkey = e.getKeyCode();
						}
					}

					public void keyReleased(KeyEvent e) {
					}
				};
				cn1.getTextWindow().addKeyListener(klis);
				// ----------------------------------------------------

				while (true) {

					if (keypr == 1) { // if keyboard button pressed

						if (rkey == KeyEvent.VK_W) {// up

							if (Maze.mazearray[py - 1][px] == (Object) '1' || Maze.mazearray[py - 1][px] == (Object) '2'
									|| Maze.mazearray[py - 1][px] == (Object) '3'
									|| Maze.mazearray[py - 1][px] == (Object) '4'
									|| Maze.mazearray[py - 1][px] == (Object) '*') {

								Items item = maze.FindItem(py - 1, px);
								maze.getH_player().AddBackpackItem(item);
								Maze.mazearray[py - 1][px] = (Object) maze.getH_player();
								Maze.mazearray[py - 1][px] = (Object) ' ';
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();
								PrintBackpack();
							}

						}
						if (rkey == KeyEvent.VK_A) {// left

							if (Maze.mazearray[py][px - 1] == (Object) '1' || Maze.mazearray[py][px - 1] == (Object) '2'
									|| Maze.mazearray[py][px - 1] == (Object) '3'
									|| Maze.mazearray[py][px - 1] == (Object) '4'
									|| Maze.mazearray[py][px - 1] == (Object) '*') {
								Items item = maze.FindItem(py, px - 1);
								maze.getH_player().AddBackpackItem(item);
								Maze.mazearray[py][px - 1] = (Object) maze.getH_player();
								Maze.mazearray[py][px - 1] = (Object) ' ';
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();
								PrintBackpack();
							}
						}
						if (rkey == KeyEvent.VK_S) {// down

							if (Maze.mazearray[py + 1][px] == (Object) '1' || Maze.mazearray[py + 1][px] == (Object) '2'
									|| Maze.mazearray[py + 1][px] == (Object) '3'
									|| Maze.mazearray[py + 1][px] == (Object) '4'
									|| Maze.mazearray[py + 1][px] == (Object) '*') {
								Items item = maze.FindItem(py + 1, px);
								maze.getH_player().AddBackpackItem(item);
								Maze.mazearray[py + 1][px] = (Object) maze.getH_player();
								Maze.mazearray[py + 1][px] = (Object) ' ';
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();
								PrintBackpack();
							}
						}
						if (rkey == KeyEvent.VK_D) {// right

							if (Maze.mazearray[py][px + 1] == (Object) '1' || Maze.mazearray[py][px + 1] == (Object) '2'
									|| Maze.mazearray[py][px + 1] == (Object) '3'
									|| Maze.mazearray[py][px + 1] == (Object) '4'
									|| Maze.mazearray[py][px + 1] == (Object) '*') {
								Items item = maze.FindItem(py, px + 1);
								maze.getH_player().AddBackpackItem(item);
								Maze.mazearray[py][px + 1] = (Object) maze.getH_player();
								Maze.mazearray[py][px + 1] = (Object) ' ';
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();
								PrintBackpack();
							}
						}
						if (rkey == KeyEvent.VK_I) {

							// up
							maze.TakeItem(py - 1, px);
							maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
									maze.getH_player().getX_coord());
							PrintScreen();
							PrintBackpack();
						}
						if (rkey == KeyEvent.VK_J) {

							// left
							maze.TakeItem(py, px - 1);
							maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
									maze.getH_player().getX_coord());
							PrintScreen();
							PrintBackpack();
						}
						if (rkey == KeyEvent.VK_K) {

							// down
							maze.TakeItem(py + 1, px);
							maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
									maze.getH_player().getX_coord());
							PrintScreen();
							PrintBackpack();
						}
						if (rkey == KeyEvent.VK_L) {

							// right
							maze.TakeItem(py, px + 1);
							maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
									maze.getH_player().getX_coord());
							PrintScreen();
							PrintBackpack();
						}
						if (rkey == KeyEvent.VK_LEFT) {

							if (Maze.mazearray[py][px - 1] != (Object) '#') {

								if (Maze.mazearray[py][px - 1] == (Object) '1'
										|| Maze.mazearray[py][px - 1] == (Object) '2'
										|| Maze.mazearray[py][px - 1] == (Object) '3'
										|| Maze.mazearray[py][px - 1] == (Object) '4') {
									if (Maze.mazearray[py][px - 2] != (Object) '#') {
										px--;
									}
									maze.PushItem(py, px, "left");
								} else if (maze.getMazearray()[py][px - 1] == (Object) '*') {
									maze.PushItem(py, px - 1, "left");
									px--;
								} else if (Maze.mazearray[py][px - 1] == (Object) ' ') {
									Maze.mazearray[py][px - 1] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = (Object) ' ';
									px--;
								} else if (Maze.mazearray[py][px - 1] == (Object) '.') {
									Maze.mazearray[py][px - 1] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = (Object) ' ';
									px--;

								}
								PrintScreen();
								maze.getH_player().setX_coord(px);
								maze.getH_player().setY_coord(py);
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();
							}
						}
						if (rkey == KeyEvent.VK_RIGHT) {

							if (Maze.mazearray[py][px + 1] != (Object) '#') {

								if (Maze.mazearray[py][px + 1] == (Object) '1'
										|| Maze.mazearray[py][px + 1] == (Object) '2'
										|| Maze.mazearray[py][px + 1] == (Object) '3'
										|| Maze.mazearray[py][px + 1] == (Object) '4') {
									if (Maze.mazearray[py][px + 2] != (Object) '#') {
										px++;
									}
									maze.PushItem(py, px, "right");
								} else if (maze.getMazearray()[py][px + 1] == (Object) '*') {
									maze.PushItem(py, px + 1, "right");
									px++;
								} else if (Maze.mazearray[py][px + 1] == (Object) ' ') {

									Maze.mazearray[py][px + 1] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = (Object) ' ';
									px++;

								} else if (Maze.mazearray[py][px + 1] == (Object) '.') {

									Maze.mazearray[py][px + 1] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = (Object) ' ';
									px++;

								}

							}
							PrintScreen();
							maze.getH_player().setX_coord(px);
							maze.getH_player().setY_coord(py);
							maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
									maze.getH_player().getX_coord());
							PrintScreen();

						}

						if (rkey == KeyEvent.VK_UP) {

							if (Maze.mazearray[py - 1][px] != (Object) '#') {
								if (Maze.mazearray[py - 1][px] == (Object) '1'
										|| Maze.mazearray[py - 1][px] == (Object) '2'
										|| Maze.mazearray[py - 1][px] == (Object) '3'
										|| Maze.mazearray[py - 1][px] == (Object) '4') {
									if (Maze.mazearray[py - 2][px] == (Object) ' ') {
										py--;
									}
									maze.PushItem(py, px, "up");
								} else if (maze.getMazearray()[py - 1][px] == (Object) '*') {
									maze.PushItem(py - 1, px, "up");
									py--;
								} else if (Maze.mazearray[py - 1][px] == (Object) ' ') {
									Maze.mazearray[py - 1][px] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = ' ';
									py--;

								} else if (Maze.mazearray[py - 1][px] == (Object) '.') {
									Maze.mazearray[py - 1][px] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = ' ';
									py--;

								}
								PrintScreen();
								maze.getH_player().setX_coord(px);
								maze.getH_player().setY_coord(py);
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();
							}
						}

						if (rkey == KeyEvent.VK_DOWN) {

							if (Maze.mazearray[py + 1][px] != (Object) '#') {
								if (Maze.mazearray[py + 1][px] == (Object) '1'
										|| Maze.mazearray[py + 1][px] == (Object) '2'
										|| Maze.mazearray[py + 1][px] == (Object) '3'
										|| Maze.mazearray[py + 1][px] == (Object) '4') {
									if (Maze.mazearray[py + 2][px] == (Object) ' ') {
										py++;
									}
									maze.PushItem(py, px, "down");
								} else if (maze.getMazearray()[py + 1][px] == (Object) '*') {
									maze.PushItem(py + 1, px, "down");
									py++;
								} else if (Maze.mazearray[py + 1][px] == (Object) ' ') {
									Maze.mazearray[py + 1][px] = maze.getH_player();
									Maze.mazearray[py][px] = (Object) ' ';
									py++;

								} else if (Maze.mazearray[py + 1][px] == (Object) '.') {
									Maze.mazearray[py + 1][px] = Maze.mazearray[py][px];
									Maze.mazearray[py][px] = (Object) ' ';
									py++;

								}
								PrintScreen();
								maze.getH_player().setX_coord(px);
								maze.getH_player().setY_coord(py);
								maze.getC_player().PathFinding(maze.getH_player().getY_coord(),
										maze.getH_player().getX_coord());
								PrintScreen();

							}
						}

						char rckey = (char) rkey;
						// left right up down
						if (rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(') {
							Maze.mazearray[py][px] = (Object) maze.getH_player();

						}
						if (rkey == KeyEvent.VK_SPACE) {
							String str;
							str = cn1.readLine(); // keyboardlistener running
													// and
													// readline input by using
													// enter
							cn1.getTextWindow().setCursorPosition(5, 20);
							cn1.getTextWindow().output(str);
						}

						keypr = 0; // last action
					}
					// TIME
					PrintTime();
					Thread.sleep(50);
					counter++;

					cn.getTextWindow().setCursorPosition(70, 5);
					// human'ýn yavaþlamasý
					if (maze.getH_player().getEnergy() <= 0) {

					}

					// bir saniye (20 milisaniye*50=1000=1 saniye)
					if (counter % 20 == 0) {
						// decreasing item's time.
						time++;
						System.out.print(time);
						maze.DecreaseTime();
						PrintScreen();
						// if (Math.abs((int) maze.getC_player().getX_coord())
						// - Math.abs((int) maze.getH_player().getX_coord()) ==
						// 1
						// && (Math.abs((int) maze.getC_player().getY_coord())
						// - Math.abs((int) maze.getH_player().getY_coord()) ==
						// 0)
						// || Math.abs((int) maze.getC_player().getX_coord())
						// - Math.abs((int) maze.getH_player().getX_coord()) ==
						// 0
						// && (Math.abs((int) maze.getC_player().getY_coord())
						// - Math.abs((int) maze.getH_player().getY_coord()) ==
						// 1)) {
						//
						// if (maze.getC_player().getEnergy() >
						// maze.getH_player().getEnergy()) {
						// cn.getTextWindow().setCursorPosition(80, 5);
						// System.out.print("THE GAME IS OVER / YOU LOST");
						//
						// } else if (maze.getC_player().getEnergy() <
						// maze.getH_player().getEnergy()) {
						// cn.getTextWindow().setCursorPosition(80, 5);
						// System.out.print("THE GAME IS OVER / YOU WÝN");
						// }
						//
						// }

					}
					maze.LessThan20();
					PrintInputList();
				}
			}
			case 2: {
				cn.getTextWindow().setCursorPosition(-1, -1);
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 70; j++) {
						System.out.print(' ');
					}
				}
				cn.getTextWindow().setCursorPosition(-1, -1);
				System.out.println();
				System.out.println("  Please choose a map:");
				System.out.println("  1. Maze 1");
				System.out.println("  2. Maze 2");
				System.out.println("  3. Maze 3");
				map = input.nextInt();
				switch (map) {
				case 1: {
					map = 1;
					input.next();
					break;
				}
				case 2: {
					map = 2;
					input.next();
					break;
				}
				case 3: {
					map = 3;
					input.next();
					break;
				}
				}
				break;
			}
			case 3: {

				cn.getTextWindow().setCursorPosition(-1, -1);
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 70; j++) {
						System.out.print(' ');
					}
				}
				cn.getTextWindow().setCursorPosition(-1, -1);
				System.out.println("    --- How To Play ---");
				System.out.println();
				System.out.println("-The game is played in a maze.");
				System.out.println("-The aim is to collect energy items in the maze.");
				System.out.println("-These items are numbers and stars.");
				System.out.println("-The player has to evade a computer player, if they get caught, the game is over.");
				System.out.println(
						"-The stars are 25 energy (50 for computer player), while bringing the same numbers together gives extra energy.");
				System.out.println("-Energy items disappear after 100 seconds.");
				System.out.println("-The player with the highest energy wins at the end.");
				input.next();
				break;

			}
			}
		}
	}

	public void PrintInputList() {
		cn.getTextWindow().setCursorPosition(60, 2);
		for (int i = 0; i < 10; i++) {
			Items temp = (Items) maze.getInputList().peek();
			System.out.print(temp.getType());
			maze.getInputList().enqueue(maze.getInputList().dequeue());
		}
	}

	public void ItemsIntoMaze() {
		Items item;
		int rndlocationx = 0;
		int rndlocationy = 0;
		for (int i = 0; i < 20; i++) {// there will be 20 items.
			item = Items.GenerateItem();// item is generated.
			while (Maze.mazearray[rndlocationy][rndlocationx] != (Object) ' ') {
				rndlocationx = (int) (Math.random() * 54);
				rndlocationy = (int) (Math.random() * 20);
			}
			item.setX_coord(rndlocationx);
			item.setY_coord(rndlocationy);
			// maze'in içindeki itemlerý tutan array.
			maze.getItemarray()[i] = item;
			Maze.mazearray[rndlocationy][rndlocationx] = item.getType();
		}
	}

	public void InputList() {
		for (int i = 0; i < 10; i++) {
			items = Items.GenerateItem();
			maze.getInputList().enqueue(items);
			System.out.print(items.getType());
			maze.getInputList().enqueue(maze.getInputList().dequeue());

		}

	}

	public void PrintScreen() {
		cn.getTextWindow().setCursorPosition(-1, -1);

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 55; j++) {
				if (Maze.mazearray[i][j] == maze.getH_player()) {
					TextAttributes attrs = new TextAttributes(Color.green, Color.black);
					cn.setTextAttributes(attrs);
					System.out.print(maze.getH_player().getName());
				}

				else if (Maze.mazearray[i][j] == (Object) '*' || Maze.mazearray[i][j] == (Object) '1'
						|| Maze.mazearray[i][j] == (Object) '2' || Maze.mazearray[i][j] == (Object) '3'
						|| Maze.mazearray[i][j] == (Object) '4') {
					TextAttributes attrs = new TextAttributes(Color.yellow, Color.black);
					cn.setTextAttributes(attrs);
					System.out.print(Maze.mazearray[i][j]);
				} else if (Maze.mazearray[i][j] == (Object) '#') {
					TextAttributes attrs = new TextAttributes(Color.blue, Color.blue);
					cn.setTextAttributes(attrs);
					System.out.print(Maze.mazearray[i][j]);
				} else if (Maze.mazearray[i][j] == maze.getC_player()) {
					TextAttributes attrs = new TextAttributes(Color.red, Color.black);
					cn.setTextAttributes(attrs);
					System.out.print(maze.getC_player().getName());
				}

				else {
					TextAttributes attrs = new TextAttributes(Color.white, Color.black);
					cn.setTextAttributes(attrs);
					System.out.print(Maze.mazearray[i][j]);
				}
				TextAttributes attrs = new TextAttributes(Color.white, Color.black);
				cn.setTextAttributes(attrs);
			}
			System.out.println();

		}
		cn.getTextWindow().setCursorPosition(60, 17);
		System.out.println("H: " + maze.getH_player().getEnergy());

		cn.getTextWindow().setCursorPosition(60, 18);
		System.out.println("C: " + maze.getC_player().getEnergy());
	}

	public void YanTaraf() {
		TextAttributes attrs = new TextAttributes(Color.white, Color.black);
		cn.setTextAttributes(attrs);
		cn.getTextWindow().setCursorPosition(60, 0);
		System.out.println("Input");

		cn.getTextWindow().setCursorPosition(60, 1);
		System.out.println("<<<<<<<<<<");

		cn.getTextWindow().setCursorPosition(60, 2);
		InputList();

		cn.getTextWindow().setCursorPosition(60, 3);
		System.out.println("<<<<<<<<<<");

		cn.getTextWindow().setCursorPosition(60, 15);
		System.out.println("Energy");

		cn.getTextWindow().setCursorPosition(60, 16);
		System.out.print("-------");

	}

	public void PrintBackpack() {
		TextAttributes attrs = new TextAttributes(Color.white, Color.black);
		cn.setTextAttributes(attrs);
		cn.getTextWindow().setCursorPosition(60, 7);
		System.out.println("Backpack");
		for (int i = 8; i < 13; i++) {
			cn.getTextWindow().setCursorPosition(60, i);
			System.out.print("|");
			cn.getTextWindow().setCursorPosition(64, i);
			System.out.print("|");
		}

		for (int i = 8; i < 13; i++) {

			if (!maze.getH_player().getBackpack().isEmpty()) {
				char x = (char) maze.getH_player().getBackpack().peek();
				cn.getTextWindow().setCursorPosition(62, i);
				System.out.println(x);
				maze.getH_player().getTemp().push(maze.getH_player().getBackpack().pop());
			} else if (maze.getH_player().getBackpack().isEmpty()) {
				for (int j = 8; j < 13; j++) {
					cn.getTextWindow().setCursorPosition(62, i);
					System.out.println(" ");
				}
			}
		}
		cn.getTextWindow().setCursorPosition(60, 13);
		System.out.println("+---+");
		// tekrar backpack stack'ine atar.
		if (!maze.getH_player().getTemp().isEmpty()) {
			int a = maze.getH_player().getTemp().size();
			for (int k = 0; k < a; k++) {
				maze.getH_player().getBackpack().push(maze.getH_player().getTemp().pop());
			}
		}
	}

	public void PrintTime() {
		TextAttributes attrs = new TextAttributes(Color.white, Color.black);
		cn.setTextAttributes(attrs);
		cn.getTextWindow().setCursorPosition(60, 5);
		System.out.println("TIME : ");

	}
}
