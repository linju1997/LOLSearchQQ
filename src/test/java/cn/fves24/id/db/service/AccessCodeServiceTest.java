package cn.fves24.id.db.service;

import cn.fves24.id.entity.model.AccessCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: fves
 * @Date: 2018-8-5 21:41
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccessCodeServiceTest  {

    @Autowired
    AccessCodeService accessCodeService;

    @Test
    public void add() {
        AccessCode accessCode = new AccessCode();
        accessCode.setCode("code");
        accessCode.setTimes(99999);
        accessCode.setModify(new Date());
        accessCode.setNote("test");
        System.out.println(accessCodeService.add(accessCode));
        System.out.println(accessCodeService.add(accessCode));
    }
    @Test
    public void adds(){
        List<AccessCode> accessCodes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            AccessCode accessCode = new AccessCode();
            accessCode.setCode("sfdsd"+i);
            accessCode.setTimes(i);
            accessCodes.add(accessCode);
        }
        System.out.println(accessCodeService.add(accessCodes));
    }
    @Test
    public void remainTimes() {
        System.out.println(accessCodeService.remainTimes("ALRTJ2B4"));
        System.out.println(accessCodeService.remainTimes("ALRTJ2B1"));
    }

    @Test
    public void existByCode() {
        System.out.println(accessCodeService.existsByCode("ALRTJ2B4"));
        System.out.println(accessCodeService.existsByCode("ALRTJ2B41"));
    }

    @Test
    public void delete(){
        System.out.println(accessCodeService.delete("ALRTJ2B4"));
        System.out.println(accessCodeService.delete("ALRTJ2B41"));
    }
    @Test
    public void deletes(){
        List<String> codes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            codes.add("sfdsd" + i);
        }

        System.out.println(accessCodeService.delete(codes));
    }
    @Test
    public void update(){
        AccessCode accessCode = new AccessCode();
        accessCode.setCode("code");  //code 存在
        accessCode.setTimes(1111);
        accessCode.setModify(new Date());
        accessCode.setNote("testUpdate1");
        System.out.println(accessCodeService.update(accessCode));

        accessCode.setCode("code1");  //code1不存在
        accessCode.setTimes(2222);
        accessCode.setModify(new Date());
        accessCode.setNote("testUpdate2");
        System.out.println(accessCodeService.update(accessCode));
    }

    @Test
    public void reduceTimes(){
        System.out.println(accessCodeService.reduceTimes("code"));
        System.out.println(accessCodeService.reduceTimes("code1"));
    }

    @Test
    public void addTimes(){
        System.out.println(accessCodeService.addTimes("code", 90));
        System.out.println(accessCodeService.addTimes("code1", 90));
    }
}