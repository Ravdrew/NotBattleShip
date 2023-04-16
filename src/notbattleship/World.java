package notbattleship;

public class World {
	private static Boat[][] map;
	public static final int NORTH = 0;
	public static final int NORTHEAST = 1;
	public static final int EAST = 2;
	public static final int SOUTHEAST = 3;
	public static final int SOUTH = 4;
	public static final int SOUTHWEST = 5;
	public static final int WEST = 6;
	public static final int NORTHWEST = 7;
	
	public World(int width, int height) {
		if(width > 10) width = 10;
		else if(width < 4) width = 4;
		
		if(height > 10) height = 10;
		else if(height < 4) height = 4;
		
		map = new Boat[height][width];
	}
	
	public int getWidth() {
		return map[0].length;
	}
	
	public int getHeight() {
		return map.length;
	}
	
	public Boat getOccupant(Coordinates cord) {
		return map[cord.getY()][cord.getX()];
	}
	
	public boolean isLocationValid(Coordinates cord) {
		if(cord == null) return false;
		if(cord.getX() > getWidth() - 1 || cord.getX() < 0) return false;
		if(cord.getY() > getHeight() - 1 || cord.getY() < 0) return false;
		return true;
	}
	
	public boolean isLocationOccupied(Coordinates cord) {
		return map[cord.getY()][cord.getX()] != null;
	}
	
	public static void setNull(Coordinates cord) {
		map[cord.getY()][cord.getX()] = null;
	}
	
	public boolean setOccupant(Boat b, Coordinates cord) {
		if(isLocationOccupied(cord)) {
			return false;
		}
		else {
			map[cord.getY()][cord.getX()] = b;
			return true;
		}
	}
	
	public Coordinates getAdjacentLocation(Coordinates cord, int direction) {
		if(isLocationValid(cord) == false) return null;
		Coordinates newCord;
		switch(direction) {
			case World.NORTH:
				newCord = new Coordinates(cord.getX(), cord.getY() - 1);
				break;
			case World.NORTHEAST:
				newCord = new Coordinates(cord.getX() + 1, cord.getY() - 1);
				break;
			case World.EAST:
				newCord = new Coordinates(cord.getX() + 1, cord.getY());
				break;
			case World.SOUTHEAST:
				newCord = new Coordinates(cord.getX() + 1, cord.getY() + 1);
				break;
			case World.SOUTH:
				newCord = new Coordinates(cord.getX(), cord.getY() + 1);
				break;
			case World.SOUTHWEST:
				newCord = new Coordinates(cord.getX() - 1, cord.getY() + 1);
				break;
			case World.WEST:
				newCord = new Coordinates(cord.getX() - 1, cord.getY());
				break;
			case World.NORTHWEST:
				newCord = new Coordinates(cord.getX() - 1, cord.getY() - 1);
				break;
			default:
				System.out.println("code broke");
				newCord = null;
				break;
		}
		return newCord;
	}
	
	public String drawTeamMap(Boat[] boats, int view) {
		String output = "@";
		/*for(int i = 0; i<this.getWidth(); i++) {
			output += "   " + (char) (coff + i);	
		}*/
		for(int i = 1; i<=this.getWidth(); i++) {
			output += "  " + i;	
		}
	
		if(view == 1) {
			int coff = 65;
			for(int r = 0; r<this.getHeight(); r++) {
				output += "\n" + (char) (coff + r) + " ";
				
				for(int w = 0; w<this.getWidth(); w++) {
					output+="###";
				}
			}
			output += "\n";
		}
		
		else if(view > 1) {
			int coff = 65;
			for(int r = 0; r<this.getHeight(); r++) {
				output += "\n" + (char) (coff + r) + " ";
				
				for(int c = 0; c<this.getWidth(); c++) {
					String app = "###";
					for(Boat b:boats) {
						Coordinates cord = new Coordinates(c, r);
						if(b.getLocation().getX() == cord.getX() && b.getLocation().getY() == cord.getY()) {
							if(view == 2) app = b.getDirection() + b.toString();
							if(view == 3) app = b.getHealth() + b.toString();
							break;
						}
						if(Math.abs(r - b.getLocation().getY()) <= b.getVision() && Math.abs(c - b.getLocation().getX()) <= b.getVision()){
							if(this.isLocationOccupied(cord)) {
								if(view == 2) app = map[r][c].getDirection() + map[r][c].toString();
								if(view == 3) app = map[r][c].getHealth() + map[r][c].toString();
								break;
							}
							else {
								app = "~~~";
							}
						}
					}
					
					output += app;
				}
			}
		}
		output += "\n";
		return output;
	}
	
}
