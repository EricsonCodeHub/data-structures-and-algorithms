public class Towers {
    private Peg[] pegs;

    // Constructor
    public Towers(int numRings) {
        pegs = new Peg[3];
        pegs[0] = new Peg(numRings);
        pegs[1] = new Peg(0);
        pegs[2] = new Peg(0);
    }

    // Method to count rings on a specific peg
    public int countRings(int pegNumber) {
        return pegs[pegNumber - 1].getNumRings();
    }

    // Method to get the diameter of the top ring on a specific peg
    public int getTopDiameter(int pegNumber) {
        return pegs[pegNumber - 1].getTopDiameter();
    }

    // Method to move a ring from startPeg to endPeg
    public void move(int startPeg, int endPeg) {
        pegs[endPeg - 1].addRing(pegs[startPeg - 1].getTopDiameter());
        pegs[startPeg - 1].removeTopRing();
    }

    // Method to display the towers
    public void displayTowers() {
        for (int i = pegs[0].getNumRings(); i > 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (pegs[j].getNumRings() >= i) {
                    System.out.print(" " + pegs[j].getTopDiameter());
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println("------");
    }
}
