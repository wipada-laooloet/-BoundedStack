import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Test Runner
 */
public class BoudedStackTest {
    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea BoundedStackTest\n");
        }

        System.out.println("=== BoundedStack Test Suite ===\n");

        //function ที่เทส อย่าลืมมาเขียน
        testCreators();
        testAdd() ;
        testRemove() ;
        testObservers() ;
        testExposure() ;
        testRankMovie() ;


        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static void testCreators() {
        System.out.println("-- Creators --");

        BoundedStack empty = new BoundedStack(10);
        check("new() -> empty", empty.size() == 0);
        check("new() -> contains nothing", !empty.contains("anything"));

        BoundedStack b = new BoundedStack(Arrays.asList("A", "B", "C"));
        check("new(list) -> size 3", b.size() == 3);
        check("new(list) -> contains B", b.contains("B"));
        check("new(list) -> preserves order",
                b.catalog().equals(Arrays.asList("A", "B", "C")));

        // boundary: list ว่างคือขอบล่างที่ถูกต้อง
        BoundedStack fromEmpty = new BoundedStack(new ArrayList<String>());
        check("new(empty list) -> empty", fromEmpty.size() == 0);

        // input ผิดเงื่อนไขโยน exception
        boolean threwDup = false;
        try {
            new BoundedStack(Arrays.asList("A", "A"));
        } catch (IllegalArgumentException e) {
            threwDup = true;
        }
        check("new(duplicates) -> throws IllegalArgumentException", threwDup);

        boolean threwNull = false;
        try {
            new BoundedStack(Arrays.asList("A", null));
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);

        boolean threwNullList = false;
        try {
            new BoundedStack(null);
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwNullList);
    }


    private static void testAdd() {
        System.out.println("\n-- Add --");

        BoundedStack s = new BoundedStack(10);
        check("add(A) -> returns true", s.push("A"));
        check("add(A) -> size 1", s.size() == 1);
        check("add(A) -> found by contains", s.contains("A"));

        s.push("B");
        s.push("C");
        check("add preserves insertion order",
                s.catalog().equals(Arrays.asList("A", "B", "C")));

        // เพลงซ้ำไม่ใช่ error — คืน false เฉย ๆ
        check("add duplicate -> returns false", !s.push("A"));
        check("failed add leaves size unchanged", s.size() == 3);

        // input ผิดเงื่อนไขโยน exception
        boolean threwEmpty = false;
        try {
            s.push("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("add(empty string) -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            s.push(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("add(null) -> throws IllegalArgumentException", threwNull);

        check("failed adds leave playlist unchanged", s.size() == 3);

        // boundary: เติมจนเต็มพอดีแล้วเติมเพิ่ม
        BoundedStack full = new BoundedStack(10);
        int cap = 10 ;
        for (int i = 0; i < cap; i++) {
            full.push("song" + i);
        }
        check("can fill up to MAX_SONGS", full.size() == cap);
        check("add when full -> returns false", !full.push("one more"));
        check("full playlist stays at MAX_SONGS",
                full.size() == cap);
    }

    private static void testRemove() {
        System.out.println("\n-- Remove --");

    BoundedStack s = new BoundedStack(Arrays.asList("A", "B", "C"));
        check("remove(B) -> returns true", s.pop("B"));
        check("remove -> size decreases", s.size() == 2);
        check("remove -> song is gone", !s.contains("B"));
        check("remove keeps the others in order",
                s.catalog().equals(Arrays.asList("A", "C")));

        // ลบเพลงที่ไม่มีไม่ใช่ error — คืน false เฉย ๆ
        check("remove missing song -> returns false", !s.pop("nope"));
        check("failed remove leaves size unchanged", s.size() == 2);

        // boundary: ลบจนหมด
        s.pop("A");
        s.pop("C");
        check("remove all -> empty", s.size() == 0);
        check("remove on empty playlist -> returns false", !s.pop("A"));
    }

        private static void testObservers() {
        System.out.println("\n-- Observers --");

        BoundedStack s = new BoundedStack(Arrays.asList("A", "B"));
        check("size reports 2", s.size() == 2);
        check("contains finds an existing song", s.contains("A"));
        check("contains rejects a missing song", !s.contains("Z"));
        check("songs returns the full list in order",
                s.catalog().equals(Arrays.asList("A", "B")));

        int before = s.size();
        s.size();
        s.contains("A");
        s.catalog();
        check("observers have no side effects", s.size() == before);
    }

    private static void testExposure() {
        System.out.println("\n-- Representation Exposure --");

        // ขาออก: แก้ list ที่ได้จาก songs() ต้องไม่กระทบ rep
        BoundedStack s = new BoundedStack(10);
        s.push("A");

        List<String> got = s.catalog();
        got.clear();
        check("clearing result of songs() does not affect playlist",
                s.size() == 1);

        got = s.catalog();
        got.add("injected");
        check("adding to result of songs() does not affect playlist",
                s.size() == 1 && !s.contains("injected"));

        // สองครั้งต้องเป็นคนละ object
        check("songs() returns a fresh list each call",
                s.catalog() != s.catalog());

        // ขาเข้า: แก้ list ที่ส่งให้ constructor ต้องไม่กระทบ rep
        List<String> input = new ArrayList<String>(Arrays.asList("A", "B"));
        BoundedStack p = new BoundedStack(input);

        input.clear();
        check("clearing constructor argument does not affect playlist",
                p.size() == 2);

        input.add("injected");
        check("adding to constructor argument does not affect playlist",
                !p.contains("injected"));
    }

    private static void testRankMovie() {
        System.out.println("\n-- Rank Movie --");

        // 1. ทดสอบการเรียงลำดับตาม List ที่ส่งเข้าไป
        BoundedStack original = new BoundedStack(Arrays.asList("Movie A", "Movie B", "Movie C"));
        List<String> newRank = Arrays.asList("Movie C", "Movie A", "Movie B");
        
        BoundedStack ranked = original.rankMovie(newRank);

        check("rankMovie() -> returns new stack with new order",
                ranked.catalog().equals(Arrays.asList("Movie C", "Movie A", "Movie B")));

        // 2. ทดสอบว่าถ้าส่งชื่อหนังที่ไม่เคยอยู่ใน stack มา มันจะไม่หลุดเข้ามา
        List<String> rankWithExtra = Arrays.asList("Movie C", "Unknown Movie", "Movie A");
        BoundedStack rankedFiltered = original.rankMovie(rankWithExtra);

        check("rankMovie() -> ignores movies not in original catalog",
                rankedFiltered.catalog().equals(Arrays.asList("Movie C", "Movie A")));

        // 3. ทดสอบว่า Stack เดิม (original) ต้องไม่ถูกแก้ไข
        check("rankMovie() -> original stack stays unchanged",
                original.catalog().equals(Arrays.asList("Movie A", "Movie B", "Movie C")));

        // 4. ทดสอบ Boundary Case: ถ้าส่ง null เข้าไป ต้องโยน IllegalArgumentException
        boolean threwNull = false;
        try {
            original.rankMovie(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("rankMovie(null) -> throws IllegalArgumentException", threwNull);
    }
    
}


