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
     * สร้าง Stack เปล่า และกำหนดจำนวนหนังสูงสุดที่สามารถเก็บได้
     * @param capacity จำนวนหนังสูงสุดที่ Stack สามารถเก็บได้
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
    /**
    * สร้าง Stack จากรายการหนังที่กำหนดมา
    *
    * @param initial รายการหนังเริ่มต้นที่จะนำมาเก็บใน Stack
    */
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
   * เพิ่มหนังเข้าไปใน Stack
    *
    * @param movie ชื่อหนังที่ต้องการเพิ่ม
    * @return true ถ้าเพิ่มสำเร็จ, false ถ้าหนังมีอยู่แล้วหรือ Stack เต็ม
    */
    public boolean push(String movie){
        if(movie==null || movie =="") throw new IllegalArgumentException();
        if(catalog.contains(movie) || catalog.size()==capacity)
        return false ;
        catalog.add(movie);
        checkRep();
        return true;
        
    }
    /**
    * ลบหนังออกจาก Stack
    *
    * @param movie ชื่อหนังที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบหนัง
    */
    public boolean pop(String movie){
        if(!catalog.contains(movie) ) 
        return false ;
       catalog.remove(movie);
       checkRep();
        return true;
        
    }
     /**
     * คืนค่ารายการหนังทั้งหมดใน Stack
     *
     * @return รายการหนังทั้งหมด
    */
    public List<String> catalog() {
        return new ArrayList<>(catalog);   // แก้บรรทัดนี้
    }
    /**
    * เลือกเฉพาะหนังที่มีอยู่ใน Stack
    * โดยเรียงลำดับตามรายการที่รับเข้ามา
    *
    * @param rankedList รายการหนังที่ต้องการจัดอันดับ
    * @return Stack ใหม่ที่มีเฉพาะหนังที่ตรงกัน
    */
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
    /**
    * คืนค่าจำนวนหนังที่อยู่ใน Stack ตอนนี้
    *
    * @return จำนวนหนังทั้งหมด
    */
    
    public int size() {
        return catalog.size();   // แก้บรรทัดนี้
    }
    /**
    * ตรวจสอบว่ามีหนังเรื่องนี้อยู่ใน Stack หรือไม่
    *
    * @param movie ชื่อหนังที่ต้องการตรวจสอบ
    * @return true ถ้ามี, false ถ้าไม่มี
    */
     public boolean contains(String movie) {
        return catalog.contains(movie);   // แก้บรรทัดนี้
    }
    /**
    * แสดงรายการหนังทั้งหมดในรูปแบบข้อความ
    *
    * @return ข้อความที่แสดงรายการหนัง
    */
    @Override
    public String toString() {
        return catalog.toString();
    }
}
