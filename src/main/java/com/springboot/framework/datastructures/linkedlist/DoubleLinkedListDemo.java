package com.springboot.framework.datastructures.linkedlist;

/**
 * 双向链表
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/24 11:30
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //加入
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);

        //显示
        doubleLinkedList.list();

        //测试修改
        HeroNode2 hero5 = new HeroNode2(2, "5555", "55555");
        doubleLinkedList.update(hero5);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        //测试删除
        System.out.println();
        doubleLinkedList.del(3);
        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
    }
}

/**
 * 创建一个双向链表
 */
class DoubleLinkedList {
    /**
     * 先初始化一个头节点，头节点不要动，不存放具体的数据
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点到双向链表
     * 第一种方式，当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next 指向 新的节点
     */
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        //将最后新的节点的pre 指向 这个节点
        heroNode.pre = temp;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * （如果有这个排名，则添加失败，并给出提示）
     */
    public void addByOrder(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找到temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改。
     * 说明
     * 1.根据newHeroNode的no来修改
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        //遍历链表，找到最后
        while (true) {
            //说明temp已经在链表的最后
            if (temp == null) {
                System.out.printf("要删除的%d 节点不存在\n", no);
                break;
            }
            //说明编号存在
            if (temp.no == no) {
                temp.pre.next = temp.next;
                //判断要删除的节点是否为最后一个节点，防止空指针异常
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
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
        HeroNode2 temp = head;
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
 * HeroNode2，每个HeroNode2 对象就是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点，默认为null
     */
    public HeroNode2 next;
    /**
     * 指向上一个节点，默认为null
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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
