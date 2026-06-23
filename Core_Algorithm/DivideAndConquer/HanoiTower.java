package Core_Algorithm.DivideAndConquer;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if(num == 1) {
            System.out.println("The 1 from " + a + " to " + c);
        }
        else {
            //把上面的所有盘看作是一个，然后从a移动到b，中间借助c
            hanoiTower(num - 1, a, c, b);
            //最下面得一个盘，从a移动到c
            System.out.println("The " + num + " from " + a + " to " + c);
            //然后再把最上面的所有盘子移动到c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
