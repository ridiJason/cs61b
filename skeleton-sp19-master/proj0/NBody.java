public class NBody{

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double Radius = in.readDouble();

        return Radius;
    }

    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        in.readDouble();
        Body[] Bodies = new Body[num];
        for(int i = 0; i < num; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Bodies[i] = new Body(xP,yP,xV,yV,m,img);
        }
        return Bodies;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] Bodies = NBody.readBodies(filename);


        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for(Body b : Bodies){
            b.draw();
        }

        StdDraw.enableDoubleBuffering();

        for(double t = 0; t < T; t = t + dt){
            double[] xForces = new double[Bodies.length];
            double[] yForces = new double[Bodies.length];
            for(int i = 0; i < Bodies.length; i++){
                xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);
                yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);
            }
            for(int i = 0; i < Bodies.length; i++){
                Bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(Body b : Bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i <Bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                    Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);
        }





    }




}