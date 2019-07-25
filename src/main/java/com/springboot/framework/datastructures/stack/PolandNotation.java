package com.springboot.framework.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/25 18:51
 */
public class PolandNotation {
    public static void main(String[] args) {

        // 完成将一个中缀表达式转成后缀表达式的功能
        // 说明
        // 1. 1+((2+3)*4)-5 => 转成 1 2 3+ 4 * + 5 -
        // 2.因为直接对str 进行操作，不方便，因此 先将 "1+((2+3)*4)-5" => 中缀的表达式对应的List
        //   即 "1+((2+3)*4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        // 3.将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1 2 3+ 4 * + 5 -]

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionListString = toInfixExpressionListString(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionListString);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionListString);
        System.out.println("后缀表达式对应的List=" + suffixExpressionList);

        System.out.println("计算的结果是=" + calculate(suffixExpressionList));


        // 先定义一个逆波兰表达式
        // (30+4)*5-6 => 30 4 + 5 * 6 - => 164
        //  4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / + => 76
        // 说明：为了方便，逆波兰表达式 的数字和符号使用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路
        // 1.先将"3 4 + 5 * 6 -" => 放到ArrayList中
        // 2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("计算的结果是=" + calculate(rpnList));
    }

    /**
     * 方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
     * 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1 2 3+ 4 * + 5 -]
     */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 定义两个栈
        Stack<String> s1 = new Stack<>();
        // 说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        List<String> s2 = new ArrayList<>();

        // 遍历list
        for (String item : list) {
            //如果是一个数，就加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                // 如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                int peekPriority = -1;
                int itemPriority = priority(item);
                boolean loop = true;
                while (loop) {
                    if (!s1.empty()) {
                        peekPriority = priority(s1.peek());
                    }
                    if (s1.empty() || "(".equals(item) || itemPriority > peekPriority) {
                        s1.push(item);
                        loop = false;
                    } else {
                        s2.add(s1.pop());
                    }
                }
            }
        }
        // 将s1中剩余的运算符依次弹出并压入s2
        while (!s1.empty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将 中缀表达式转成对应的List
     */
    public static List<String> toInfixExpressionListString(String expression) {
        // 定义一个List，存放中缀表达式 对应的内容
        List<String> list = new ArrayList<>();
        // 这是一个指针，用于遍历中缀表达式字符串
        int index = 0;
        // 对多位数的拼接
        String str = "";
        // 每遍历到一个字符，就放入s
        String s;
        while (index < expression.length()) {
            s = expression.substring(index, ++index);
            //如果是一个数，就拼接到str
            if (s.matches("\\d+")) {
                str += s;
                //index已经指向最后一位，则直接追加到list
                if (index == expression.length()) {
                    list.add(str);
                }
            } else {
                //如果str存在，则先把str追加到list
                if (!"".equals(str)) {
                    list.add(str);
                    str = "";
                }
                //将符号追加到list
                list.add(s);
            }
        }
        return list;
    }

    /**
     * 将一个逆波兰表达式，一次将数据和运算符放入到ArrayList中
     */
    public static List<String> getListString(String suffixExpression) {
        // 将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     */
    public static int calculate(List<String> rpnList) {
        // 创建一个栈
        Stack<String> stack = new Stack<>();
        // 遍历rpnList
        for (String item : rpnList) {
            // 这里使用正则表达式来取出数，匹配多位数
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                // 入栈
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
     * 数字越大，则优先级越高
     */
    public static int priority(String oper) {
        if ("*".equals(oper) || "/".equals(oper)) {
            return 1;
        } else if ("+".equals(oper) || "-".equals(oper)) {
            return 0;
        } else {
            //假定目前的表达式只有+，-，*，/
            return -1;
        }
    }
}
