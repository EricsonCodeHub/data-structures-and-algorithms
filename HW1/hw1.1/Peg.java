public class Peg {
    private int[] rings;
    private int numRings;

    // Constructor
    public Peg(int n) {
        rings = new int[n];
        numRings = n;
        for (int i = 0; i < n; i++) {
            rings[i] = n - i;
        }
    }

    // Accessor method to get the number of rings
    public int getNumRings() {
        return numRings;
    }

    // Accessor method to get the diameter of the topmost ring
    public int getTopDiameter() {
        if (numRings > 0) {
            return rings[numRings - 1];
        }
        return 0; // No rings on the peg
    }

    // Method to add a new ring to the top
    public void addRing(int diameter) {
        if (numRings < rings.length && (numRings == 0 || diameter < rings[numRings - 1])) {
            rings[numRings] = diameter;
            numRings++;
        } else {
            System.out.println("Invalid operation: Cannot add ring with diameter " + diameter);
        }
    }

    // Method to remove the topmost ring
    public void removeTopRing() {
        if (numRings > 0) {
            numRings--;
        } else {
            System.out.println("Invalid operation: No rings to remove");
        }
    }
}
