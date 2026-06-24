package Core_Algorithm.KMP;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String text = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        int index = kmpSearch(text, pattern, kmpNext(pattern));
        System.out.println(index);
    }

    public static int kmpSearch(String text, String pattern, int[] next) {
        for(int i = 0, j = 0; i < text.length(); i++) {
            //需要成立不相等的情况怎么做
            while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if(text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if(j == pattern.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（子串)得部分匹配值
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0; // 如果字符串的长度为1，那么它的部分匹配值就是0；
        for(int i = 1, j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}



class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int[] next = buildNext(needle);

        int i = 0; // 指向 haystack
        int j = 0; // 指向 needle

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                // needle 全部匹配完成
                if (j == needle.length()) {
                    return i - j;
                }
            } else {
                if (j > 0) {
                    // haystack 的 i 不动，needle 的 j 回退
                    j = next[j - 1];
                } else {
                    // j 已经在 0 了，没法回退，只能 i 往后走
                    i++;
                }
            }
        }

        return -1;
    }

    // 构建 next 数组，也叫 lps 数组
    private int[] buildNext(String pattern) {
        int[] next = new int[pattern.length()];

        int i = 1; // 从第二个字符开始
        int j = 0; // 当前已经匹配的前缀长度

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                next[i] = j;
                i++;
            } else {
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }

        return next;
    }
}