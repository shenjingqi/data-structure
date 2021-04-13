package numberConstruct;

public class Josepfu {
    public static void main(String[] args) {
    //测试一把看看构建环形链表，和遍历是否
        CircleSingLinkedList circleSingLinkedList = new CircleSingLinkedList();
        circleSingLinkedList.addBoy(5);//加入五个小孩节点
        circleSingLinkedList.ShowBoy();
        //测试一把小孩出圈是否正确
        circleSingLinkedList.countBoy(1,2,5);

    }
}
//创建一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点默认null
    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
class CircleSingLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    //添加一个小孩节点，构建一个环形的链表
    public void addBoy(int nums) {
        //nums做数据校验
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助我们构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号节点，创造小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void ShowBoy() {
        //是否判断链表为空
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//将curBoy后移
        }
    }

    //根据用户的输入，计算出小孩的出圈顺序
        /*
        startNo表示第一个小孩开始数数
        countNum 表示数几下
        nums表示最初小孩有圈中

         */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建要给辅助指针helper，帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前,先让first和helper移动k - 1次
        for (int k = 0; k < startNo - 1; k++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和helper 指针同时的移动m- 1次，然后出圈
        //这里是一个循环操作直到只有一个节点
        while (true) {
            if (helper == first) {//说明只有一个节点
                break;
            }

            //让 first和helper指针同时的移动countNum - 1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向节点，就是要出圈小孩的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时first指向小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中小号编号%d \n", first.getNo());
    }
}