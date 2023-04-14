package notbattleship;

public class Cruiser extends ScoutBoat{
	
	public Cruiser(int teamID, Coordinates cord, int direction) {
		super(teamID, cord, direction, 3, 3);
	}

	@Override
	public String act(int[] choices, World world) {
		String result = "";
		for(int i = 0; i<choices.length; i++) {
			if(choices[i] == 1) result += move(world) + "\n";
			else if(choices[i] == 2) result += turn(-1) + "\n";
			else if(choices[i] == 3) result += turn(1) + "\n";
		}
		return result;
	}

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return "Choose any of the following actions for the Cruiser:\r\n"
				+ " 1. Move\r\n"
				+ " 2. Turn left\r\n"
				+ " 3. Turn right\r\n";
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "C" + this.getTeam();
	}
}
