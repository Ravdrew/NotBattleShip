package notbattleship;

public class Battleship extends Boat implements Attacker{
    public Battleship(int teamID, Coordinates location, int dir) {
        super(teamID, location, dir, 4, 3, 1);
    }
    
    public String getID() {
        return "B" + super.getTeam();
    }
    
    public String getActions() {
        return "Choose any of the following actions for the Battleship: " + "\n" +
                "1. Move" + "\n" + "2. Turn left" + "\n" + "3. Turn right" + "\n" +
                "4. Attack" + "\n";
    }
    
    public String act(int[] choices, World world) {
        String result = "";
        choices[1] = 0;
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == 1) result += move(world) + "\n";
            if (choices[i] == 2)result += turn(-1) + "\n";
            if (choices[i] == 3) result += turn(1) + "\n";
            if (choices[i] == 4) result += attack(world) + "\n";
        }
        return result;
    }
    
    public String attack(World world) {
        Coordinates oneUp = world.getAdjacentLocation(this.getLocation(), this.getDirectioNum());
        if(world.isLocationValid(oneUp)) {
            if(world.getOccupant(oneUp) != null){
                if(world.getOccupant(oneUp).getTeam() == this.getTeam()) return "Friendly Boat Ahead of " + this.getID();
                    Boat hitted = world.getOccupant(oneUp);
                    return "Fire cannons! " + hitted.takeHit(this.getStrength()) + hitted.takeHit(this.getStrength());
            }
        }
        return "There are no boats in range currently.";
    }
}
