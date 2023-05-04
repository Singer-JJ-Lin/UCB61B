public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        int numberOfPlanets = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[numberOfPlanets];
        for(int i = 0; i < numberOfPlanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in. readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
//        double T = Double.parseDouble(args[0]);
//        double dt = Double.parseDouble(args[1]);
//        String filename = args[2];
//
//        double radius = readRadius(filename);
//        Planet[] planets = readPlanets(filename);
//
//        StdDraw.setXscale(-radius, radius);
//        StdDraw.setYscale(-radius, radius);
//        StdDraw.enableDoubleBuffering();
//        StdDraw.picture(0, 0, "images/starfield.jpg");
//        for(Planet planet : planets) {
//            planet.draw();
//        }
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // set the universe scale
        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;
        while(t <= T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i = 0; i < num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // draw the backgroud picture
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // draw all the planets
            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.getMass(), planet.getImgFileName());
        }

    }
}
