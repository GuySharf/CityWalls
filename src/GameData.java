import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {

	private static final long serialVersionUID = 1L;
	List<Boolean> booleans = new ArrayList<>();
	String location, weapon;
	int hp, zombieHp;
	Integer mazeLength;

	public GameData(Game game) {
		this.weapon = game.weapon;
		this.location = game.location;
		this.hp = game.hp;
		this.zombieHp = game.zombieHp;
		this.mazeLength = game.mazeLength;
		booleans.add(game.hasKey);
		booleans.add(game.hasRing);
		booleans.add(game.hasNote);
		booleans.add(game.isDrunk);
		booleans.add(game.hasMachete);
		booleans.add(game.metFairy);
	}

	public void save() {
		FileOutputStream fos;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("data.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (IOException ioe) {
		} finally {
			if (oos != null)
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public static GameData load() {
		GameData loadData = null;
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("data.txt");
			ois = new ObjectInputStream(fis);
			loadData = (GameData) ois.readObject();
		} catch (FileNotFoundException fnfe) {
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return loadData;
	}
}
