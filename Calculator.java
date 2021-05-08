package numberConstruct;
public class Calculator {
    public static void main(String[] args) {
        //根据前面思路，完成表达式的运算
        String expression = "30+2*5-6";
        //创建两个栈一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描的到的字符char保存到ch中
        String keepNum = "";//用于拼接多位数
        //开始while循环的扫描expresssion
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应处理
            if (operStack.isOper(ch)) {//判断是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果有操作符就进行比较
                    // 如果当前操作符优先级小于或者等于栈中操作符,
                    // 就需要从数栈pop两个数，栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结构入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前运算符优先级大于栈中的运算符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            } else {
                //扫描到数字，则直接入数栈
                //numStack.push(ch - 48);//减48是因为字符与十进制的数字不一样见ASCII码
                //分析思路
                //1.当前处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2.在处理数，需要expression的表达式Index后看一位，如果是数就扫描，如果是符号就入栈
                //3因此我们需要定义一个字符串，用于拼接
                //处理多位数
                keepNum += ch;
                //如果ch已经是expression
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字就继续扫描如果是运算符则入栈
                    if (numStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要！！！！keepNum清空
                        keepNum = "";
                    }

                }
            }
                //让index+1，并判断时候扫描到expression最后
                index++;
                if (index >= expression.length()) {
                    break;

            }
        }
            //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应和符号，并运行
            while (true) {
                //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
                if (operStack.isEmpty()) {
                    break;
                }
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper=operStack.pop();
                res = numStack.cal(num1, num2, oper);
                numStack.push(res);//入栈
            }
//// 将数栈的最后数，pop出，就是结果
            res=numStack.pop();
            System.out.printf("表达式 %s =%d", expression, res);

    }
}

    //创建一个栈
    class ArrayStack2 {
        private int maxsize;//栈的大小
        private int[] stack;//数组，数组模拟栈，数据存放在数组
        private int top = -1;//top表示栈顶，初始化为-1


        //增加一个方法，可以返回当前栈顶的值但是不是真正的pop
        public int peek() {
            return stack[top];
        }

        public ArrayStack2(int maxsize) {
            this.maxsize = maxsize;
            stack = new int[this.maxsize];//初始化栈的数据
        }

        //栈满
        public boolean isfull() {
            return top == maxsize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        //入栈-push
        public void push(int value) {
            if (isfull()) {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
        }

        //出栈
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈空没有数据");

            }
            int value = stack[top];
            top--;
            return value;
        }

        //现实栈的情况[遍历栈]，遍历时，需要从栈顶开始现实数据
        public void list() {
            if (isEmpty()) {
                System.out.println("栈空，没有数据~~");

            }
            //需要从栈顶显示数据
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d\n", i, stack[i]);
            }
        }

        //返回运算符的优先级，优先级是程序员决定，优先使用数字表示
        //数字越大。优先级越高
        public int priority(int oper) {
            if (oper == '*' || oper == '/') {
                return 1;
            } else if (oper == '+' || oper == '-') {
                return 0;
            } else {
                return -1;//假定目前表达式有+ - * /
            }

        }

        //判断是不是一个运算符
        public boolean isOper(char val) {
            return val == '+' || val == '-' || val == '*' || val == '/';
        }

        //计算方法
        public int cal(int num1, int num2, int oper) {
            int res = 0;//res用于存放计算的结构
            switch (oper) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2 / num1;
                    break;
                default:
                    break;
            }
            return res;
        }
    }
