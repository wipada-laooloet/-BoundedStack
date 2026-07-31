import java.util.*;

/**
 * BoundedStack
 */
public class BoundedStack {
    private final List<String> catalog ;
    private final int moive ;
    
    
    //AF(catalog,capacity) = elements คือ Stack ที่ใช้เก็บข้อมูลตามความจุ(capacity) ที่กำหนดไว้
    //RI
    //-capacity > 0
    //-catalog ต้องไม่เป็น null
    //-catalog != ""
    //-จำนวนหนังต้องน้อยกว่าหรือเท่ากับ capacity
    //

    /**
     * 
     * @param catalog
     */
    public BoundedStack(int catalog){
        this.catalog = new ArrayList<>();
        this.catalog = catalog ; 
        

    }
    private void checkRep() {
        assert catalog != null;
    assert catalog > 0;
    assert catalog.size() <= catalog ;

    Set<String> set = new HashSet<>();

    for(String s : catalog){
        assert s != null;
        assert !s.isEmpty();
        assert set.add(s);
    }
    }
    
    public BoundedStack(List<String> initial) {
        if(initial == null) throw new IllegalArgumentException() ;
        this.catalog = initial.size();
        if(initial.size() > catalog) throw new IllegalArgumentException() ;
        Set<String> seen = new HashSet<>();
        for (String s : initial) {
            if(s == null || s == "") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException() ;
        }
        this.catalog = new ArrayList<>(initial) ; 
         this.catalog = catalog;// แก้บรรทัดนี้
        checkRep(); 
    }
    

    /**
     * @param s
     */
    public boolean push(String movie){
        return false ;
    }
    public boolean pop(String movie){
        return false ;
    }
     
    public List<String> catalog() {
        return new ArrayList<>(catalog);   // แก้บรรทัดนี้
    }


    public int size() {
        return catalog.size();   // แก้บรรทัดนี้
    }
     public boolean contains(String movie) {
        return catalog.contains(movie);   // แก้บรรทัดนี้
    }
    @Override
    public String toString() {
        return catalog.toString();
    }
}
