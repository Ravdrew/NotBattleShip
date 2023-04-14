package notbattleship;

import java.util.HashMap;

public abstract class Boat {
	private int team;
	private Coordinates location;
	private int direction;
	private int health;
	private int strength;
	private int vision;
	
	public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {
		this.team = team;
		this.location = location;
		this.direction = direction;
		this.health = health;
		this.strength = strength;
		this.vision = vision;
	}
	
	public abstract String act(int[] choices, World world);
	public abstract String getActions();
	public abstract String getID();

	public String move(World world) {
		Coordinates wantMove = world.getAdjacentLocation(this.location, this.direction);
		Coordinates prev = this.getLocation();
		//System.out.println(wantMove);
		if(world.isLocationValid(wantMove) == false) {
			return this.getID() + " cannot move off the map.";
		}
		else {
			if(world.isLocationOccupied(wantMove) == true) {
				return this.getID() + " cannot move to " + wantMove.toString() + " as it is occupied.";
			}
			else {
				World.setNull(this.getLocation());
				this.setLocation(wantMove);
				world.setOccupant(this, wantMove);
				return this.getID() + " moves from " + prev.toString() + " to " + wantMove.toString() + ".";
			}
		}
	}
	
	public String turn(int dirChange) { //-1 = left, 1 = right
		HashMap<Integer, String> dirDict = new HashMap<Integer, String>();
		dirDict.put(0, "N");
		dirDict.put(1, "NE");
		dirDict.put(2, "E");
		dirDict.put(3, "SE");
		dirDict.put(4, "S");
		dirDict.put(5, "SW");
		dirDict.put(6, "W");
		dirDict.put(7, "NW");
		
		if(dirChange == -1) {
			direction -= 1;
			if(direction < 0) direction = 7;
			return this.getID() + " turned left, now facing " + dirDict.get(this.getDirection());
		}
		else {
			direction += 1;
			if(direction > 7) direction = 0;
			return this.getID() + " turned right, now facing " + dirDict.get(this.getDirection());
		}
	}
	
	public String takeHit(int atk) {
		health -= atk;
		if(health <= 0) {
			health = 0;
			World.setNull(this.getLocation());
			return this.getID() + " has been sunk!";
		}
		else {
			return this.getID() + " takes " + atk + " damage.";
		}
	}
	
	public void setLocation(Coordinates cord) {
		this.location = cord;
	}
	
	public String toString() {
		return this.getID();
	}
	
	public int getTeam() {
		return team;
	}

	public Coordinates getLocation() {
		return location;
	}

	public String getDirection() {
		HashMap<Integer, String> dirDict = new HashMap<Integer, String>();
		dirDict.put(0, "^");
		dirDict.put(1, "/");
		dirDict.put(2, ">");
		dirDict.put(3, "\\");
		dirDict.put(4, "v");
		dirDict.put(5, ",");
		dirDict.put(6, "<");
		dirDict.put(7, "`");
		
		return dirDict.get(direction);
	}

	public int getDirectioNum(){
		return direction;
	}
	
	public int getHealth() {
		return health;
	}

	public int getStrength() {
		return strength;
	}

	public int getVision() {
		return vision;
	}
	
}
