public class NBody {
	public static double radius;


	public static double readRadius(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		radius = in.readDouble();
		Planet[] pList = new Planet[N];

		for(int i = 0; i < N; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();

			Planet p = new Planet(xP, yP, xV, yV, m, img);
			pList[i] = p;
		}
		return pList;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] pList = readPlanets(filename);

		StdDraw.setScale(-NBody.radius,NBody.radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"./images/starfield.jpg");

		for(int i = 0; i < pList.length; i++){
			pList[i].draw();
		}
		StdDraw.enableDoubleBuffering();

		StdDraw.show();
		double time = 0;
		while(time <= T){
			double[] xForces = new double[pList.length];
			double[] yForces = new double[pList.length];

			for(int i = 0; i < pList.length;i++){
				xForces[i] = pList[i].calcNetForceExertedByX(pList);
				yForces[i] = pList[i].calcNetForceExertedByY(pList);
			}
			for(int i = 0; i < pList.length;i++){
				pList[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0,0,"./images/starfield.jpg");
			
			for(int i = 0; i < pList.length; i++){
				pList[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;

		}


		StdOut.printf("%d\n", pList.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < pList.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  pList[i].xxPos, pList[i].yyPos, pList[i].xxVel,
		                  pList[i].yyVel, pList[i].mass, pList[i].imgFileName);   
		}
	}
















}