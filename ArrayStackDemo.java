package numberConstruct;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下 ArrayStack是否正确
        //先创建一个ArrayStack对象
    ArrayStack stack=new ArrayStack(4);
    String key="";
    boolean loop=true;//控制是否退出菜单
        Scanner scanner=new Scanner(System.in);

        while (loop){
            System.out.println("show 表示显示栈");
            System.out.println("exit 退出程序");
            System.out.println("push 表示添加数据入栈");
            System.out.println(" pop  表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value =scanner.nextInt();
                    stack.push(value);
                    break;
                case  "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    System.out.println("程序退出");
                    break;
            }


        }
    }
}
//定义一个ArrayStack表示栈
class  ArrayStack{
  private  int maxsize;//栈的大小
  private int []stack;//数组，数组模拟栈，数据存放在数组
  private int top=-1;//top表示栈顶，初始化为-1


    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        stack=new int[this.maxsize];//初始化栈的数据
    }
 //栈满
    public boolean isfull(){
        return top==maxsize-1;
    }
    public  boolean isEmpty(){
        return top==-1;
    }
    //入栈-push
    public  void push( int value){
        if(isfull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw  new RuntimeException("栈空没有数据");

        }
        int value=stack[top];
        top--;
        return value;
    }
    //现实栈的情况[遍历栈]，遍历时，需要从栈顶开始现实数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据~~");

        }
        //需要从栈顶显示数据
        for (int i=top; i>=0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}

