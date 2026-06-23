package KMP;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "absdfksjmfkskrjds";
        String s2 = "kskrj";
        int index = violenceMatch(s1,s2);
        System.out.println(index);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] a1 = str1.toCharArray();
        char[] a2 = str2.toCharArray();
        int s1Len = a1.length;
        int s2Len = a2.length;

        int i = 0;
        int j = 0;

        while(i < s1Len && j < s2Len) {
            if(a1[i] == a2[j]) {
                i++;
                j++;
            }
            else {
                i = i - j + 1;
                j = 0;
            }
        }
        if(j == s2Len) {
            return i - j;
        }
        else {
            return -1;
        }
    }
}
