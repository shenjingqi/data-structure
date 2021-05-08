package numberConstruct;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组来模拟环形队列");
        //测试环形队列有效数据为3
        CircleArray arrayQueue = new CircleArray(4);
        String key=" ";//接受用户输入
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while(loop){
            //输出一个菜单
            System.out.println("show 表示显示队列");
            System.out.println("exit 退出程序");
            System.out.println("push 表示添加数据到队列");
            System.out.println(" pop  表示从队列取出数据（出栈）");
            System.out.println("h表示查看队列的头部");
            System.out.println("请输入你的选择");
            key=scanner.next();
            switch (key){
                case "show":
                    arrayQueue.showQueue();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value =scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case  "pop":
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    System.out.println("程序退出");
                    break;
                case "h":
                    try {
                        int res= arrayQueue.headQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}
class CircleArray{
    private  int maxsize;//表示数组的最大容量
    //front变量的含义做一个调整:front 就指向队列的第一个元素，也就是说arr[front]
// Front的初始值=0
    private  int front;//队列头
    //rear变量的含义做一个调整:rear指向队列的最后一个元素的后一个位置。因为希望空出一个位置
    // rear 的初始值=0

    private  int rear;//队列尾
    private  int []arr; //该数组用于存放和数据，模拟队列

public CircleArray(int arrMaxsize) {
    maxsize = arrMaxsize;
    arr = new int[maxsize];
}
    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxsize==front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return  rear==front;
    }
    //添加数据到队列
    public  void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能添加数据");
            return;
        }
        //直接将数据加入
       arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear=(rear+1)%maxsize;

    }
    //获取队列数据，出数列
    public  int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能获取");
        }
      //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应是指向队列的第一个元素
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value=arr[front];
        front=(front+1)%maxsize;
        return value;
    }
    //显示队列的所有数据
    public  void  showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        //思路：front开始遍历，变量出有效数据的个数
        for (int i = front; i <front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxsize,arr[i%maxsize]);
        }
    }
    //求出当前队列有效个数
    public int size(){
       return (rear+maxsize-front)%maxsize;
       // 思路

        //         * 相信大家的思路都是rear-front 直接取到有效数值的个数,但是
        //         * 获取数组的有效数值,我们想一种情况,就是rear-front为负数,如果不知道这种情况是怎么发生的
        //         * 请看图,自己想象不断取值和存值 的过程中有没有可能会发生以上情况
        //         * 如果以上情况发生,我们取到的负数就不是实际有效数值的个数
        //         * 这个时候就应该给rear加上maxSize 让他多跑一圈再减去front即(rear+maxSize-front)
        //         * 此时的在和maxSize取模 就能得到我们要的实际有效参数的数值(利用取模达到保证出余数为正数即有效数据)
        //         *
        //         *
        //         *
        //————————————————
        //版权声明：本文为CSDN博主「临时抱不到佛脚」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/weixin_46705768/article/details/109125223
    }
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }

}
