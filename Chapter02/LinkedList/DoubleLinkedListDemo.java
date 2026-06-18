package Chapter02.LinkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.addHeroNode(hero1);
//        doubleLinkedList.addHeroNode(hero2);
//        doubleLinkedList.addHeroNode(hero3);
//        doubleLinkedList.addHeroNode(hero4);
        doubleLinkedList.addHeroNodeByOrder(hero3);
        doubleLinkedList.addHeroNodeByOrder(hero2);
        doubleLinkedList.addHeroNodeByOrder(hero1);
        doubleLinkedList.addHeroNodeByOrder(hero4);

        doubleLinkedList.showLinkedList();

        HeroNode2 newHeroNode = new HeroNode2(2, "武松", "行者");
        doubleLinkedList.updateHeroNode(newHeroNode);
        System.out.println("---------------------------------------");
        doubleLinkedList.showLinkedList();

        doubleLinkedList.deleteHeroNode(4);
        System.out.println("---------------------------------------");
        doubleLinkedList.showLinkedList();

    }
}

class DoubleLinkedList{
    //初始化头节点
    private HeroNode2 head =  new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //显示链表
    public void showLinkedList(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("The LinkedList is empty");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while(true){
            //判断链表是否到达最后
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    public void addHeroNode(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp =  temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后。
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //第二种添加方式，根据编号(heroNode.no)将节点插入指定位置
    //如果有这个排名则添加失败，并返回给出提示。
    public void addHeroNodeByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        boolean flag = false; //添加的编号是否存在
        while(temp.next != null){
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

            if(temp.next != null){
                temp.next.pre = heroNode;
            }

            temp.next = heroNode;
            heroNode.pre = temp;
        }
    }

    //修改节点信息，根据no编号来修改，即no编号不能被修改
    public void updateHeroNode(HeroNode2 heroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("The LinkedList is empty");
            return;
        }
        HeroNode2 temp = head.next;
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

    //从双向链表中删除一个节点
    //删除链表中的某一个节点
    public void deleteHeroNode(int no){
        //判断当前链表是否为空
        if(head.next == null){
            System.out.println("The LinkedList is empty");
            return;
        }
        //需要找到要删除节点的头一个节点。
        HeroNode2 temp = head.next;
        boolean flag = false;
        while(temp != null){
            if(temp.no == no){
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            //如果是删除最后一个节点，就不需要下面这句话。因为会出现空指针异常。
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        } else  {
            System.out.println("The " + no + " does not been found.");
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向前一个结点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickname = nickName;
    }

    @Override
    public String toString() {
        return no + " " + name + " " + nickname;
    }
}