package notbattleship;

import java.util.HashMap;

public class Coordinates {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates() {
		x = 0;
		y = 0;
	}
	
	public String toString() {
		HashMap<Integer, String> numDict = new HashMap<Integer, String>();
		numDict.put(0, "A");
		numDict.put(1, "B");
		numDict.put(2, "C");
		numDict.put(3, "D");
		numDict.put(4, "E");
		numDict.put(5, "F");
		numDict.put(6, "G");
		numDict.put(7, "H");
		numDict.put(8, "I");
		numDict.put(9, "J");
		numDict.put(10, "K");
		numDict.put(11, "L");
		numDict.put(12, "M");
		numDict.put(13, "N");
		numDict.put(14, "O");
		numDict.put(15, "P");
		numDict.put(16, "Q");
		numDict.put(17, "R");
		numDict.put(18, "S");
		numDict.put(19, "T");
		numDict.put(20, "U");
		numDict.put(21, "V");
		numDict.put(22, "W");
		numDict.put(23, "X");
		numDict.put(24, "Y");
		numDict.put(25, "Z");
		
		return numDict.get(y) + Integer.toString(x);
	}
	
}
