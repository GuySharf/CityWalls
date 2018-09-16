import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveLoadHandler implements ActionListener {
	GameData loadData;
	Game game;

	public SaveLoadHandler(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = e.getActionCommand();
		if (choice.equals("save")) {
			GameData saveData = new GameData(game);
			saveData.save();
			game.plot.setText(game.plot.getText() + "\n\n                    Game Saved");
		} else {
			loadData = GameData.load();
			loadGame();
			
		}
	}

	private void loadGame() {
		game.weapon = loadData.weapon;
		game.hp = loadData.hp;
		game.zombieHp = loadData.zombieHp;
		game.location = loadData.location;
		game.mazeLength = loadData.mazeLength;
		game.hpInt.setText("" + game.hp);
		game.weaponString.setText(game.weapon);
		game.hasKey = loadData.booleans.get(0);
		game.hasRing = loadData.booleans.get(1);
		game.hasNote = loadData.booleans.get(2);
		game.isDrunk = loadData.booleans.get(3);
		game.hasMachete = loadData.booleans.get(4);
		game.metFairy = loadData.booleans.get(5);
		game.plot.setText("Game Loaded Successfully\n"
				+ "Returning To City Walls");
		game.cityWalls();
	}

}
