package com.springboot.framework.study.datastructures.stack;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/25 12:17
 */
public class Calculator {
    public static void main(String[] args) {
        //根据思路，完成表达式的运算
        String expression = "20-5*7+13";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        //用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        //将每次扫描得到char保存到ch
        char ch = ' ';
        //开始while循环的扫描expression
        while (index < expression.length()) {
            //依次得到expression 的每一个字符
            ch = expression.substring(index, ++index).charAt(0);
            //判断ch 是什么，做相应的处理
            if (ArrayStack2.isOper(ch)) {
                //如果当前的符号栈不为空 并且 当前优先级小于等于栈顶优先级
                if (!operStack.isEmpty() && ArrayStack2.priority(ch) <= ArrayStack2.priority(operStack.getStack()[operStack.getTop()])) {
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    //把运算结果入数栈
                    numStack.push(ArrayStack2.cal(num1, num2, oper));
                }
                //把当前符号入符号栈
                operStack.push(ch);
            } else {
                // 定义一个数值变量，用于存放当前数值
                int value = ch - 48;
                // 循环获取，当index指向最后一位时（此时index已经指向下一个了），循环结束
                while (index < expression.length()) {
                    // 获取下一个字符
                    char temp = expression.substring(index, ++index).charAt(0);
                    // 判断是否为运算符，若为运算符，将index值回滚，并跳出循环
                    if (ArrayStack2.isOper(temp)) {
                        index--;
                        break;
                    }
                    // 否则，给value拼接数值
                    value = Integer.parseInt(value + "" + temp);
                }
                //如果是数，则直接入栈。将字符型转数值型
                numStack.push(value);
            }
        }
        numStack.list();
        operStack.list();

//        //以下为老师的思路：倒序计算，有严重问题（例如当式子中出现两个减号，就与预期不符）
//        //当表达式扫描完毕，就顺序的从 数栈和符号栈pop出相应的数和符号，并运算
//        while (true) {
//            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
//            if (operStack.isEmpty()) {
//                break;
//            }
//            num1 = numStack.pop();
//            num2 = numStack.pop();
//            oper = operStack.pop();
//            //把运算结果入数栈
//            numStack.push(ArrayStack2.cal(num1, num2, oper));
//        }
//        System.out.printf("表达式%s = %d\n", expression, numStack.pop());

        //以下为我的思路：正序计算
        //当表达式扫描完毕，就顺序的从 数栈和符号栈pop出相应的数和符号，并运算
        int[] numList = numStack.getStack();
        int[] operList = operStack.getStack();
        // 取出初始数值
        int value = numList[0];
        for (int i = 0; i <= operStack.getTop(); ) {
            oper = operList[i];
            num1 = numList[++i];
            // 把运算结果赋值给value
            value = ArrayStack2.cal(num1, value, oper);
        }
        System.out.printf("表达式%s = %d\n", expression, value);
    }
}

/**
 * 先创建一个栈
 * ArrayStack2需要扩展功能
 */
@Data
class ArrayStack2 {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组，数组模拟栈，数据就放在该数组中
     */
    private int[] stack;
    /**
     * 栈顶，初始化为-1
     */
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空~~");
        }
        return stack[top--];
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈已空~~");
            return;
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
     * 数字越大，则优先级越高
     */
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            //假定目前的表达式只有+，-，*，/
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     */
    public static boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    /**
     * 计算方法
     *
     * @param num1 栈顶
     * @param num2 栈顶后一个
     */
    public static int cal(int num1, int num2, int oper) {
        switch (oper) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                throw new RuntimeException("运算符有误");
        }
    }
}