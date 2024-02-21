import java.util.*;

public class Map01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa"); //list 중복 가능
        list.get(1); // 첫번째꺼 호출

        // map 자료형이 2개 들어간다
        //map.put(key, value);
        // key
        // value
        Map<Integer ,String> map = new HashMap<>();
        map.put(111, "bbb");
        map.put(222, "ccc");
        map.put(333, "d+");
        map.put(333, "java");
        // key 가 중복일 때 최신 값으로 덮어써버림
        map.get(111); // key 의 값 호출
        System.out.println(map.get(111));
    }


    public Map<String, Object> aaa(){
        aA a = new aA();
        bB b = new bB();

        //Map<String, aA> map = new HashMap<>();
        //map.put("A 객체", a);
        //map.put("B 객체", b);
        //Map<String, aA> 자료형이 aA 이기때문에 b 값이 안들어감 b는 자료형이 bB 라서.

        Map<String, Object> map = new HashMap<>();
        map.put("A 객체", a);
        map.put("B 객체", b);
        return map;
    }
}

class  aA{}
class  bB{}
class  cC{}

