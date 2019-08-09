package com.springboot.framework.study.jvm.classloader;

/**
 * 对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcom.springboot.framework.study.jvm.classloader.MyParent4，
 * 这种形式。动态生成的类型，其父类型就是Object
 * <p>
 * 对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型。
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/9 17:41
 */
public class MyTest4 {
    public static void main(String[] args) {
//        MyParent4 myParent4 = new MyParent4();

        MyParent4[] myParent4 = new MyParent4[1];
        System.out.println(myParent4.getClass());

        MyParent4[][] myParent4s1 = new MyParent4[1][1];
        System.out.println(myParent4s1.getClass());

        System.out.println(myParent4.getClass().getSuperclass());
        System.out.println(myParent4s1.getClass().getSuperclass());

//        System.out.println(myParent4s1.getClass().getClassLoader());
        System.out.println("==========");

        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent4 static block");
    }
}
