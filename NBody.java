public class NBody{


	public static double readRadius(String in){

		In inhandle = new In(in);
		int count =0;
		while(!inhandle.isEmpty()){
			count ++;
			double dub= inhandle.readDouble();
			if (count==2){
				return dub;
			}
		}
		return 0;
	}

	public static Planet[] readPlanets(String in){
		In inhandle = new In(in);
		int count =0;
		int numplanets= inhandle.readInt()+ 2;
		double R= inhandle.readDouble();
		Planet[] ps= new Planet[numplanets-2];

		while(!inhandle.isEmpty() && numplanets>1){
			count ++;
			numplanets= numplanets-1;
			// starting the third line read and create the planets...
			if (count>=2){
			double xxpos=inhandle.readDouble();
			//System.out.println(xxpos);
			double yypos=inhandle.readDouble();
			//System.out.println(yypos);
			double xxvel=inhandle.readDouble();
			//System.out.println(xxvel);
			double yyvel=inhandle.readDouble();
			//System.out.println(yyvel);
			double mass=inhandle.readDouble();
			//System.out.println(mass);
			String name=inhandle.readString();
			//System.out.println(name);

			Planet p = new Planet(xxpos,yypos,xxvel,yyvel,mass,name);
			ps[numplanets-1] =p;
			}
		//System.out.println("p counts is "+ numplanets);
		}
		return ps;
	}

	public static void drawPlanets(Planet[] planets){
		// draw all the planets
		for (Planet p : planets ){
			p.draw();
		}
	}

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Please supply proper input");
			System.exit(0);
		}


		double T =  Double.parseDouble(args[0]);
		double dt =  Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius (filename);
		Planet[] planets = readPlanets (filename);

		/** Sets up the universe so it goes from 
		  * -100, -100 up to 100, 100 */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear(); 
		String universe = "images/starfield.jpg";
		StdDraw.picture(0, 0, universe);

		// draw all the plantes
		drawPlanets(planets);

	double time= 0; 
	int numPlanets = planets.length;
	while (time<T){

		double[] xForces = new double[numPlanets];
		double[] yForces = new double[numPlanets];

		int count= 0;
		for (Planet p : planets ){
			// calculate the components of the resultant force
		xForces[count] = p.calcNetForceExertedByX(planets);
		yForces[count] = p.calcNetForceExertedByY(planets);
		// update the position of the planets
		p.update(dt,xForces[count] ,yForces[count]);
			count++;
		}
		// draw background
		StdDraw.picture(0, 0, universe);

		// move the planets to their new possition
		drawPlanets(planets);
		//Pause the animation for 10 milliseconds 
		

		time = time + dt;
	}
	


	}

}