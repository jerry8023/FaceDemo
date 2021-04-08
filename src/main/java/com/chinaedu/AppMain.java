package com.chinaedu;

import com.util.JsonResult;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class AppMain {




    public static void main(String[] args) {




        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请选择要执行操作");
            System.out.println("0 设置设备基本信息");
            System.out.println("1 重启设备");
            System.out.println("2 重置设备");
            System.out.println("3 创建人员");
            System.out.println("4 照片注册（base64）");
            String userInput = scanner.next();
            JsonResult jsonResult  =null;
            if("0".equals(userInput)){
                jsonResult  =  MachineUtil.setConfig();
            }else             if("1".equals(userInput)){
                 jsonResult  =  MachineUtil.restartDevice();
            }else if ("2".equals(userInput)){
                 jsonResult  =  MachineUtil.deviceReset();
            }else if ("3".equals(userInput)){
                 jsonResult  =  MachineUtil.createPersonnel();

            }else if ("4".equals(userInput)){
                jsonResult  =  MachineUtil.createFace();

            }else {
                System.out.println("输入错误，请重新输入");
            }
            System.out.println("人脸识别设备返回结果:"+jsonResult);

        }








    }
}
