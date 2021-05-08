package numberConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明 1+((2+3)×4)-5 =>转成1 23 +4 ×+ 5 -
        //2.因为直接对string进行操作不方便，因此想将“1+((2+3)x4)-5=>的重罪表达式对应的list”
        //即"1+((2+3)×4)-5" =>ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的List =>后缀表达式对应的List
        //ArrayList [1,+,(,(,2,+,3,)*.4,),-,5] =》ArrayList [1,2,3,+,4，*，+,5,-]

        String expression="1+((2+3)*4)-5";
        List<String>list1 = toInfixEpressionList(expression);
        System.out.println(list1);//[1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]
        List<String> list2 = parseSuffixExpressionList(list1);
        System.out.println("对应的后缀表达式list2"+list2);//list2[1, 2, 3, +, 4, *, +, 5, -]
        System.out.println(calculate(list2));
        //先定义一个逆波兰表达式
        //(3+4)*5-6==>3 4 + 5 * 6 -
        //为了说明方便，逆波兰表达式的数字和符号用空格隔开
        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";
        //1.先将3 4 + 5 * 6 -放到Arraylist中
        //2.将Arraylist传递一个方法，遍历ArrayList配合栈运算
        List<String> list=getListString(suffixExpression);
        System.out.println("rpnlist"+list);
        int res=calculate(list);
        System.out.println("运算的结果是="+res);
    }
    public static List<String>parseSuffixExpressionList(List<String>ls){
        Stack<String> s1 = new Stack<String>();//符号栈
        //因此比较麻烦,这里我们就不用Stack<String>直接使用List<String> s2

        List <String> s2 = new ArrayList<String>();//存储中间结果栈s2
        //遍历ls
        for (String item : ls){
            //如果是一个数，加入s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                //如果是右括号"")”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                    while (!s1.peek().equals("(")){
                        s2.add(s1.pop());
                }
                    s1.pop();//消除小括号
            }else{
                //当item的优先级小于或者等于栈顶的运算符，将s1的运算符并加入到s2中，再次（4.1）与s1新的栈顶运算符相比较
                //问题缺少一个比较高点的
                while (s1.size()!=0&& Operation.getvalue(s1.peek())>=Operation.getvalue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
            //还需要将item压入栈

        }
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放带list
    }


    //方法：将中缀表达式转成对应的list
    public static List<String> toInfixEpressionList(String s){
        //定义一个List,存放中缀表达式对应的内容
        List<String> ls=new ArrayList<String>();
        int i=0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对于多位数的拼接
        char c;//每遍历到一个数字，就放入c
        do{
            //如果c是一个非数字，需要加入到ls
            if ((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                    ls.add(""+c);
                    i++;
            }else {
                str="";//想将str置成""‘0’[48]->‘9’[57]
                while (i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str+=c;//拼接
                    i++;
                }
               ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }
//将一个逆波兰表达式，一次将数据和运算符放在ArrayList中
public static List<String> getListString(String suffixExpression) {
        //将suffixEpression 分割
     String[] split = suffixExpression.split(" ");
     List<String> list=new ArrayList<String>();
     for (String ele: split){
        list.add(ele);
     }
    return list;

 }

//完成对逆波兰表达式的运算
    /*
    1)从左至右扫描,将3和4压入堆栈;
2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈;
3)将5入栈;
4)接下来是×运算符,因此弹出5和7，计算出7×5=35，将35入栈
5)将6入栈;
6)最后是-运算符，计算出35-6的值,即29，由此得出最终结果

     */
public static int calculate(List<String> ls) {
    // 创建给栈, 只需要一个栈即可
    Stack<String> stack = new Stack<String>();
    // 遍历 ls
    for (String item : ls) {
        // 这里使用正则表达式来取出数
        if (item.matches("\\d+")) { // 匹配的是多位数
            // 入栈
            stack.push(item);
        } else {
            // pop出两个数，并运算， 再入栈
            int num2 = Integer.parseInt(stack.pop());
            int num1 = Integer.parseInt(stack.pop());
            int res = 0;
            if (item.equals("+")) {
                res = num1 + num2;
            } else if (item.equals("-")) {
                res = num1 - num2;
            } else if (item.equals("*")) {
                res = num1 * num2;
            } else if (item.equals("/")) {
                res = num1 / num2;
            } else {
                throw new RuntimeException("运算符有误");
            }
            //把res 入栈
            stack.push("" + res);
        }

    }
    //最后留在stack中的数据是运算结果
    return Integer.parseInt(stack.pop());

}
}
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回对应方法
    public  static  int getvalue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("运算符不存在"+operation);
                break;
        }
        return result;
    }

}
