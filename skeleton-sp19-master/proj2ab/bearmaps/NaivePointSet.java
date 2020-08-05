package bearmaps;

import java.util.List;

import static java.lang.Math.pow;

public class NaivePointSet implements PointSet{


    private List<Point> list;

    public NaivePointSet(List<Point> points){
        this.list = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point p = new Point(x,y);
        Point nearestP = list.get(0);
        double nearestD = Point.distance(p,nearestP);
        for (int i = 1; i < list.size();i++){
            double dis = Point.distance(p,list.get(i));
            if (dis < nearestD){
                nearestD = dis;
                nearestP = list.get(i);
            }
        }
        return nearestP;


    }


    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret);
    }

}


