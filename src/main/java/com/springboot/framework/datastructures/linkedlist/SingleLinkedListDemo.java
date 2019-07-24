package com.springboot.framework.datastructures.linkedlist;

import java.util.Stack;

/**
 * 单向链表（以水浒传英雄举例）
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/23 10:58
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
////        加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        //加入按照编号的顺序
        System.out.println();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示
        singleLinkedList.list();

        //测试修改
        System.out.println();
        HeroNode hero5 = new HeroNode(3, "5555", "55555");
        singleLinkedList.update(hero5);
        //显示
        singleLinkedList.list();

//        //测试删除
//        System.out.println();
//        singleLinkedList.del(2);
//        singleLinkedList.del(4);
//        //显示
//        singleLinkedList.list();

        //测试查找单链表中有效的个数
        System.out.println();
        System.out.println("有效的节点个数=" + getLength(singleLinkedList));

        //测试查找单链表中的倒数第k个节点
        System.out.println("倒数第k个节点=" + findLastIndexNode(singleLinkedList, 2));

        //测试反转单链表
        System.out.println();
        SingleLinkedList newSingleLinkedList = reverseList(singleLinkedList);
        //显示反转后的单链表
        System.out.println("反转单链表~~");
        newSingleLinkedList.list();

        //测试逆序打印单链表
        System.out.println();
        System.out.println("测试逆序打印单链表~~");
        reversePrint(singleLinkedList);
    }

    /**
     * 方法：获取到单链表的节点的个数（如果是带头节点的链表，需要不统计头节点）
     *
     * @param singleLinkedList 需要获取的单链表
     * @return 单链表的节点的个数
     */
    public static int getLength(SingleLinkedList singleLinkedList) {
        //先获取单链表的头节点
        //为符合命名规范，给head取个别名
        HeroNode cur = singleLinkedList.getHead();
        int length = 0;
        while (true) {
            if (cur.next == null) {
                return length;
            }
            length++;
            cur = cur.next;
        }
    }

    /**
     * 方法：查找单链表中的倒数第k个节点【新浪面试题】
     *
     * @param singleLinkedList 需要获取的单链表
     * @param k                要查找的倒数第k个节点
     * @return 节点对象
     */
    public static HeroNode findLastIndexNode(SingleLinkedList singleLinkedList, int k) {
        if (k <= 0) {
            throw new RuntimeException("传入参数有误");
        }
        //得到链表的总长度
        int length = getLength(singleLinkedList);
        //表示正数第index个节点
        int index = length - k + 1;
        if (index <= 0) {
            throw new RuntimeException("超出范围");
        }
        HeroNode cur = singleLinkedList.getHead();
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 方法：单链表反转【腾讯面试题，有点难度】
     * 我的思路如下：
     * 利用链表插入机制（特点：新生成了一个链表，也同时保留了原来的链表）
     *
     * @param singleLinkedList 需要反转的单链表
     * @return 反转后的链表
     */
    public static SingleLinkedList reverseList(SingleLinkedList singleLinkedList) {
        //创建一个新的链表，作为反转后的链表
        SingleLinkedList newSingleLinkedList = new SingleLinkedList();
        //创建一个新链表的头节点
        HeroNode reverseHead = newSingleLinkedList.getHead();
        //创建一个临时节点，用于存放新链表的第一个节点
        HeroNode temp;

        //创建一个原链表的临时节点变量。用于遍历
        HeroNode cur = singleLinkedList.getHead();
        while (cur.next != null) {
            cur = cur.next;
            //赋值到新节点
            //将遍历的节点的data域存入临时节点的data域
            temp = new HeroNode(cur.no, cur.name, cur.nickname);
            //临时节点的next域指向新头节点的下一个节点
            temp.next = reverseHead.next;
            //新头节点的下一个节点指向temp
            reverseHead.next = temp;
        }
        return newSingleLinkedList;
    }

    /**
     * 方法：单链表反转【腾讯面试题，有点难度】
     * 老师的思路如下：
     * 利用链表next域机制（特点：不再新生成了一个链表，直接替换了原来的链表）
     *
     * @param singleLinkedList 需要反转的单链表
     * @return 反转后的链表
     */
    public static SingleLinkedList reverseListForTeacher(SingleLinkedList singleLinkedList) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        HeroNode head = singleLinkedList.getHead();
        if (head.next == null || head.next.next == null) {
            return singleLinkedList;
        }
        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向当前节点[cur]的下一个节点
        HeroNode next = null;
        //创建一个新的链表，作为反转后的链表
        SingleLinkedList newSingleLinkedList = new SingleLinkedList();
        HeroNode reverseHead = newSingleLinkedList.getHead();

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        //动脑筋
        while (cur != null) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = cur.next;
            //将cur的下一个节点执行新的链表的最前端
            cur.next = reverseHead.next;
            //将cur 连接到
            reverseHead.next = cur;
            //新头节点的下一个节点指向temp
            cur = next;
        }
        //将head.next 指向 reverseHead.next，实现单链表反转
        head.next = reverseHead.next;
        return newSingleLinkedList;
    }

    /**
     * 方法：单链表反转打印【百度面试题】
     * 利用栈的特点
     *
     * @param singleLinkedList 单链表
     */
    public static void reversePrint(SingleLinkedList singleLinkedList) {
        HeroNode cur = singleLinkedList.getHead();
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        while (cur.next != null) {
            cur = cur.next;
            stack.push(cur);
        }
        //将栈中的节点打印
        while (stack.size() > 0) {
            //pop就是将栈顶的数据取出
            System.out.println(stack.pop());
        }
    }
}

/**
 * 定义SingleLinkedList 管理我们的英雄
 */
class SingleLinkedList {
    /**
     * 先初始化一个头节点，头节点不要动，不存放具体的数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 第一种方式，当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next 指向 新的节点
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * （如果有这个排名，则添加失败，并给出提示）
     */
    public void addByOrder(HeroNode heroNode) {
        //因为head节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找到temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;
        //遍历链表，找到最后
        while (true) {
            //说明temp已经在链表的最后
            if (temp.next == null) {
                break;
            }
            //位置找到，就在temp的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明编号存在
                flag = true;
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        if (flag) {
            //说明编号存在
            System.out.printf("准备插入的这个英雄的编号%d 已经存在了，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改。
     * 说明
     * 1.根据newHeroNode的no来修改
     */
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        //是否找到该节点
        boolean flag = false;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点的信息，根据no编号来删除。
     * 说明
     * 1.head 不能动，因此我们需要一个temp辅助节点找到待删除的前一个节点
     * 2.说明我们在比较时，是temp.next.no 和 需要删除的节点的no比较
     */
    public void del(int no) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //说明temp已经在链表的最后
            if (temp.next == null) {
                System.out.printf("要删除的%d 节点不存在\n", no);
                break;
            }
            //说明编号存在
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
    }

    /**
     * 显示链表[遍历]
     */
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

/**
 * 定义HeroNode，每个HeroNode 对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}