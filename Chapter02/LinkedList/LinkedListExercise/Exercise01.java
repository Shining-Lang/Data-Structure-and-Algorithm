package Chapter02.LinkedList.LinkedListExercise;

import java.util.LinkedList;
import java.util.Stack;

public class Exercise01 {
    public static void main(String[] args){
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "武松", "行者");
        HeroNode hero6 = new HeroNode(6, "关胜", "大刀");
        HeroNode hero7 = new HeroNode(7, "花荣", "小李广");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHeroNodeByOrder(hero1);
        singleLinkedList.addHeroNodeByOrder(hero5);
        singleLinkedList.addHeroNodeByOrder(hero4);
        singleLinkedList.addHeroNodeByOrder(hero2);
        SingleLinkedList extraSingleLinkedList = new SingleLinkedList();
        extraSingleLinkedList.addHeroNodeByOrder(hero7);
        extraSingleLinkedList.addHeroNodeByOrder(hero3);
        extraSingleLinkedList.addHeroNodeByOrder(hero6);
//        int length = singleLinkedList.getLength(singleLinkedList.getHead());
//        System.out.println("The length of the list is " + length);
//        HeroNode lastHeroNode = singleLinkedList.getLastIndexNode(singleLinkedList.getHead(), 4);
//        System.out.println("The last HeroNode is " + lastHeroNode);

        System.out.println("The original Linked List:");
        singleLinkedList.showLinkedList();
//        extraSingleLinkedList.showLinkedList();

        System.out.println("The new Linked List:");
        singleLinkedList.mergeLinkedList(singleLinkedList.getHead(), extraSingleLinkedList.getHead());
        singleLinkedList.showLinkedList();

//        singleLinkedList.reverseLinkedList(singleLinkedList.getHead());
//        System.out.println("The Linked List after reversing:");
//        singleLinkedList.showLinkedList();

//        singleLinkedList.reverseLinkedListPrint(singleLinkedList.getHead());
    }
}

class SingleLinkedList{
    //初始化一个头结点，不存放任何具体的数据。
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead(){
        return head;
    }

    public void addHeroNode(HeroNode heroNode){
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp =  temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后。
        temp.next = heroNode;
    }

    //第二种添加方式，根据编号(heroNode.no)将节点插入指定位置
    //如果有这个排名则添加失败，并返回给出提示。
    public void addHeroNodeByOrder(HeroNode heroNode){
        //临时节点temp需要找到所需插入位置的头一个节点，否则加入不了
        HeroNode temp = head;
        boolean flag = false; //添加的编号是否存在
        while(true){
            if(temp.next == null){
                break;
            }
            //如果temp指向的下一个节点的no大于要插入节点的no，就说明，要插入的新节点就紧跟在temp节点的后面。
            if(temp.next.no > heroNode.no){
                break;
            } else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.println("The " + heroNode.no + " has already been added.");
        } else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void showLinkedList(){
        if(head.next == null){
            System.out.println("The LinkedList is empty");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能被修改
    public void updateHeroNode(HeroNode heroNode){
        if(head.next == null){
            System.out.println("The LinkedList is empty");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("The " + heroNode.no + " does not been found.");
        }
    }

    //删除链表中的某一个节点
    public void deleteHeroNode(int no){
        //需要找到要删除节点的头一个节点。
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        } else  {
            System.out.println("The " + no + " does not been found.");
        }
    }

    //获取单链表的节点的个数（如果带头节点的链表，不需要计入头节点）
    /**
     * @param  head: 链表的头节点
     * @return 返回的就是有效节点的个数
     * */
    public int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode currentNode = head.next;
        while(currentNode != null){
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    //查找单链表中倒数第k个节点
    /**
     * @param head:链表的头节点;
     * @param index: 表述倒数第index个节点数
     * */
    public HeroNode getLastIndexNode(HeroNode head, int index){
        if(head.next == null){
            return null;
        }
        int size = getLength(head);
        if(index <= 0 || index > size){
            return null;
        }
        HeroNode currentNode = head.next;
        for(int i = 0; i < (size - index); i++){
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    //将单链表反转
    public void reverseLinkedList(HeroNode head){
        if(head.next == null || head.next.next == null){
            return;
        }
        HeroNode currentNode = head.next;
        HeroNode nextNode = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        while(currentNode != null){
            nextNode = currentNode.next;
            currentNode.next = reverseHead.next;
            reverseHead.next = currentNode;
            currentNode = nextNode;
        }
        head.next = reverseHead.next;
    }

    //逆序打印单链表;(使用栈先进后出的原理来解决.)
    public void reverseLinkedListPrint(HeroNode head){
        if(head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode currentNode = head.next;
        while(currentNode != null){
            stack.push(currentNode);
            currentNode = currentNode.next;
        }
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序单链表，合并之后的链表也依然有序
    public void mergeLinkedList(HeroNode head1, HeroNode head2){
        if(head1 == null && head2 == null){
            return;
        }
        HeroNode currentNode1 = head1.next;
        HeroNode currentNode2 = head2.next;
        HeroNode mergedHead = new HeroNode(0, "", "");
        HeroNode temp = mergedHead;
        while(currentNode1 != null &&  currentNode2 != null){
            if(currentNode1.no > currentNode2.no){
                temp.next = currentNode2;
                currentNode2 = currentNode2.next;
            }  else {
                temp.next = currentNode1;
                currentNode1 = currentNode1.next;
            }
            temp = temp.next;
        }
        if(currentNode1 != null){
            temp.next = currentNode1;
        }
        if(currentNode2 != null){
            temp.next = currentNode2;
        }

        head.next = mergedHead.next;

    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public String toString() {
        return no + " " + name + " " + nickname;
    }
}
