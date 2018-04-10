package com.tongyuan.testmp1.service;

import com.tongyuan.testmp1.dao.OrderDetailMapper;
import com.tongyuan.testmp1.dao.ViewMapper;
import com.tongyuan.testmp1.entity.Stuinfo;
import com.tongyuan.testmp1.entity.Userr;
import com.tongyuan.testmp1.helper.PageDataResult;
import com.tongyuan.testmp1.viewModel.StuTeacherView;
import com.tongyuan.testmp1.viewModel.StudentView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zhangcy on 2018/2/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserrServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ViewMapper viewMapper;
    @Autowired
    private StuinfoService stuinfoService;

    @Test
    public void queryUser() throws Exception {
        Userr userr = userService.selectById(1l);
        System.out.println(userr.getAge());
    }


    @Test
    public void testUpdate() throws Exception{
        orderDetailMapper.decreaseGoodsId();
    }

    @Test
    public void trans() throws Exception{
        userService.testTrans();
    }

    @Test
    public void testStu() throws Exception{
        List<StudentView> list = viewMapper.selectStudentByTeacherJobNumber(new Integer(1234).toString());
        System.out.println(list.size());
    }

    @Test
    public void testVague() throws Exception{
        List<StuTeacherView> list = viewMapper.selectStuTeaByDept("一级","二级","三");
        System.out.println(list.size());
        List<StuTeacherView> list1 = viewMapper.selectStuTeaByDept("一级","二级",null);
        System.out.println(list1.size());
    }

    @Test
    public void testVague2() throws Exception{
        PageDataResult<Stuinfo> list = stuinfoService.selectVagueStuPage(1,10,"三");
        System.out.println(list.getList().size());
        PageDataResult<Stuinfo> list1 = stuinfoService.selectVagueStuPage(1,10,"dent");
        System.out.println(list1.getList().size());
        PageDataResult<Stuinfo> list2 = stuinfoService.selectVagueStuPage(1,10,null);
        System.out.println(list2.getList().size());
    }

    @Test
    public void testDelete() throws Exception{
        System.out.println(stuinfoService.deleteById(3));
        System.out.println(stuinfoService.deleteById(3));
    }
}