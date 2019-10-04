import java.util.Arrays;

public class KDPoint {
    private int x; 
    private int y;
    private int z;
    
    private KDPoint left;
    private KDPoint right;
    private KDPoint zArr[]; 
    
    public KDPoint(int newX, int newY, int newZ) {
        this.x = newX; 
        this.y = newY; 
        this.z = newZ;
        this.left = null; 
        this.right = null; 
    }
    
    public void setX(int newx) {
        this.x = newx;
    }
    public int getX() {
        return this.x; 
    }
    
    public void setY(int newy) {
        this.y = newy;
    }
    public int getY() {
        return this.y; 
    }
    public void setZ(int newz) {
        this.z = newz;
    }
    public int getZ() {
        return this.z; 
    }
    
    public void setLeft(KDPoint temp) {
        this.left = temp; 
    }
    public void setRight(KDPoint temp) {
        this.right = temp; 
    }
    public KDPoint getLeft(){
        return this.left;
    }
    public KDPoint getRight(){
        return this.right;
    }
    public void setZArr(KDPoint [] zs) {
        this.zArr = zs; 
    }
    public KDPoint[] getZArr() {
        return this.zArr; 
    }
    public String toString() {
        return "("+this.x+","+this.y+","+this.z+")";
    }
}
