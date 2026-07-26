import java.util.*;

/**
 * BoundedStack
 */
public class BoundedStack {
    private final List<String> elements ;
    private final int capacity ;
    //AF(elements,capacity) = elements คือ Stack ที่ใช้เก็บข้อมูลตามความจุ(capacity) ที่กำหนดไว้
    //RI
    //-capacity > 0
    //-stack ต้องไม่เป็น null
    //-stack != ""
    //-จำนวน stack ต้องน้อยกว่าหรือเท่ากับ capacity
    //

    /**
     * 
     * @param capacity
     */
    public BoundedStack(int capacity){
        this.elements = new ArrayList<>();
        this.capacity = capacity ; 
    }

    /**
     * @param s
     */
    public void push(String s){

    }
}
