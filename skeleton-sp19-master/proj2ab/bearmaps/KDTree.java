package bearmaps;

import java.util.List;

public class KDTree{

    private final static boolean FLAG = false;
    private List<Point> points;
    private Node root;

    private class Node{
        private Point point;
        private boolean flag; //if flag == 0, compare the X; if flag == 1, compare the Y
        private Node leftchild;
        private Node rightchild;

        public Node(Point point, Node leftchild, Node rightchild, boolean flag){
            this.point = point;
            this.leftchild = leftchild;
            this.rightchild = rightchild;
            this.flag = flag;
        }

        public Point getPoint() {
            return point;
        }

        public boolean getFlag(){
            return flag;
        }
    }

    public KDTree(List<Point> points){
        this.points = points;
    }

    public Node insert(Point point, Node node, boolean flag){
        if (node == null){
            return new Node(point, null, null,flag);
        }

        if (point == node.getPoint()){
            return node;
        }

        int cmp = comparePoint(point, node.getPoint(),flag);

        if (cmp > 0){
            node.rightchild = insert(point, node, !flag);
        }else{
            node.leftchild = insert(point, node, !flag);
        }

        return node;
    }

    public int comparePoint(Point p1, Point p2, boolean flag) {
        if (flag == false) {
            if (p1.getX() >= p2.getX()) {
                return 1;
            } else {
                return -1;
            }
        }else {
            if (p1.getY() >= p2.getY()){
                return 1;
            }else {
                return -1;
            }
        }
    }



    public Point nearest(double x,double y){
        Node n = nearest(root, new Point(x,y), root);
        return n.getPoint();
    }


   public Node nearest(Node node, Point goal, Node best){
        if (node == null){
            return null;
        }
        if (Point.distance(node.getPoint(),goal) < Point.distance(best.getPoint(),goal)){
            best = node;
        }
        best = nearest(node.leftchild, goal, best);
        best = nearest(node.rightchild, goal, best);

        return best;

    }


}
