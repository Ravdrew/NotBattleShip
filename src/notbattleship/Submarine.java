package notbattleship;

public class Submarine extends ScoutBoat implements Attacker{
	private int numOfTorpedos;
	
	public Submarine(int teamID, Coordinates cord, int direction, int nOT) {
		super(teamID, cord, direction, 3, 2);
		
		numOfTorpedos = nOT;
	}

	public String submerge(World world) {
		Coordinates oldCord = this.getLocation();
		Coordinates newCord = new Coordinates((int) (Math.random() * world.getWidth()), (int) (Math.random() * world.getHeight()));
		int xDiff = Math.abs(this.getLocation().getX() - newCord.getX());
		int yDiff = Math.abs(this.getLocation().getY() - newCord.getY());
		
		while(xDiff < 2 || yDiff < 2 || world.isLocationOccupied(newCord) == true) {
			newCord = new Coordinates((int) (Math.random() * world.getWidth()), (int) (Math.random() * world.getHeight()));
			xDiff = Math.abs(this.getLocation().getX() - newCord.getX());
			yDiff = Math.abs(this.getLocation().getY() - newCord.getY());
		}
		
		world.setNull(this.getLocation());
		this.setLocation(newCord);
		world.setOccupant(this, newCord);
		return this.getID() + " moves from " + oldCord.toString() + " to " + newCord.toString() + ".";
	}
	
	@Override
	public String attack(World world) {
		// TODO Auto-generated method stub
		if(numOfTorpedos > 0) {
			switch(this.getDirection()) {
			case "N":
				Coordinates oneUp = world.getAdjacentLocation(this.getLocation(), 1);
				if(world.isLocationValid(oneUp)) {
					if(world.getOccupant(oneUp) != null){
						Boat hitted = world.getOccupant(oneUp);
						return "Fire torpedoes! " + hitted.takeHit(1 + (int) (Math.random() * hitted.getHealth()));
					}
				}
				Coordinates twoUp = world.getAdjacentLocation(oneUp, 1);
				if(world.isLocationValid(twoUp)) {
					if(world.getOccupant(world.getAdjacentLocation(oneUp, 1)) != null){
						Boat hitted = world.getOccupant(twoUp);
						return "Fire torpedoes! " + hitted.takeHit(1 + (int) (Math.random() * hitted.getHealth()));
					}
				}
				break;
			case "NE":
				
				break;
			case "E":
				
				break;
			case "SE":
				
				break;
			case "S":
				
				break;
			case "SW":
				
				break;
			case "W":
				
				break;
			case "NW":
				
				break;
			default:
				
				break;
			}
		}
		else return this.getID() + " has no torpedos remaining.";
	}

	@Override
	public String act(int[] choices, World world) {
		// TODO Auto-generated method stub
		String result = "";
		for(int i = 0; i<choices.length; i++) {
			if(choices[i] == 1) result += move(world) + "\n";
			else if(choices[i] == 2) result += turn(-1) + "\n";
			else if(choices[i] == 3) result += turn(1) + "\n";
			else if(choices[i] == 4) result += 
		}
		return result;
	}

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return "Choose any of the following actions for the Cruiser:\r\n"
			+ " 1. Move\r\n"
			+ " 2. Turn left\r\n"
			+ " 3. Turn right\r\n"
			+ " 4. Submerge\r\n"
			+ " 5. Fire torpedoes\r\n";
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "S" + this.getTeam();
	}
	
}
