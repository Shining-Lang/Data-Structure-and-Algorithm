package Chapter04.Search;

import java.util.ArrayList;

public class SequenceSearch {
    public static void main(String[] args){
        int[] arr = {1, 9, 11, -1, 34, 89, 9};
        ArrayList<Integer> list = new ArrayList<>();
        list = sequenceSearch(arr, 9);
        if(list.isEmpty()){
            System.out.println("No sequence found");
        }
        else{
            System.out.println(list);
        }
    }

    public static ArrayList<Integer> sequenceSearch(int[] arr, int value){
        ArrayList<Integer> result = new ArrayList<>();
        //线性查找是逐一比对，发现相同值，就将下表加入到ArrayList中去
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == value){
                result.add(i);
            }
        }
        return result;
    }
}
