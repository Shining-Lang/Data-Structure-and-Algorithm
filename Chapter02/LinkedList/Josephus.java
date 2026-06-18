package Chapter02.LinkedList;

public class Josephus {
    public static void main(String args[]){
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加小孩节点，构成一个环形的链条
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("The nums is uncorrected.");
            return;
        }
        Boy curBoy = null; //辅助节点，帮助构建环形链表
        for(int i = 1; i <= nums; i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first); //构成环
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("The list is empty.");
            return;
        }
        //first永远指向第一个，所以需要创建一个辅助指针来完成遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("the number of boy is %d \n", curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入。计算出小孩出圈的顺序
    /**
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     * */
    public void countBoy(int startNo, int countNum, int nums) {
        if(first == null || startNo < 1 ||  startNo > nums || countNum < 1){
            System.out.println("The input is uncorrected.");
            return;
        }
        //创建一个辅助指针helper，事先应该指向环形链表的最后一个节点
        Boy helper = first;
        while(true){
            if(helper.getNext() == first){ //说明helper指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper 移动k-1次
        for(int j = 0; j < startNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈。
        while(true){
            if(helper == first){
                break;
            }
            //让first和helper同时移动countNum-1
            for(int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("the boy of %d is out. \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("The final boy of %d in list.", first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo(){
        return no;
    }

    public void setNo(int no){
        this.no = no;
    }

    public Boy getNext(){
        return next;
    }
    public void setNext(Boy next){
        this.next = next;
    }
}
