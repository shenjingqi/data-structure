package numberConstruct;

import java.util.Stack;

public class linklist {
    public static void main(String[] Args) {
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "松er江", "及as时雨");
        HeroNode hero3=new HeroNode(3,"小虎","春天虎");
        HeroNode hero4=new HeroNode(4,"小打虎","天虎");
        singlelinkedlist  s1=   new singlelinkedlist();
        s1.add(hero1);
        s1.add(hero2);
        s1.add(hero3);
        s1.insert(hero4);

        s1.reversePoint(s1.getHead());


    }

}
//定义HeroNode类每一个HeronNode对象就是一个节点
    class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;//指向下一个节点

        //构造器
        public HeroNode(int hNo, String hName, String hNickname) {
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
        //定义一个singlinkelist 管理我们的英雄
        class singlelinkedlist {
            //先初始化一个头节点，头节点不要动（便于找到单链表首端）不存放具体的数据
            private HeroNode head = new HeroNode(0, "", "");

            public  HeroNode getHead() {
                return head;
            }

            //添加节点到单向链表
            //思路，当不考虑编号顺序时
            //1.找到当前链表的最后节点
            //2.讲最后这个节点的next指向新的节点
            public void add(HeroNode heroNode) {
                //因为head节点不能动，因此我们需要一个辅助遍历temp
                HeroNode temp = head;
                //遍历链表，找到最后
                while (true) {
                    if (temp.next == null) {
                        break;
                    }
                    //如果没有找到最后，将temp后移
                    temp = temp.next;
                }
                //当退出while循环temp指向了链表的最后
                //将最后这个节点指向性的节点
                temp.next = heroNode;
            }

            //显示链表
            public void list() {
                if (head.next == null) {
                    System.out.println("链表为空");
                    return;

                }
                //因为head节点不能动，因此我们需要一个辅助遍历temp
                HeroNode temp = head.next;
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

            //第二种方式在添加英雄时根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
            public void insert(HeroNode heroNode) {
                //因为头节点不能动，因此我们仍然通过一个辅助变量来帮助找到添加的位置
                //因为单链表，我们找到的temp位于是于添加前一个节点否则插入不了
                HeroNode temp = head;
                boolean flag = false;//flag表示添加的编号时候存在默认为false
                while (true) {
                    if (temp.next == null) {//说明temp已经在链表的最后
                        break;
                    }
                    if (temp.next.no > heroNode.no) {//位置找到了就在temp的后边插入
                        break;
                    } else if (temp.next.no == heroNode.no) {//说明希望添加的heronode已经存在
                        flag = true;
                        break;
                    }
                    temp = temp.next;
                }
                //判断flag的值
                if (flag) {
                    System.out.println("当前插入的编号已经存在了，不能加入" + heroNode);
                } else {
                    //插入到链表中，temp的后面
                    heroNode.next = temp.next;
                    temp.next = heroNode;
                }

            }

            public void update(HeroNode newheronode) {
                HeroNode temp = head.next;
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
            //  返回头节点

            //删除思路
            //1.head 不能动，因此我们需要一个temp辅助遍历找到删除的节点的前一个节点
            //说明我们在比较是temp.next.no和需要删除的节点no比较
            public void delete(int no) {
                HeroNode temp = head;
                boolean flag = false;
                while (true) {
                    if (temp.next == null) {//已经到链表的最后
                        break;
                    }
                    if (temp.next.no == no) {
                        //找到待删除的一个节点temp
                        flag = true;
                        break;
                    }
                    temp = temp.next;
                    if (true) {
                        temp.next = temp.next.next;
                    } else {
                        System.out.printf("要删除的节点%d 节点不存在");
                    }
                }
            }

            //方法：获取到单链表的的节点个数(如果是带头节点，需要不统计头节点)
            public static int getlength(HeroNode head) {
                if (head.next == null) {
                    return 0;
                }
                //执行到这里说明已经不是空链表
                int length = 0;
                //没有统计头节点
                HeroNode temp = head.next;
                while (temp != null) {
                    length++;
                    temp = temp.next;

                }
                return length;
            }
            //查找单链表的倒数第k个节点
            /*思路
            1.编写一个方法，接受一个head节点，同时接受一个index
            2.Index表示是倒数第index节点
            3.把链表从头到尾遍历，得到链表的长度getlength
            4.size之后，从链表的第一个开始遍历（size-length）个就可以得到
            5.如果找到节点了，就返回节点否则则返回null
             */

            public static HeroNode findlastIndexNode(HeroNode head, int index) {
                //如果链表为空，返回null
                if (head.next == null) {
                    return null;//没有找到
                }//第一个遍历到链表的长度
                int size = getlength(head);
                //第二个遍历size-index位置，就是我们倒数第K个节点
                if (index <= 0 || index > size) {
                    return null;
                }
                HeroNode temp = head.next;
                //  while (temp==size-index)数据类型不一样不能使用
                //定义给辅助变量，for循环定位到倒数的index
                for (int i = 0; i < size - index; i++) {
                    temp = temp.next;
                }
                return temp;
            }

            //将单链表进行反转
            public static void reversetList(HeroNode head) {
                //如果当前链表为空，或者只有一个节点，无需反转，直接返回
                if (head.next == null || head.next.next == null) {
                    return;
                }
                //定义一个辅助指针，帮助我们来遍历原来的链表
                HeroNode cur = head.next;
                HeroNode next = null;//指向当前节点[nur]的下一个节点
                HeroNode reverseHead = new HeroNode(0, "", "");
                //遍历原来的链表
                //每遍历一个节点，就将其取出，兵放在新的链表reversehead的最前端
                while (cur != null) {
                    next = cur.next;//先暂时保存当前的下一个节点，因为后面需要使用
                    cur.next=reverseHead.next;//将cur的下一个节点指向性的链表的最前端
                    reverseHead.next=cur;//将cur连接到新的链表上
                    cur=next;//让cur后移

                }
                //将head.next指向reversehead.next实现单链表的反转
                head.next=reverseHead.next;
            }
            //使用栈的数据结构，将各个节点压入栈中，然后利用栈先进后出的特点，就实现逆向打印
            public static void reversePoint(HeroNode head){
                if (head.next==null){
                    return ;
                }
                Stack<HeroNode> stack=new Stack<HeroNode>();
                HeroNode cur= head.next;
                //将链表所有节点压入栈
                while (cur!=null){
                    stack.push(cur);
                    cur=cur.next;//cur后移，就可以亚里亚下一个节点
                }
                //将栈中的节点进行打印pop出栈
                while (stack.size()>0){
                    System.out.println(stack.pop());
                }
            }
        }





