public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel; 
	double mass;
	String imgFileName; 

	/* frist constuctor
	create a planet object from input values
	*/
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
	xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV ; 
	mass  = m;
	imgFileName = new String(img);

	}
	/* Second constuctor
	copy all the properties of the input planet
	*/
	public Planet(Planet p){
	xxPos = p.xxPos;
	yyPos = p.yyPos;
	xxVel = p.xxVel;
	yyVel = p.yyVel ;
	mass  = p.mass;
	imgFileName = new String(p.imgFileName);
	}

/* calculates the distance between this and 
* and the inputed Planets.
*/
	public double calcDistance (Planet pIn){
		double dis;
		double dx = xxPos - pIn.xxPos;
		double dy = yyPos - pIn.yyPos;
		dis = Math.sqrt(dx*dx + dy*dy);
		return dis;
	}

	public double calcForceExertedBy (Planet pIn){
		double g= 6.67e-11;
		double r = this.calcDistance(pIn);
		double f = g* mass* pIn.mass /(r*r);
		return f;
	}

	public double calcForceExertedByX (Planet pIn){
		double f = calcForceExertedBy(pIn);
		double r = this.calcDistance(pIn);
		double dx = pIn.xxPos - xxPos;
		double fx= f*dx/r;
		return fx;

	}

	public double calcForceExertedByY (Planet pIn){
		double f = calcForceExertedBy(pIn);
		double dy = pIn.yyPos - yyPos;
		double r = this.calcDistance(pIn);
		double fy= f*dy/r;
		return fy; 
	}

	public double calcNetForceExertedByX (Planet[] psIn ){
		double totfx=0;
		for (Planet p: psIn){
			if (!this.equals(p)){
				totfx= totfx + calcForceExertedByX(p);  
			}

		}
		return totfx;
	}
	public double calcNetForceExertedByY  (Planet[] psIn ){
		double totfy=0;
		for (Planet p: psIn){
			if (!this.equals(p)){
				totfy= totfy + calcForceExertedByY(p);  
			}

		}
		return totfy;

	}
/*
1 -Calculate the acceleration using the provided x and y 
forces.

2- Calculate the new velocity by using the acceleration 
and current velocity. Recall that accleration describes 
the change in velocity per unit time, so the new velocity
is (vx + dt * ax, vy + dt * ay).

3 -Calculate the new position by using the velocity 
computed in step 2 and the current position. The new 
position is (px + dt * vx, py + dt * vy).
*/

	public void update(double dt,double fX,double fY){
		double ax = fX/mass;
		double ay = fY/mass; 
		xxVel= xxVel + ax * dt;
		yyVel= yyVel + ay * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;  
	}

	public void draw (){
	String myimg = "images/"+imgFileName;
		StdDraw.picture(xxPos, yyPos, myimg);
	}

	

}