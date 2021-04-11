package numberConstruct;

public class DoubleLinkLsitDemo {
    public static void main(String[] args) {
    //双向链表的测试
        System.out.println("双向链表的测试");
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "松er江", "及as时雨");
        HeroNode2 hero3=new HeroNode2(3,"小虎","春天虎");
        HeroNode2 hero4=new HeroNode2(4,"小打虎","天虎");
        //创建一个双向链表的对象
        DoubleLinkLsit   doubleLinkLsit =new  DoubleLinkLsit();
        doubleLinkLsit.add(hero1);
        doubleLinkLsit.add(hero2);
        doubleLinkLsit.add(hero3);
        doubleLinkLsit.add(hero4);
        doubleLinkLsit.list();

        //修改
        HeroNode2 newHeronode=new HeroNode2(4,"销户","卢仙");
        doubleLinkLsit.update(newHeronode);
        System.out.println("修改后的链表情况");
        doubleLinkLsit.list();
        //删除
        doubleLinkLsit.delete(3);
        System.out.println("删除后的链表");
        doubleLinkLsit.list();
    }

}
//创建一个双向链表类
class DoubleLinkLsit {
    //先初始化一个头节点，头节点不要动（便于找到单链表首端）不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");
    //返回头节点
    public  HeroNode2 getHead() {
        return head;
    }
    //显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;

        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;//判断是否为空节点
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移，一定小心
            temp = temp.next;
        }

    }
    //添加节点到双向链表的最后
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.讲最后这个节点的next指向新的节点
    //3.被加的节点的前一个节点指向temp
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环temp指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
       heroNode.pre=temp;
    }
    //修改一个节点的内容，可以看到双向链表的节点内容和单项链表一样
    //只是节点类型改成Heronode2
    public void update(HeroNode2 newheronode) {
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;//遍历完了
            }
            if (temp.no == newheronode.no) {
                flag = true;
                break;

            }
            temp = temp.next;
        }
        //根据flag是否是要修改的点,到这说明找到了位置并且flag为true
        if (true) {
            temp.name = newheronode.name;
            temp.nickname = newheronode.nickname;
        } else {//没有找到
            System.out.printf("没有找到编号%d 的节点，不能修改\n", newheronode.no);
        }

    }
    //从双向链表中删除一个节点
    //说明
    //1.对于双向链表，我们可以直接找到要删除的这个节点
    //2.找到后，自我删除即可
    public void delete(int no) {
        //判断链表当前时候为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;//辅助指针指向删除的本身
        boolean flag = false;
        while (true) {
            if (temp == null) {//已经到链表的最后说明是空链表了不用删了
                break;
            }
            if (temp.no == no) {
                //找到待删除的一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
            if (true) {//找到可以删除
                //temp.next = temp.next.next【单向链表】
                temp.pre.next=temp.next;
                //这里我们的代码有问题
                //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针异常
                if (temp.next !=null) {
                    temp.next.pre=temp.pre;
                }

            } else {
                System.out.printf("要删除的节点%d 节点不存在",no);
            }
        }
    }


class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点 默认为null
   public  HeroNode2 pre; //指向前一个节点  默认为null

    //构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
        this.name = hName;
        this.no = hNo;
        this.nickname = hNickname;
    }
    @Override
    public String toString() {
        return "HeroNode[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}