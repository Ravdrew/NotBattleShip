package notbattleship;

public class Destroyer extends Boat implements Attacker{
	public Destroyer(int teamID, Coordinates location, int dir) {
		super(teamID, location, dir, 2, 2 , 1);
	}
	
	public String getID() {
		return "D" + super.getTeam();
	}
	
	public String getActions() {
		return "Choose any of the following actions for the Destroyer:\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Attack";
	}
	
	public String act(int[] choices, World world) {
		String result = "";
		
		for(int i = 0; i < choices.length; i ++) {
			if (choices[i] == 1) result += move(world) + "\n";
            if (choices[i] == 2)result += turn(-1) + "\n";
            if (choices[i] == 3) result += turn(1) + "\n";
            if (choices[i] == 4) result += attack(world) + "\n";
		}
		
		return result;
	}
	
	public String takeHit(int strength) {
		int random = (int) (Math.random()*2);
		
		if(random == 0) {
			return super.takeHit(strength);
		}
		else return this.getID() + " avoids the attack.";
	}
	
	public String attack(World world) {
        Coordinates oneUp = world.getAdjacentLocation(this.getLocation(), this.getDirectioNum());
        if(world.isLocationValid(oneUp)) {
            if(world.getOccupant(oneUp) != null){
                if(world.getOccupant(oneUp).getTeam() == this.getTeam()) return "Friendly Boat Ahead of " + this.getID();
                    Boat hitted = world.getOccupant(oneUp);
                    return "Fire cannons! " + hitted.takeHit(this.getStrength());
            }
        }
        return this.getID() + " has no boats in range currently.";
	}
}
