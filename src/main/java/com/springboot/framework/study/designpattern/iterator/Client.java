package com.springboot.framework.study.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/6 19:21
 */
public class Client {
    public static void main(String[] args) {
        // 创建学院
        List<College> collegeList = new ArrayList<>();

        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();

        collegeList.add(computerCollege);
        collegeList.add(infoCollege);

        OutPutImpl outPut = new OutPutImpl(collegeList);
        outPut.printCollege();
    }
}
