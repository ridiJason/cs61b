
public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;



    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.hypot(dx, dy);
        return r;

    }

    public double calcForceExertedBy(Body b){
        double F = G * b.mass * this.mass / Math.pow(this.calcDistance(b),2);
        return F;
    }

    public double calcForceExertedByX(Body b){
        double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;

    }

    public double calcForceExertedByY(Body b){
        double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] bb){
        double FNx = 0;
        for(Body b : bb){
            if(!this.equals(b)){
                FNx = FNx + this.calcForceExertedByX(b);
            }
        }
        return FNx;
    }

    public double calcNetForceExertedByY(Body[] bb){
        double FNy = 0;
        for(Body b : bb){
            if(!this.equals(b)){
                FNy = FNy + this.calcForceExertedByY(b);
            }
        }
        return FNy;
    }

    public void update(double dt, double fx, double fy){
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }




}