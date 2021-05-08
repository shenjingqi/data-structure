package numberConstruct;

import java.util.Queue;
import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
//使用数组模拟队列所以一个ArrayQueue类
class ArrayQueue{
   private  int maxsize;//表示数组的最大容量
    private  int front;//队列头
    private  int rear;//队列尾
    private  int []arr; //该数组用于存放和数据，模拟队列

    //创建队列构造器
    public ArrayQueue(int arrmaxsize){
        maxsize=arrmaxsize;
        arr=new int[maxsize];
        rear=-1;//指向队列尾部，指向队列的尾的数据（即包含队列的最后一个数值）
        front=-1;//指向队列头部，分析出front是指向队列头的前一个位置
    }
    //判断队列是否满
    public boolean isFull(){
        return  rear==maxsize-1;
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
        rear++;//让rear后移
        arr[rear]=n;
    }
    //获取队列数据，出数列
    public  int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能获取");
        }
        front++;//让后移
        return arr[front];
    }
    //显示队列的所有数据
    public  void  showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        for (int i = 0; i <arr.length ; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front+1];
    }
}