package bearmaps;

import java.util.List;

public class KDTree{

    private List<Point> points;
    private Node root;

    private class Node{
        private Point point;
        private boolean flag; //if flag == 0, compare the X; if flag == 1, compare the Y
        private Node leftchild;
        private Node rightchild;

        public Node(Point point, Node leftchild, Node rightchild){
            this.point = point;
            this.leftchild = leftchild;
            this.rightchild = rightchild;
        }

        @Override
        public boolean equals(Object obj) {
            Node node = (Node)obj;

            if (this.point.equals(((Node) obj).point)){
                return true;
            }
            return false;
        }
    }

    public Node insert(Node node){
        double x = node.point.getX();
        double y = node.point.getY();

        if (node.equals(root)){
            return node;
        }

        return null;
    }


    public KDTree(List<Point> points){
        this.points = points;
    }

    public Point nearest(double x,double y){
        return null;
    }

}
