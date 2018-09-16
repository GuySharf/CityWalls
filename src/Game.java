import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game {
	// declare game variables
	boolean hasKey, hasRing, hasNote, isDrunk, hasMachete, metFairy;
	String weapon, location = "";
	int hp, zombieHp = 20;
	Integer mazeLength = new Integer(1);
	ChoiceHandler handler;
	Map<Integer, String> mazeMap = new HashMap<>();
	Font plotFont = new Font("Deja Vu", Font.PLAIN, 30);
	Font borderFont = new Font("Times New Roman", Font.PLAIN, 25);
	JButton startButton, choice1, choice2, choice3, choice4, upperSaveButton, upperLoadButton;
	JPanel startPanel, title, plotPanel, choicePanel, playerInfoPanel;
	JTextArea plot;
	JLabel hpLabel, hpInt, weaponLabel, weaponString;

	JFrame window;
	Container container;

	private Game() {
	

		// Create Game Window
		window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		container = window.getContentPane();
		container.setBackground(Color.black);
		// Create new Title
		title = new Title();
		// Create Start Buttons and Panel
		startPanel = new JPanel();
		startPanel.setBounds(300, 400, 200, 100);
		startPanel.setBackground(Color.black);
		startPanel.setLayout(new GridLayout(2, 1));

		startButton = new JButton();
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(borderFont);
		startButton.setFocusPainted(false);
		startButton.setText("Start Game");
		startButton.setBounds(300, 400, 200, 100);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createGameWindow();
			}
		});

		/*loadButton = new JButton();
		loadButton.addActionListener(new SaveLoadHandler(this));
		loadButton.setBackground(Color.black);
		loadButton.setForeground(Color.white);
		loadButton.setFont(borderFont);
		loadButton.setFocusPainted(false);
		loadButton.setText("Load Game");
		loadButton.setBounds(300, 400, 200, 100);
		loadButton.setActionCommand("load");*/

		startPanel.add(startButton);
		//startPanel.add(loadButton);

		container.add(startPanel);
		container.add(title);

		window.setVisible(true);
	}

	public void createGameWindow() {
		// initiailise booleans

		hasKey = false;
		hasRing = false;
		hasNote = false;
		isDrunk = false;
		hasMachete = false;
		metFairy = false;

		// Disable MainWindow
		startPanel.setVisible(false);
		title.setVisible(false);
		// Create the main Game window.
		plotPanel = new JPanel();
		plotPanel.setBounds(100, 100, 600, 300);
		plotPanel.setBackground(Color.black);

		plot = new JTextArea();
		plot.setBounds(100, 100, 600, 300);
		plot.setBackground(Color.black);
		plot.setForeground(Color.white);
		plot.setFont(plotFont);
		plot.setLineWrap(true);
		plot.setText("Main Plot Goes Here");

		plotPanel.add(plot);

		// Create multi-choice panel
		choicePanel = new JPanel();
		choicePanel.setBounds(250, 400, 300, 150);
		choicePanel.setBackground(Color.black);
		choicePanel.setLayout(new GridLayout(4, 1));

		// Create upper label
		playerInfoPanel = new JPanel();
		playerInfoPanel.setBounds(100, 15, 600, 50);
		playerInfoPanel.setBackground(Color.black);
		playerInfoPanel.setLayout(new GridLayout(1, 6));
		choice1 = buttonInit(choice1);
		choice1.setActionCommand("c1");
		choicePanel.add(choice1);

		choice2 = buttonInit(choice2);
		choice2.setActionCommand("c2");
		choicePanel.add(choice2);

		choice3 = buttonInit(choice3);
		choice3.setActionCommand("c3");
		choicePanel.add(choice3);

		choice4 = buttonInit(choice4);
		choice4.setActionCommand("c4");
		choice4.setVisible(false);
		choicePanel.add(choice4);

		playerSetup();
		mapSetup();
		container.add(playerInfoPanel);
		container.add(plotPanel);
		container.add(choicePanel);

		// Start GAME.
		cityWalls();
	}

	public void playerSetup() {
		hp = 15;
		weapon = "Knife";

		hpLabel = new JLabel("HP: ");
		hpLabel.setFont(borderFont);
		hpLabel.setForeground(Color.white);
		playerInfoPanel.add(hpLabel);

		hpInt = new JLabel("" + hp);
		hpInt.setFont(borderFont);
		hpInt.setForeground(Color.white);
		playerInfoPanel.add(hpInt);

		weaponLabel = new JLabel("Weapon: ");
		weaponLabel.setFont(borderFont);
		weaponLabel.setForeground(Color.white);
		playerInfoPanel.add(weaponLabel);

		weaponString = new JLabel(weapon);
		weaponString.setFont(borderFont);
		weaponString.setForeground(Color.white);
		playerInfoPanel.add(weaponString);

		upperLoadButton = new JButton();
		upperLoadButton.addActionListener(new SaveLoadHandler(this));
		upperLoadButton.setBackground(Color.black);
		upperLoadButton.setForeground(Color.white);
		upperLoadButton.setFont(borderFont);
		upperLoadButton.setFocusPainted(false);
		upperLoadButton.setText("load");
		upperLoadButton.setActionCommand("load");
		playerInfoPanel.add(upperLoadButton);

		upperSaveButton = new JButton();
		upperSaveButton.addActionListener(new SaveLoadHandler(this));
		upperSaveButton.setBackground(Color.black);
		upperSaveButton.setForeground(Color.white);
		upperSaveButton.setFont(borderFont);
		upperSaveButton.setFocusPainted(false);
		upperSaveButton.setText("save");
		upperSaveButton.setActionCommand("save");
		playerInfoPanel.add(upperSaveButton);

	}

	public void mapSetup() {
		mazeMap.put(1, "c3");
		mazeMap.put(2, "c3");
		mazeMap.put(3, "c2");
		mazeMap.put(4, "c4");
		mazeMap.put(5, "c2");
		mazeMap.put(6, "c4");
		mazeMap.put(7, "c1");

	}

	public void cityWalls() {
		location = "cityWalls";
		plot.setText("You stand outside the City Walls!\nA guard stands nearby.");
		choice1.setText("Attack The Guard");
		choice2.setText("Talk To The Guard");
		choice3.setText("Leave");
		choice4.setVisible(false);

	}

	public void reluctantGuard() {
		location = "reluctantGuard";
		plot.setText("Guard: \"who goes there? Go away, stranger!\" \n\nThe guard turns around.");
		choice1.setText("Go Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void firstKnightFight() {
		location = "firstKnightFight";
		int dmg = smallHit();

		plot.setText("You attempt to hit the guard with your " + weapon.toLowerCase() + "."
				+ "\nThe guard turns around and hits you,\ndealing " + dmg + " damage."
				+ "\n\nThe guard seems unscathed.");

		choice1.setText("Hit Him Again!");
		choice2.setText("Go Back");
		choice3.setVisible(false);
		choice4.setVisible(false);
		if (hp <= 0) {
			death();
		}
	}

	public void crossroads() {
		location = "crossroads";
		plot.setText("You arrive to a crossroads, South of the city.\n\n Where do you go?");
		choice1.setText("Go North");
		choice2.setText("Go East");
		choice3.setText("Go South");
		choice4.setText("Go West");
	}

	public void lake() {
		location = "lake";
		plot.setText("You reach the edge of a small lake. \nA body lays at the shore.");
		choice1.setText("Search The Body");
		choice2.setText("Go For A Swim");
		choice3.setText("Rest For A While");
		choice4.setText("Go Back");
	}

	public void searchBody() {
		if (hasKey) {
			location = "searchBodyKey";
			plot.setText("You have already searched the body.");
			choice1.setText("Go Back");
			choice2.setVisible(false);
			choice3.setVisible(false);
			choice4.setVisible(false);
		} else {
			location = "searchBodyNoKey";
			plot.setText("As you approach the body, it suddenly\nsprings to life!");
			choice1.setText("Fight The Zombie");
			choice2.setText("Run Away");
			choice3.setVisible(false);
			choice4.setVisible(false);
		}
	}

	public void fightZombie() {
		int dmg = smallHit();
		int playerDmg = playerHit();
		zombieHp -= playerDmg;
		location = "fightZombie";

		plot.setText("You swing your " + weapon.toLowerCase() + " and slash the zombie,\ndealing " + playerDmg
				+ " damage.\n" + "The zombie bites your shoulder,\ndealing " + dmg + " damage.");

		choice1.setText("Strike Again");
		choice2.setText("Run Away");
		choice3.setVisible(false);
		choice4.setVisible(false);

		if (hp <= 0) {
			death();
		} else if (zombieHp <= 0) {
			deadZombie();
		}

	}

	public void deadZombie() {
		location = "deadZombie";
		plot.setText("You hit the zombie one last time and it falls to the ground, dead for good.");

		choice1.setText("Search The Zombie");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void searchZombie() {
		hasKey = true;
		location = "searchZombie";

		plot.setText("You search the dead body, and\nfind a silver key.\n\n\n+Silver Key");

		choice1.setText("Go Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void runZombie() {
		location = "runZombie";
		int dmg = smallHit();
		plot.setText("You flee from the zombie, but the zombie\nconnects one last time for " + dmg + " damage.");
		choice1.setText("Run To The Lake");
		choice2.setText("Run To The Crossroads");
		choice3.setVisible(false);
		choice4.setVisible(false);

		if (hp <= 0) {
			death();
		}
	}

	public void swim() {
		location = "swim";
		if (hasRing) {
			plot.setText("You swim around the lake.");
		} else {
			plot.setText("You swim around the lake, and see a shining object"
					+ " at the bottom.\nYou dive in and retrieve an emerald ring!\n\n+Emerald Ring");
			hasRing = true;
		}
		choice1.setText("Swim Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void lakeRest() {
		location = "lakeRest";

		if (hp < 15) {
			plot.setText("You sit down on a tree stump and relax for a while.\n\n Health Restored!");
			hp = 15;
			hpInt.setText("" + hp);
		} else {
			plot.setText("You sit down on a tree stump and relax for a while.");
		}
		choice1.setText("Stay");
		choice2.setText("Leave");
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void forest() {
		location = "forest";
		plot.setText("You reach the edge of a dense forest. There's a wooden hut to the east, and a trail"
				+ " leading deeper into the woods behind it. A faint\nsound of laughter comes from the west.");
		choice1.setText("Go To The Cabin");
		choice2.setText("Follow The Trail");
		choice3.setText("Head West");
		choice4.setText("Leave");
	}

	public void cabin() {
		location = "cabin";
		plot.setText("You search the cabin. There's a golden hand painted on the door. Inside the cabin"
				+ " you see two beds, but nothing else.");
		choice1.setText("Leave");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void trailStart() {
		location = "trailStart";
		plot.setText(
				"You follow the path further into the woods. \nAfter a few minutes, the path splits in multiple\ndirections.");
		choice1.setText("Go North");
		choice2.setText("Go South");
		choice3.setText("Go West");
		choice4.setText("Go East");
	}

	// exploring the maze
	public void deepTrail() {
		location = "deepTrail";
		plot.setText(
				"You follow the path further into the woods. \nAfter a few minutes, the path splits in multiple\ndirections.");
		choice1.setText("Go North");
		choice2.setText("Go South");
		choice3.setText("Go West");
		choice4.setText("Go East");
	}

	public void trailEnd() {
		location = "trailEnd";
		if (metFairy) {
			weapon = "Sword";
			weaponString.setText(weapon);
			plot.setText(
					"You finally leave the seemingly endless\nwoods, and reach the fairy's house. You step inside, and the fairy greets you."
							+ "\nFairy: \"Hello again! Here, I found this sword in the woods. You can have it!\""
							+ "\n\n+Sword");
		} else {
			plot.setText(
					"You finally leave the seemingly endless\nwoods, and see a house. The house seems\nto be empty.");
		}
		choice1.setText("Leave");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void denseWoods() {
		if (hasMachete) {
			location = "denseWoodsMachete";
			plot.setText("You investigate the area where the sounds of laughter were coming from."
					+ " The path is\nblocked by thick, dense branches. You chop them down with your"
					+ " machete, and reach a clearing.");
			choice1.setText("Enter The Clearing");
			choice2.setText("Leave");
		} else {
			location = "denseWoodsNoMachete";
			plot.setText("You investigate the area where the sounds of laughter were coming from."
					+ " The path is \nblocked by thick, dense branches.");
			choice1.setText("Leave");
			choice2.setVisible(false);
		}
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void clearing() {
		location = "clearing";
		if (hasRing) {
			hasRing = false;
			metFairy = true;
			plot.setText("You step inside the clearing and see a green fairy flying around.\n"
					+ "Fairy: \"Oh! You've found my ring! Thank you!"
					+ " come see me in my house, I'll reward you!\n\n-Emerald Ring");
		} else {
			plot.setText(
					"You step inside the clearing and see a green fairy flying around. She doesn't seem to notice you.");
		}
		choice1.setText("Leave");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void village() {
		location = "village";
		plot.setText("You reach a small village near the city.\nThere aren't many "
				+ "people around.\nThere's a notice board nearby.");
		choice1.setText("Explore The Village");
		choice2.setText("Read The Board");
		choice3.setText("Go To The Tavern");
		choice4.setText("Go Back");
	}

	public void villageExplore() {
		location = "villageExplore";
		plot.setText("You walk past the village houses, searching \nfor anything interesting to do."
				+ "\nAround the stables, you notice a silver door.");
		choice1.setText("Open The Door");
		choice2.setText("Turn Around");
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void silverDoor() {
		if (hasKey) {
			location = "silverDoorHasKey";
			plot.setText("The silver key fits perfectly!\nYou enter the stables and look around. \nOn the "
					+ "opposite wall, you see a note written\nin green color.");
			choice1.setText("Read The Note");
			choice2.setText("Leave");
		} else {
			location = "silverDoorNoKey";
			plot.setText("The door is locked.");
			choice1.setText("Go Back");
			choice2.setVisible(false);
		}
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void readNote() {
		location = "readNote";
		char left = 706;
		char right = 707;
		char up = 708;
		char down = 709;

		plot.setText("The note reads:\n\n" + left + " " + left + " " + down + " " + right + " " + down + " " + right
				+ " " + up + "\n\n+Note\n-Silver Key");
		hasKey = false;
		hasNote = true;
		choice1.setText("Go Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void villageBoard() {
		location = "villageBoard";
		plot.setText("There are 3 notices posted on the board.");
		choice1.setText("Read The First Note");
		choice2.setText("Read The Second Note");
		choice3.setText("Read The Third Note");
		choice4.setText("Go Back");

	}

	public void firstNotice() {
		location = "noticeBoard";
		plot.setText("You read a sun-dried notice:\n\n" + "\"WITCHER WANTED: A Leshen is prowling \nthe area, "
				+ "contact Mr. Branch for details.\"");
		choice1.setText("Go Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void secondNotice() {
		location = "noticeBoard";
		plot.setText("मेरे पास बताने की कोई और कहानी नहीं है");
		choice1.setText("Go Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void thirdNotice() {
		location = "noticeBoard";
		plot.setText("Rubies are red,\n" + "Emeralds are green,\n" + "Bring the sword to the city,\n"
				+ "If  you want to win!");
		choice1.setText("Go Back");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void villageTavern() {
		location = "villageTavern";

		plot.setText("You enter the Tavern. A lone man sits at the\ntable in the rear.");

		choice1.setText("Talk To The Bartender");
		choice2.setText("Talk To The Man");
		choice3.setText("Leave");
		choice4.setVisible(false);
	}

	public void bartender() {
		if (isDrunk) {
			location = "bartenderDrunk";
			plot.setText("Bartender: \"Get lost, drunk!\"");
			choice1.setText("Leave");
			choice2.setVisible(false);
			choice3.setVisible(false);
			choice4.setVisible(false);
		} else {
			location = "bartender";
			plot.setText("You approach the bartender. There are \nvarious containers in weird shapes,"
					+ " the likes of which you have never seen before."
					+ " On the counter, you notice three bottles: Red, Green and Blue.");
			choice1.setText("Order A Red Drink");
			choice2.setText("Order A Green Drink");
			choice3.setText("Order A Blue Drink");
			choice4.setText("Go Back");
		}
	}

	public void drink(String color) {
		location = "drink";
		if (color.equals("green")) {
			isDrunk = true;
			plot.setText("You take a sip from the green drink. In an instance, the world spins and"
					+ " you fall to the\nground. You rise up slowly back to your seat."
					+ "\n\nBartender: \"Get lost, drunk!\"");
		} else {
			plot.setText("You drink the " + color + " drink.\nIt doesn't taste very well.");
		}
		choice1.setText("Order Another Drink");
		choice2.setText("Leave");
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public void loneMan() {
		location = "loneMan";
		if (isDrunk) {
			if (hasMachete) {
				plot.setText("Man: \"Leave me alone with my drink!\"");
			} else {
				hasMachete = true;
				plot.setText("Man: \"Greetings stranger! Have you been to the woods south of town yet?\n"
						+ "Here, take this machete! It will help you go\ndeeper into the woods.\"\n\n"
						+ "You thank the lone man, and prepare to leave the tavern.");
			}
		} else {
			plot.setText("The man sits on his own with a small cup. He slurs indistinctly.");
		}
		choice1.setText("Leave");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void victory() {
		location = "victory";
		plot.setText("Guard: \"What? You found my sword! Thanks man! Here, you can go inside the city\".\n\nGAME OVER");
		choice1.setText("NEW GAME");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);

	}

	public void death() {
		location = "death";
		String death = plot.getText() + "\n\nThe blow was fatal! You are dead.";
		plot.setText(death);
		choice1.setText("New Game");
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
	}

	public int smallHit() {
		// handles low damage

		int dmg = (int) (Math.random() * 4);
		if (dmg == 0)
			dmg = 1;
		hp -= dmg;

		hpInt.setText("" + hp);
		return dmg;
	}

	public int mediumHit() {
		// handles medium damage

		int dmg = (int) (Math.random() * 4) + 3;
		hp -= dmg;

		hpInt.setText("" + hp);
		return dmg;
	}

	public int bigHit() {
		// handles high damage

		int dmg = (int) (Math.random() * 4) + 6;
		hp -= dmg;

		hpInt.setText("" + hp);
		return dmg;
	}

	public int playerHit() {
		int dmg;
		switch (weapon) {
		default:
			dmg = (int) ((Math.random() * 4) + 3);
			break;
		case "Club":
			dmg = (int) ((Math.random() * 4) + 6);
			break;
		case "Sword":
			dmg = (int) ((Math.random() * 4) + 9);
			break;
		}
		return dmg;
	}

	public JButton buttonInit(JButton button) {
		handler = new ChoiceHandler(this);
		button = new JButton();
		button.addActionListener(handler);
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.setFont(borderFont);
		button.setFocusPainted(false);
		return button;
	}

	public static void main(String[] args) {
		new Game();
	}
}
