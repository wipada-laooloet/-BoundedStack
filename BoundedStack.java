import java.util.*;

/**
 * BoundedStack
 */
public class BoundedStack {
    private final List<String> catalog ;
    private final int capacity ;
    
    
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
    public BoundedStack(int capacity){
        this.catalog = new ArrayList<>();
        this.capacity = capacity ; 
        

    }
    private void checkRep() {
    assert catalog != null;
    assert capacity > 0;
    assert catalog.size() <= capacity ;

    Set<String> set = new HashSet<>();

    for(String s : catalog){
        assert s != null;
        assert !s.isEmpty();
        assert set.add(s);
    }
    }
    
    public BoundedStack(List<String> initial) {
        if(initial == null) throw new IllegalArgumentException() ;
        this.capacity = initial.size();
        this.catalog = new ArrayList<>();
        if(initial.size() > capacity) throw new IllegalArgumentException() ;
        Set<String> seen = new HashSet<>();
        for (String s : initial) {
            if(s == null || s == "") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException() ;
             catalog.add(s);
        }
        checkRep();
    }

    public boolean push(String movie){
        /**if(movie == null || movie.isEmpty())
        throw new IllegalArgumentException();

        if(catalog.contains(movie))
        return false;

        if(catalog.size() == capacity)
        return false;

        catalog.add(movie);
        checkRep();

        return true;/* */
        if(movie==null || movie =="") throw new IllegalArgumentException();
        if(catalog.contains(catalog) || catalog.size()==MAX_MOVIES)
        return false ;
        movie.add(movie);
        checkRep();
        return true
        return false ;
    }
    public boolean pop(String movie){
       /*if(catalog.remove(movie)){
        checkRep();
        return true;
        }
    return false ; /* */ 
     if(!movies().contains(movie) ) 
        return false ;
       movies().remove(movie);
       checkRep();
        return false;
    }

    public List<String> catalog() {
        return new ArrayList<>(catalog);   // แก้บรรทัดนี้
    }


    public int size() {
        return catalog.size();   // แก้บรรทัดนี้
    }
    public List<String> movies() {
    return new ArrayList<>();
    }

     public boolean contains(String movie) {
        return catalog.contains(movie);   // แก้บรรทัดนี้
    }
    @Override
    public String toString() {
        return catalog.toString();
    }
}
