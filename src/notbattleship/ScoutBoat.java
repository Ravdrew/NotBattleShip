package notbattleship;

public abstract class ScoutBoat extends Boat{
	
	public ScoutBoat(int team, Coordinates location, int direction, int health, int vision) {
		super(team, location, direction, health, 1, vision);
	}
	
	public String takeHit(int attacks) {
		int damageTaken = 0;
		for(int i = 0; i < attacks; i++) {
			if(Math.random() < 0.25) {
				damageTaken += 1;
			}
		}
		
		if(damageTaken == 0) {
			return this.getID() + "has avoided the attack";
		}
		else {
			return super.takeHit(damageTaken);
		}
	}
}
