import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceHandler implements ActionListener {
	private Game game;

	public ChoiceHandler(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// make all buttons visible
			game.choice2.setVisible(true);
			game.choice3.setVisible(true);
			game.choice4.setVisible(true);
	
		// get player's choice
		String choice = e.getActionCommand();
		// get player's location
		switch (game.location) {

	

		// after death

		case "death":
			switch (choice) {
			case "c1":
				game.weapon = "Knife";
				game.hp = 15;
				game.weaponString.setText(game.weapon);
				game.hpInt.setText("" + game.hp);
				game.zombieHp = 20;
				game.hasKey = false;
				game.hasRing = false;
				game.hasNote = false;
				game.isDrunk = false;
				game.hasMachete = false;
				game.metFairy = false;
				game.cityWalls();
				break;
			}
			break;
		// after victory
		case "victory": {
			game.weapon = "Knife";
			game.hp = 15;
			game.weaponString.setText(game.weapon);
			game.hpInt.setText("" + game.hp);
			game.zombieHp = 20;
			game.hasKey = false;
			game.hasRing = false;
			game.hasNote = false;
			game.isDrunk = false;
			game.hasMachete = false;
			game.metFairy = false;
			game.cityWalls();
			break;
		}
		// city entrance
		case "cityWalls":
			if (!game.weapon.equals("Sword")) {
				switch (choice) {
				case "c1":
					game.firstKnightFight();
					break;
				case "c2":
					game.reluctantGuard();
					break;
				case "c3":
					game.crossroads();
					break;
				}

			} else {
				game.victory();
				break;
			}
			break;

		// talking to guard

		case "reluctantGuard":
			switch (choice) {
			default:
				game.cityWalls();
				break;
			}

			break;

		// fighting the guard
		case "firstKnightFight":
			switch (choice) {
			case "c1":
				game.firstKnightFight();
				break;
			case "c2":
				game.cityWalls();
				break;
			}
			break;

		// at the crossroads
		case "crossroads":
			switch (choice) {
			case "c1":
				game.cityWalls();
				break;
			case "c2":
				game.lake();
				break;
			case "c3":
				game.forest();
				break;
			case "c4":
				game.village();
				break;
			}
			break;

		// at the lake
		case "lake":
			switch (choice) {
			case "c1":
				game.searchBody();
				break;
			case "c2":
				game.swim();
				break;
			case "c3":
				game.lakeRest();
				break;
			case "c4":
				game.crossroads();
				break;
			}
			break;

		// searching the body

		case "searchBodyKey":
			switch (choice) {
			default:
				game.lake();
				break;
			}
			break;
		// searching the body again
		case "searchBodyNoKey":
			switch (choice) {
			case "c1":
				game.fightZombie();
				break;
			case "c2":
				game.runZombie();
				break;
			}
			break;

		// zombie fight
		case "fightZombie":
			switch (choice) {
			case "c1":
				game.fightZombie();
				break;
			case "c2":
				game.runZombie();
				break;
			}
			break;

		// after fight
		case "deadZombie":
			switch (choice) {
			default:
				game.searchZombie();
				break;
			}
			break;

		// finding key
		case "searchZombie":
			switch (choice) {
			default:
				game.lake();
				break;
			}
			break;

		// running from zombie
		case "runZombie":
			switch (choice) {
			case "c1":
				game.lake();
				break;
			case "c2":
				game.crossroads();
				break;
			}
			break;

		// resting at the lake
		case "lakeRest":
			switch (choice) {
			case "c1":
				game.lakeRest();
				break;
			case "c2":
				game.lake();
				break;
			}
			break;

		// swimming
		case "swim":
			switch (choice) {
			default:
				game.lake();
				break;
			}
			break;

		// village entrance
		case "village":
			switch (choice) {
			case "c1":
				game.villageExplore();
				break;
			case "c2":
				game.villageBoard();
				break;
			case "c3":
				game.villageTavern();
				break;
			case "c4":
				game.crossroads();
				break;
			}
			break;

		// Exploring village
		case "villageExplore":
			switch (choice) {
			case "c1":
				game.silverDoor();
				break;
			case "c2":
				game.village();
				break;
			}
			break;

		// silver door with key
		case "silverDoorHasKey":
			switch (choice) {
			case "c1":
				game.readNote();
				break;
			case "c2":
				game.village();
				break;
			}
			break;

		// silver door no key

		case "silverDoorNoKey": {
			game.village();
			break;
		}

		// reading note
		case "readNote": {
			game.village();
			break;
		}

		// read board
		case "villageBoard":
			switch (choice) {
			case "c1":
				game.firstNotice();
				break;
			case "c2":
				game.secondNotice();
				break;
			case "c3":
				game.thirdNotice();
				break;
			case "c4":
				game.village();
				break;
			}
			break;

		// reading a notice
		case "noticeBoard": {
			game.villageBoard();
			break;
		}

		// at the tabern
		case "villageTavern":
			switch (choice) {
			case "c1":
				game.bartender();
				break;
			case "c2":
				game.loneMan();
				break;
			case "c3":
				game.village();
				break;
			}
			break;
		// bartender, not drunk
		case "bartender":
			switch (choice) {
			case "c1":
				game.drink("red");
				break;
			case "c2":
				game.drink("green");
				break;
			case "c3":
				game.drink("blue");
				break;
			case "c4":
				game.villageTavern();
				break;
			}
			break;
		// bartender, already drunk
		case "bartenderDrunk": {
			game.villageTavern();
			break;
		}

		// drinking
		case "drink":
			switch (choice) {
			case "c1":
				game.bartender();
				break;
			case "c2":
				game.villageTavern();
				break;
			}
			break;

		// talking to lone man
		case "loneMan": {
			game.villageTavern();
			break;
		}

		// forest edge
		case "forest":
			switch (choice) {
			case "c1":
				game.cabin();
				break;
			case "c2":
				game.trailStart();
				break;
			case "c3":
				game.denseWoods();
				break;
			case "c4":
				game.crossroads();
				break;

			}
			break;
		// dense woods no machete
		case "denseWoodsNoMachete": {
			game.forest();
			break;
		}
		// dense woods with machete
		case "denseWoodsMachete":
			switch (choice) {
			case "c1":
				game.clearing();
				break;
			case "c2":
				game.forest();
				break;
			}
			break;

		// in the clearing
		case "clearing": {
			game.forest();
			break;
		}

		// in the cabin
		case "cabin": {
			game.forest();
			break;
		}
		// starting the maze
		case "trailStart":
			switch (choice) {
			case "c1":
				game.forest();
				break;
			case "c3":
				game.mazeLength++;
				game.deepTrail();
				break;
			default:
				game.trailStart();
				break;
			}
			break;

		// inside the maze
		case "deepTrail": {
			if (game.mazeMap.get(game.mazeLength).equals(choice)) {
				// check if finished maze
				if (game.mazeLength == 7) {
					game.mazeLength = 1;
					game.trailEnd();
				} else {
					game.mazeLength++;
					game.deepTrail();
				}
			} else {
				game.mazeLength = 1;
				game.trailStart();

			}
		}
			break;

		// after the maze
		case "trailEnd": {
			game.forest();
			break;
		}
		}

	}
}
