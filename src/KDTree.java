import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Scanner;

public class KDTree {
    private KDPoint[] kdPoints; // size n
    private KDPoint[] xSortedPoints;
    private KDPoint[] ySortedPoints;
    private KDPoint[] zSortedPoints; 
    private KDPoint root; 
 
    public KDTree(KDPoint[] newkdPoints, KDPoint[] newxsorted, KDPoint[] newysorted, KDPoint[] newzsorted){
        this.kdPoints = newkdPoints; 
        this.xSortedPoints = newxsorted; 
        this.ySortedPoints = newysorted; 
        this.zSortedPoints = newzsorted; 
    }
  
    
    public  void buildKDTree() {
        this.root = buildKDTree(kdPoints,xSortedPoints,ySortedPoints,zSortedPoints,0); 
    }
    
    public  KDPoint buildKDTree(KDPoint[] P, KDPoint[] xsorted, KDPoint[] ysorted, KDPoint[] zsorted, int depth) {
        KDPoint[] div1, div2, xsorteddiv1, ysorteddiv1, xsorteddiv2, ysorteddiv2, zsorteddiv1, zsorteddiv2;
        KDPoint root = new KDPoint(0, 0, 0); 
        if(P.length == 1) {
            System.out.println("Base Case");
            System.out.println("xsorted: "+Arrays.toString(xsorted));
            System.out.println("ysorted: "+Arrays.toString(ysorted));
            System.out.println("------------------------------------------------");
            System.out.println();
            return P[0];
        }
        else if(depth % 2 ==0) {
            System.out.println("Partitioning by x median");
            int medIndex = xsorted.length == 2 ? 0:(xsorted.length-1)/2; 
            System.out.println("xsorted: "+Arrays.toString(xsorted)); 
            System.out.println("Med Index: "+medIndex);
            root.setX(xsorted[medIndex].getX());
            root.setY(xsorted[medIndex].getY());
            root.setZ(xsorted[medIndex].getZ());
            root.setZArr(zsorted);
            xsorteddiv1 = new KDPoint[medIndex+1]; 
            xsorteddiv2 = new KDPoint[xsorted.length-medIndex-1];
            int sizeD1 =0, sizeD2 = 0; 
            int countD1 =0, countD2 = 0; 
            
            System.out.println("ysorted: "+Arrays.toString(ysorted)); 
            if(ysorted.length == 2) {
                sizeD1 = 1;sizeD2 = 1; 
            }
            else {
                for(int i = 0; i<ysorted.length;i++) {
                    if(ysorted[i].getX() <= xsorted[medIndex].getX()) {
                        sizeD1++; 
                        
                    }
                    else {
                        sizeD2++; 
                    }
                }
            }
            xsorteddiv1 = copyRange(xsorted,0, medIndex); 
            System.out.println("xsorteddiv1: "+Arrays.toString(xsorteddiv1));
            xsorteddiv2 = copyRange(xsorted, medIndex+1, xsorted.length-1);
            System.out.println("xsorteddiv2: "+Arrays.toString(xsorteddiv2));
           
            div1 = new KDPoint[sizeD1]; 
            div2 = new KDPoint[sizeD2]; 
            if(ysorted.length==2) {
                div1 = copyRange(ysorted,0,medIndex); 
                div2 = copyRange(ysorted,medIndex+1,ysorted.length-1); 
            }
            else {
                for(int i = 0; i<ysorted.length;i++) {
                    if(ysorted[i].getX() <= xsorted[medIndex].getX()) {
                        div1[countD1++] = ysorted[i];
                        
                    }
                    else {
                        div2[countD2++] = ysorted[i];
                    }
                }
            }
            
            ysorteddiv1 = div1; 
            ysorteddiv2 = div2;
            System.out.println("ysorteddiv1: "+Arrays.toString(ysorteddiv1));
            System.out.println("ysorteddiv2: "+Arrays.toString(ysorteddiv2));
            
            if(zsorted.length ==2) {
                zsorteddiv1 = new KDPoint[1];
                zsorteddiv2 = new KDPoint[1];
                
                zsorteddiv1[0] = zsorted[0]; 
                zsorteddiv2[0] = zsorted[zsorted.length-1]; 
            }
            else {
                sizeD1 = 0; sizeD2 = 0; 
                for(int i =0; i<zsorted.length; i++) {
                    if(zsorted[i].getX() <= xsorted[medIndex].getX()) {
                       sizeD1++; 
                   }
                   else {
                       sizeD2++;  
                   }
                }
                zsorteddiv1 = new KDPoint[sizeD1];
                zsorteddiv2 = new KDPoint[sizeD2]; 
                countD1 = 0; countD2 = 0; 
                for(int i = 0; i<zsorted.length;i++) {
                    if(zsorted[i].getX() <= xsorted[medIndex].getX()) {
                         zsorteddiv1[countD1++] = zsorted[i]; 
                    }
                    else {
                        zsorteddiv2[countD2++] = zsorted[i]; 
                    }
                }
            }
            
        }
        else {
            System.out.println("Partitioning by y median");
            int medIndex = ysorted.length == 2 ? 0: (ysorted.length-1)/2;
            System.out.println("ysorted: "+Arrays.toString(ysorted)); 
            System.out.println("Med Index: "+medIndex);
            root.setX(ysorted[medIndex].getX());
            root.setY(ysorted[medIndex].getY());
            root.setZ(ysorted[medIndex].getZ());
            root.setZArr(zsorted);
            ysorteddiv1 = new KDPoint[medIndex+1]; 
            ysorteddiv2 = new KDPoint[ysorted.length-medIndex-1];
            int sizeD1 =0, sizeD2 = 0; 
            int countD1 =0, countD2 = 0; 
            System.out.println("xsorted: "+Arrays.toString(xsorted));
            if(xsorted.length==2) {
                sizeD1 =1;sizeD2 = 1; 
            }
            else {
                for(int i = 0; i<xsorted.length;i++) {
                    if(xsorted[i].getY() <= ysorted[medIndex].getY()) {
                        sizeD1++; 
                        
                    }
                    else {
                        sizeD2++; 
                    }
                }
            }
            ysorteddiv1 = copyRange(ysorted,0, medIndex); 
            ysorteddiv2 = copyRange(ysorted, medIndex+1, ysorted.length-1); 
            
            System.out.println("ysorteddiv1: "+Arrays.toString(ysorteddiv1));
            System.out.println("ysorteddiv2: "+Arrays.toString(ysorteddiv2));
           
            div1 = new KDPoint[sizeD1]; 
            div2 = new KDPoint[sizeD2]; 
            if(xsorted.length == 2) {
                div1 = copyRange(xsorted,0,medIndex);
                div2 = copyRange(xsorted, medIndex+1,xsorted.length-1); 
            }
            else {
                for(int i = 0; i<xsorted.length;i++) {
                    if(xsorted[i].getY() <= ysorted[medIndex].getY()) {
                        div1[countD1++] = xsorted[i];
                        
                    }
                    else {
                        div2[countD2++] = xsorted[i];
                    }
                } 
            }
            
            xsorteddiv1 = div1; 
            System.out.println("xsorteddiv1: "+Arrays.toString(xsorteddiv1));
            xsorteddiv2 = div2;
            System.out.println("xsorteddiv2: "+Arrays.toString(xsorteddiv2));
            
            if(zsorted.length == 2) {
                zsorteddiv1 = new KDPoint[1];
                zsorteddiv2 = new KDPoint[1]; 
                zsorteddiv1[0] = zsorted[0]; 
                zsorteddiv2[0] = zsorted[zsorted.length-1]; 
            }
            else {
                sizeD1 = 0;sizeD2 = 0; 
                for(int i = 0; i<zsorted.length;i++) {
                    if(zsorted[i].getY() <= ysorted[medIndex].getY()) {
                         sizeD1++;
                   }
                   else {
                        sizeD2++;
                   }
                }
                zsorteddiv1 = new KDPoint[sizeD1];
                zsorteddiv2 = new KDPoint[sizeD2]; 
                countD1 = 0; countD2 = 0; 
                for(int i = 0; i<zsorted.length;i++) {
                    if(zsorted[i].getY() <= ysorted[medIndex].getY()) {
                         zsorteddiv1[countD1++] = zsorted[i]; 
                    }
                    else {
                        zsorteddiv2[countD2++] = zsorted[i]; 
                    }
                }
            }
        }
        System.out.println("------------------------------------------------");
        System.out.println();
        KDPoint left = buildKDTree(div1,xsorteddiv1,ysorteddiv1,zsorteddiv1,++depth);
        KDPoint right = buildKDTree(div2,xsorteddiv2,ysorteddiv2,zsorteddiv2,++depth);
        root.setLeft(left);
        root.setRight(right);
        return root; 
    }
    
    public  KDPoint[] copyRange(KDPoint[] a, int startI, int endI) {
        KDPoint[] res = new KDPoint[endI -startI +1]; 
        int count = 0; 
        for(int i = startI; i<=endI; i++) {
            res[count++] = a[i]; 
        }
        return res; 
    }
    
    public  void InOrder(KDPoint root)
    {
        if(root == null) {
            return; 
        }
        InOrder(root.getLeft()); 
        System.out.println(root.toString()+"    root's z array: "+Arrays.toString(root.getZArr()));
        InOrder(root.getRight());
    }

    public KDPoint getRoot() {
        return this.root;
    }

}