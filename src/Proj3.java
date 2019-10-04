import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Proj3 {
    private KDPoint root; 
    private static RecPris[] queries; 
    
    public static void main (String args[]) {
        KDTree kdTree = readPoints();
        kdTree.buildKDTree();
        KDPoint root = kdTree.getRoot(); 
        RecPris C = new RecPris(Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
        int z = rangeCount(queries[1],root,C,0);
        System.out.println(z); 
    }  
    public static KDTree readPoints() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int q = sc.nextInt();
        KDPoint[] kdPoints = new KDPoint[n]; 
        queries = new RecPris[q]; 
        KDPoint[] xSortedPoints = new KDPoint[n];
        KDPoint[] ySortedPoints = new KDPoint[n];
        KDPoint[] zSortedPoints = new KDPoint[n];
        for(int i = 0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();     
            KDPoint point = new KDPoint(x,y,z); 
            kdPoints[i] = point; 
            xSortedPoints[i] =  point; 
            ySortedPoints[i] = point;
            zSortedPoints[i] = point; 
        }
        for(int i=0; i<q; i++) {
            int xmin = sc.nextInt();
            int xmax = sc.nextInt();
            int ymin = sc.nextInt();
            int ymax = sc.nextInt();
            int zmin = sc.nextInt();
            int zmax = sc.nextInt();
            queries[i] = new RecPris(xmin,xmax,ymin,ymax,zmin,zmax); 
        }
        //overhead: sort points by x and y
        Arrays.sort(xSortedPoints, new Comparator<KDPoint>()  {

            @Override
            public int compare(KDPoint o1, KDPoint o2) {
                if(o1.getX() > o2.getX()){
                    return 1; 
                }
                else if(o1.getX() == o2.getX()) {
                    return 0; 
                }
                else {
                    return -1; 
                }
            }
        });
        Arrays.sort(ySortedPoints, new Comparator<KDPoint>()  {

            @Override
            public int compare(KDPoint o1, KDPoint o2) {
                if(o1.getY() > o2.getY()){
                    return 1; 
                }
                else if(o1.getY() == o2.getY()) {
                    return 0; 
                }
                else {
                    return -1; 
                }
            }
        });
        Arrays.sort(zSortedPoints, new Comparator<KDPoint>()  {

            @Override
            public int compare(KDPoint o1, KDPoint o2) {
                if(o1.getZ() > o2.getZ()){
                    return 1; 
                }
                else if(o1.getZ() == o2.getZ()) {
                    return 0; 
                }
                else {
                    return -1; 
                }
            }
        });
        KDTree tree = new KDTree(kdPoints, xSortedPoints, ySortedPoints, zSortedPoints);
        return tree; 
    }

    public static int rangeCount(RecPris Q, KDPoint t, RecPris C, int depth) {
        //if t is a leaf, check if it is in Q
        if(t.getLeft() == null && t.getRight() == null) {
            if(Q.containsPoints(t)) {
                return 1;
            }
            else {
                return 0; 
            }
        }
        
        else {
            if(!C.intersect(Q)) {
                return 0; 
            }
            else if(C.isSubset(Q)){
                int count =0; 
                KDPoint[] zArr = t.getZArr(); 
                for(int i = 0; i<zArr.length;i++) {
                    if(zArr[i].getZ()>= Q.getZmin() && zArr[i].getZ() <= Q.getZmax()) {
                        count++; 
                    }
                }
                return count; 
            }
            else {
                RecPris C1, C2; 
                // x:case
                if(depth%2 == 0) {
                    RecPris[] splits =C.splitXRectangle(Q);
                    C1 = splits[0];
                    C2 = splits[1];
                }
                else {
                    RecPris[] splits =C.splitYRectangle(Q);
                    C1 = splits[0];
                    C2 = splits[1];
                }
                return rangeCount(Q,t.getLeft(),C1,++depth) + rangeCount(Q,t.getRight(),C2,++depth); 
            }
        }
    }
}
