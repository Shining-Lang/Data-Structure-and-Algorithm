package Core_Algorithm.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> hashset1 = new HashSet<>();
        hashset1.add("beijing");
        hashset1.add("shanghai");
        hashset1.add("tianjin");
        HashSet<String> hashset2 = new HashSet<>();
        hashset2.add("guangzhou");
        hashset2.add("beijing");
        hashset2.add("shenzhen");
        HashSet<String> hashset3 = new HashSet<>();
        hashset3.add("chengdu");
        hashset3.add("shanghai");
        hashset3.add("hangzhou");
        HashSet<String> hashset4 = new HashSet<>();
        hashset4.add("shanghai");
        hashset4.add("tianjin");
        HashSet<String> hashset5 = new HashSet<>();
        hashset5.add("hangzhou");
        hashset5.add("dalian");

        broadcasts.put("K1", hashset1);
        broadcasts.put("K2", hashset2);
        broadcasts.put("K3", hashset3);
        broadcasts.put("K4", hashset4);
        broadcasts.put("k5", hashset5);

        //allAreas存放所有城市
        HashSet<String> allAreas = new HashSet<>();
        for(HashSet<String> value : broadcasts.values()){
            allAreas.addAll(value);
        }

        //创建ArrayList，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合;存放遍历过程中电台覆盖的地区和还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖地区所对应得电台的key
        String maxKey = null;
        int maxIntersectionSize = 0;

        while (!allAreas.isEmpty()) {
            maxKey = null;
            maxIntersectionSize = 0;
            for(String key : broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出两个集合的交集
                tempSet.retainAll(allAreas);
                if(!tempSet.isEmpty() && (maxKey == null || tempSet.size() > maxIntersectionSize)){
                    maxKey = key;
                    maxIntersectionSize = tempSet.size();
                }
            }

            if(maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println(selects);
    }
}
