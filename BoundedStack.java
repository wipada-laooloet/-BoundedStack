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
     * @param capacity
     */
    public BoundedStack(int capacity){
        this.catalog = new ArrayList<>();
        this.capacity = capacity ; 
        

    }
    private void checkRep() {
        assert catalog != null ;
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

    /**
     * @param s
     */
    public boolean push(String movie){
        if(movie==null || movie =="") throw new IllegalArgumentException();
        if(catalog.contains(movie) || catalog.size()==capacity)
        return false ;
        catalog.add(movie);
        checkRep();
        return false;
        
    }
    public boolean pop(String movie){
        if(!catalog.contains(movie) ) 
        return false ;
       catalog.remove(movie);
       checkRep();
        return false;
        
    }
     
    public List<String> catalog() {
        return new ArrayList<>(catalog);   // แก้บรรทัดนี้
    }
    
    public BoundedStack rankMovie(List<String> rankedList) {
        if (rankedList == null) {
            throw new IllegalArgumentException("Ranked list cannot be null");
        }
        List<String> copy = new ArrayList<>();
        for (String item : rankedList) {
            if (this.catalog.contains(item)) {
                copy.add(item);
            }
        }
        return new BoundedStack(copy);   
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
