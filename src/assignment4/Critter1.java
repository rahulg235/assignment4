package assignment4;
//wants to reproduce alot
public class Critter1 extends Critter {
    @Override
    public String toString(){
        return "1";
    }
    private int dir;
    private int countKids;

    public Critter1(){
        dir = getRandomInt(8);
        countKids = 0;
    }

    @Override
    public boolean fight(String oponent) {
        if(getRandomInt(10) > 4){
            Critter1 c = new Critter1();
            this.countKids++;
            reproduce(c,c.dir);
        }
        return false;
    }

    @Override
    public void doTimeStep() {
        Critter1 c = new Critter1();
        this.countKids++;
        reproduce(c,c.dir);
    }
    public static void runStats(java.util.List<Critter> c1){
        int total_kids = 0;
        for(Object obj: c1){
            Critter1 crit1 = (Critter1) obj;
            total_kids+= crit1.countKids;
        }
        System.out.println("There were "+ c1.size() + " Critter1s");
        int avg = total_kids/c1.size();
        System.out.println("On average, each Critter1 had " + avg+ " kids"); 

    }
}
