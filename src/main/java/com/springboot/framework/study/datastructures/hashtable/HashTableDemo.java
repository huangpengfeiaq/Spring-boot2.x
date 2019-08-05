package com.springboot.framework.study.datastructures.hashtable;

/**
 * 哈希表
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/28 10:53
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Emp emp1 = new Emp(1, "Tom");
        Emp emp2 = new Emp(2, "Jack");
        Emp emp8 = new Emp(8, "Smith");
        hashTable.add(emp1);
        hashTable.add(emp2);
        hashTable.add(emp8);
        hashTable.list();
        System.out.println(hashTable.findById(9));
    }

}

/**
 * 创建HashTable 管理多条链表
 */
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTable(int size) {
        // 初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
        // ？留一个坑，这时不要忘记分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     */
    public void add(Emp emp) {
        // 根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListArrayNo = hashFund(emp.id);
        // 将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListArrayNo].add(emp);
    }

    /**
     * 遍历所有的链表，遍历HashTable
     */
    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据id查找雇员
     */
    public Emp findById(int id) {
        // 根据员工的id，得到该员工应当到哪条链表查找
        int empLinkedListArrayNo = hashFund(id);
        // 将到对应的链表中查找 emp
        return empLinkedListArray[empLinkedListArrayNo].findById(id);
    }

    /**
     * 编写散列函数，使用一个简单取模法
     */
    private int hashFund(int id) {
        return id % size;
    }
}

/**
 * 表示一个雇员
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 创建EmpLinkedList，表示链表
 */
class EmpLinkedList {
    /**
     * 头指针，执行第一个Emp，因此我们这个链表的head 是指向第一个Emp
     */
    private Emp head;

    /**
     * 添加雇员到链表
     * 说明
     * 1.假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
     * 因此我们将该雇员直接加入到本链表的最后即可
     */
    public void add(Emp emp) {
        // 如果是第一个
        if (head == null) {
            head = emp;
            return;
        }
        //否则
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        //否则
        System.out.print("第 " + (no + 1) + " 链表为 ");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     */
    public Emp findById(int id) {
        if (head == null) {
            return null;
        }
        //否则
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                return curEmp;
            }
            if (curEmp.next == null) {
                return null;
            }
            curEmp = curEmp.next;
        }
    }
}