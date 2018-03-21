package assignment4;
//can only move up (direction 0)
public class Critter2 extends Critter {
    @Override
    public String toString() {
        return "2";
    }

    private int dir;
    private int numSteps;


    public Critter2() {
        dir = 0;
        numSteps = 0;

    }

    @Override
    public boolean fight(String oponent) {
        numSteps++;
        walk(dir);
        return false;
    }

    @Override
    public void doTimeStep() {
        numSteps += 2;
        run(dir);

    }


    public static void runStats(java.util.List<Critter> c1) {
        int total_steps = 0;
        int count = 0;
        for (Object obj : c1) {
            Critter2 crit1 = (Critter2) obj;
            if(crit1.numSteps > 10){
                count++;
            }

        }
        System.out.println("There were " + c1.size() + " Critter2s");
        System.out.println("There were " + count + " Critter2 that moved more than 10 times");


    }
}


