
public class RecPris {
    private int xmin; 
    private int xmax; 
    private int ymin; 
    private int ymax; 
    private int zmin; 
    private int zmax; 
    
    public RecPris(int newxmin, int newxmax, int newymin, int newymax, int newzmin, int newzmax) {
        this.xmin = newxmin; 
        this.xmax = newxmax; 
        this.ymin = newymin; 
        this.ymax = newymax; 
        this.zmin = newzmin; 
        this.zmax = newzmax; 
    }
    
    
    public int getXmin() {
        return xmin;
    }
    public void setXmin(int xmin) {
        this.xmin = xmin;
    }
    public int getXmax() {
        return xmax;
    }
    public void setXmax(int xmax) {
        this.xmax = xmax;
    }
    public int getYmin() {
        return ymin;
    }
    public void setYmin(int ymin) {
        this.ymin = ymin;
    }
    public int getYmax() {
        return ymax;
    }
    public void setYmax(int ymax) {
        this.ymax = ymax;
    }
    public int getZmin() {
        return zmin;
    }
    public void setZmin(int zmin) {
        this.zmin = zmin;
    }
    public int getZmax() {
        return zmax;
    }
    public void setZmax(int zmax) {
        this.zmax = zmax;
    }
    
    public boolean intersect(RecPris B) {
        return !(B.getXmin() > this.getXmax() || B.getXmax() < this.getXmin() || B.getYmin() > this.getYmax() || B.getYmax() < this.getYmin() 
            || B.getZmin() > this.getZmax() || B.getZmax() < this.getZmin()); 
    }
    public boolean isSubset(RecPris B) {
        return inInterval(this.xmin, this.xmax, B.getXmin(), B.getXmax()) && inInterval(this.ymin, this.ymax, B.getYmin(), B.getYmax()) &&
            inInterval(this.zmin, this.zmax, B.getZmin(), B.getZmax());
    }
    private boolean inInterval(int pMin, int pMax, int min, int max){
        return pMin >= min && pMax <= max; 
    }
    
    public RecPris[] splitXRectangle(RecPris B) {
        RecPris[] res = new RecPris[2];
        RecPris left, right; 
        if(this.xmin < B.getXmin() && this.xmax > B.getXmin() && this.xmax < B.getXmax()) {
            res[0] = new RecPris(this.xmin, B.getXmin(), this.ymin, this.ymax, this.zmin,this.zmax);
            res[1] = new RecPris(B.getXmin(),this.xmax, this.ymin,this.ymax, this.zmin,this.zmax);
        }
        else {
            res[0] = new RecPris(this.xmin, B.getXmax(), this.ymin,this.ymax,this.zmin,this.zmax);
            res[1] = new RecPris(B.getXmax(), this.xmax, this.ymin,this.ymax,this.zmin,this.zmax); 
        }
        return res; 
    }

    public RecPris[] splitYRectangle(RecPris B) {
        RecPris[] res = new RecPris[2];
        RecPris left, right; 
        if(this.ymin < B.getYmin() && this.ymax > B.getYmin() && this.ymax < B.getYmax()) {
            res[0] = new RecPris(this.xmin,this.xmax,this.ymin,B.getYmin(),this.zmin,this.zmax); 
            res[1] = new RecPris(this.xmin,this.xmax, B.getYmin(),this.ymax, this.zmin,this.zmax);
        }
        else {
            res[0] = new RecPris(this.xmin, this.xmax, this.ymin,B.getYmax(),this.zmin,this.zmax);
            res[1] = new RecPris(this.xmin, this.xmax, B.getYmax(),this.ymax,this.zmin, this.zmax); 
        }
        return res; 
    }
    
    public boolean containsPoints(KDPoint t) {
        return  t.getX() >= this.xmin && t.getX() <=this.xmax && t.getY() >= this.ymin && t.getY() <=this.ymax 
            && t.getZ() >= this.zmin && t.getZ() <=this.zmax;
    }
    
    public String toString() {
        return "["+this.xmin+","+this.xmax+"],"+"["+this.ymin+","+this.ymax+"],"+"["+this.zmin+","+this.zmax+"]"; 
    }
}
