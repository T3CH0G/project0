public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
				double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;

		/**@stackoverflow */
		double r = Math.sqrt(dx*dx + dy*dy);
		return r;

	}

	public double calcForceExertedBy(Planet p){
		double dist = this.calcDistance(p);
		double force = Planet.G * this.mass * p.mass / (dist * dist);

		return force;
	}

	public double calcNetForceExertedByX(Planet[] pAll){
		double sum = 0;
		for(int i = 0; i < pAll.length; i++){
			if (pAll[i].equals(this)){
				continue;
			}
			double side = pAll[i].xxPos - this.xxPos;
			double dist = this.calcDistance(pAll[i]);
			double force = this.calcForceExertedBy(pAll[i]);
			sum += force * side / dist;
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] pAll){
		double sum = 0;
		for(int i = 0; i < pAll.length; i++){
			if (pAll[i].equals(this)){
				continue;
			}
			double side = pAll[i].yyPos - this.yyPos;
			double dist = this.calcDistance(pAll[i]);
			double force = this.calcForceExertedBy(pAll[i]);
			sum += force * side / dist;
		}
		return sum;
	}

	public void update(double secs, double Fx, double Fy){
		double accelX = Fx/this.mass;
		double accelY = Fy/this.mass;

		this.xxVel = this.xxVel + secs * accelX;
		this.yyVel = this.yyVel + secs * accelY;

		this.xxPos = this.xxPos + secs * xxVel;
		this.yyPos = this.yyPos + secs * yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"./images/"+this.imgFileName);
	}
















}







