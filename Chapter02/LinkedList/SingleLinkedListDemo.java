package Chapter02.LinkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args){
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addHeroNode(hero1);
//        singleLinkedList.addHeroNode(hero2);
//        singleLinkedList.addHeroNode(hero3);
//        singleLinkedList.addHeroNode(hero4);

        singleLinkedList.addHeroNodeByOrder(hero1);
        singleLinkedList.addHeroNodeByOrder(hero4);
        singleLinkedList.addHeroNodeByOrder(hero2);
        singleLinkedList.addHeroNodeByOrder(hero3);

        HeroNode newHeroNode = new HeroNode(2, "武松", "行者");
        singleLinkedList.updateHeroNode(newHeroNode);

        singleLinkedList.deleteHeroNode(4);

        singleLinkedList.showLinkedList();
    }
}

class SingleLinkedList{
    //初始化一个头结点，不存放任何具体的数据。
    private HeroNode head = new HeroNode(0, "", "");

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

    @Override
    public String toString() {
        return no + " " + name + " " + nickname;
    }
}