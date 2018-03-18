package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter{
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static List<Critter> alive = new java.util.ArrayList<Critter>();
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	protected int getX_coord(){
		return x_coord;
	}
	protected int getY_coord(){
		return y_coord;
	}
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
        this.energy = this.energy - Params.walk_energy_cost;
        int step = 1;
        switch (direction) {
            case 0: //increment x
                x_coord = (x_coord + 1) % (Params.world_width);
            case 1: //increment x, decrement y
                x_coord = (x_coord + 1) % (Params.world_width);
                if (y_coord == 0) {
                    y_coord = Params.world_height - 1;
                } else {
                    y_coord--;
                }
            case 2: //decrement y
                if (y_coord == 0) {
                    y_coord = Params.world_height - 1;
                } else {
                    y_coord--;
                }
            case 3: //decrement x and y
                if (y_coord == 0) {
                    y_coord = Params.world_height - 1;
                } else {
                    y_coord--;
                }
                if (x_coord == 0) {
                    x_coord = Params.world_width - 1;
                } else {
                    x_coord--;
                }
            case 4: //decrement x
                if (x_coord == 0) {
                    x_coord = Params.world_width - 1;
                } else {
                    x_coord--;
                }
            case 5: //decrement x, increment y
                if (x_coord == 0) {
                    x_coord = Params.world_width - 1;
                } else {
                    x_coord--;
                }
                y_coord = (y_coord + 1) % Params.world_height;
            case 6: //increment y
                y_coord = (y_coord + 1) % Params.world_height;
            case 7: //increment x and y
                y_coord = (y_coord + 1) % Params.world_height;
                x_coord = (x_coord + 1) % (Params.world_width);

        }
    }
	
	protected final void run(int direction) {
		energy = energy-Params.run_energy_cost;
			if(energy>=0)
			{
				walk(direction);
				walk(direction);
			}
		}


    /**
     * @TODO add new critter to collection after time step
     * @param offspring
     * @param direction
     */
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy < Params.min_reproduce_energy) {
            return;
        }
        if(this.energy%2 == 0) {
		    offspring.energy = this.energy/2;
		    this.energy = this.energy/2;
        }
        else {
		    offspring.energy = this.energy/2;
		    this.energy = this.energy/2 +1;
        }
        offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;
		offspring.walk(direction);

		//add new critter to collection after time step


	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try{
			Class newCritterClass = Class.forName(critter_class_name);
			Critter newCritter = (Critter) newCritterClass.newInstance();
			alive.add(newCritter);

			newCritter.x_coord = getRandomInt(Params.world_width);
			newCritter.y_coord = getRandomInt(Params.world_height);
			newCritter.energy = Params.start_energy;



			//Collections.sort(aliveX,Comparator.comparingInt(Critter:: getX_coord).thenComparing(Critter:: getY_coord));
			Collections.sort(alive, Comparator.comparingInt(Critter:: getY_coord). thenComparing(Critter:: getX_coord));
		}
		catch (Exception e){
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */

    public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
        List<Critter> result = new java.util.ArrayList<Critter>();
        try{
            Class newCritterClass = Class.forName(critter_class_name);
            Critter newCritter = (Critter) newCritterClass.newInstance();
            for(int i=0; i<alive.size(); i++)
            {
                if(alive.get(i).getClass().getName().equals(critter_class_name))
                    result.add(alive.get(i));
            }
        }
        catch (Exception e){
            throw new InvalidCritterException(critter_class_name);
        }
        return result;
    }
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */

    public static void clearWorld() {
        // Complete this method.
        for(int i=0; i<population.size(); i++)
        {
            population.remove(i);
        }
        for(int j=0; j<alive.size(); j++)
        {
            alive.remove(j);
        }
        for(int k=0; k<babies.size(); k++)
        {
            babies.remove(k);
        }
    }

	/**
	 * @TODO dealing with critters in same position, incrementing time steps
	 */
	public static void worldTimeStep() {
		Collections.sort(alive, Comparator.comparingInt(Critter::getY_coord).thenComparing(Critter::getX_coord));
		for (int i = 0; i < alive.size(); i++) {
			alive.get(i).doTimeStep();

		}
		for (int i = 0; i < alive.size(); i++) {
			for (int j = i + 1; j < alive.size(); j++) {
				//checking same position and if alive
				if (alive.get(i).x_coord == alive.get(j).x_coord && alive.get(i).y_coord == alive.get(j).y_coord && alive.get(i).energy > 0 && alive.get(j).energy > 0) {

					Critter p1 = alive.get(i);
					Critter p2 = alive.get(j);
					if (p1.fight(p2.toString())) { //A wants to fight
						if (p2.fight(p1.toString())) { //B wants to fight
							if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
								int num1 = p1.getRandomInt(p1.energy);
								int num2 = p2.getRandomInt(p2.energy);
								if (num1 >= num2) { //p1 wins
									p1.energy += (p2.energy / 2);
									p2.energy = 0; //will be removed later
								}
								if (num1 < num2) {
									//p2 wins
									p2.energy += (p1.energy / 2);
									p1.energy = 0; //will be removed later
								}


							}

						} else { //A wants to fight but B doesn't
							if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
								p1.energy += p2.energy / 2;
								p2.energy = 0;
							}

						}

					} else {
						//A doesn't want to fight but B does
						if (p2.fight(p1.toString())) {
							if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
								p2.energy += p1.energy / 2;
								p1.energy = 0;
							}


						}
					}


				}
			}


			// Complete this method.
		}
		//removing dead critters from collection
		for(Critter c : alive) {
			if(c.energy <= 0) {
				alive.remove(c);
			}
		}

	}
	
	public static void displayWorld() {
		Collections.sort(alive, Comparator.comparingInt(Critter:: getY_coord). thenComparing(Critter:: getX_coord));

			// Complete this method.
			//First Row
			System.out.print("+");                                                    //Top left corner
			for(int i=0; i<Params.world_width; i++)
			{
				System.out.print("-");                                                // n dashes (width)
			}
			System.out.println("+");                                              //Top right corner
			//Middle Rows
			int aliveIndex=0;                                                       //iterates through alive array
			for(int vertical=0; vertical<Params.world_height; vertical++)            //for m rows (height)
			{
				System.out.print("|");                                                //Left hand wall
            /*
            while(alive.get(aliveIndex).y_coord<vertical)                        //Unnecessary
            {
                aliveIndex++;
            }
            */
				if(alive.get(aliveIndex).y_coord==vertical)                                                                 //If the Critter in alive belongs in that row
				{
					for(int horizontal = 0; horizontal < Params.world_width; horizontal++)                                       //For every column in that row
					{
						if(alive.get(aliveIndex).y_coord==vertical && alive.get(aliveIndex).x_coord==horizontal)                    //If the Critter in alive belongs in that column & row
						{
							System.out.print(alive.get(aliveIndex));                                                                //Print it
							aliveIndex++;                                                                                           //Next Critter
							while(alive.get(aliveIndex).x_coord==horizontal && alive.get(aliveIndex).y_coord==vertical)             //For every other critter in alive that has the same coordinates
							{
								aliveIndex++;                                                                                       //Skip them
							}
						}
						else                                                                                                //Else just print spaces for that row
						{
							System.out.print(" ");
						}
					}
					System.out.println("|");
				}
				else
				{
					for(int horizontal = 0; horizontal < Params.world_width; horizontal++)
					{
						System.out.print(" ");
					}
					System.out.println("|");
				}
			}
			//Last Row
			System.out.print("+");
			for(int i=0; i<Params.world_width; i++)
			{
				System.out.print("-");
			}
			System.out.println("+");
		}
	}
		// Complete this method.





