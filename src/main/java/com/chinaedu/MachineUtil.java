package com.chinaedu;

import com.google.gson.Gson;
import com.util.JsonResult;

import javax.swing.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class MachineUtil {


    public static String MACHINE_PASSWORD="12345678";
    public static String MACHINE_IP  = "192.168.1.1003";


    public static JsonResult createPersonnel(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入人员编号:建议用姓名拼音");
        String id = scanner.next();
        System.out.println("请输入人员姓名:");
        String name = scanner.next();
        LinkedHashMap<String,Object> jsonObject = new LinkedHashMap<String, Object>();
        jsonObject.put("id",id);
        jsonObject.put("name",name);


        Gson gson = new Gson();
        String param = "pass="+MACHINE_PASSWORD+"&person="+gson.toJson(jsonObject);
        String url = "http://"+MACHINE_IP+":8090/person/create";
        String returnStr = null;
        JsonResult jsonResult = null;
        try {
            returnStr = ConnectMJUtil.connectFaceMachine(url,param,ConnectMJUtil.REQ_POST);
            jsonResult = gson.fromJson(returnStr,JsonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static JsonResult deviceReset(){
        Gson gson = new Gson();
        String param = "pass="+MACHINE_PASSWORD;
        String url = "http://"+MACHINE_IP+":8090/device/reset";
        String returnStr = null;
        JsonResult jsonResult = null;
        try {
            returnStr = ConnectMJUtil.connectFaceMachine(url,param,ConnectMJUtil.REQ_POST);

            jsonResult = gson.fromJson(returnStr,JsonResult.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }


    public static JsonResult restartDevice(){
         Gson gson = new Gson();
        String param = "pass="+MACHINE_PASSWORD;
        String url = "http://"+MACHINE_IP+":8090/restartDevice";
        String returnStr = null;
        JsonResult jsonResult = null;
        try {
            returnStr = ConnectMJUtil.connectFaceMachine(url,param,ConnectMJUtil.REQ_POST);

            jsonResult = gson.fromJson(returnStr,JsonResult.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    public static JsonResult  setConfig(){

        LinkedHashMap<String,Object> jsonObject = new LinkedHashMap<String, Object>();
        jsonObject.put("comModContent","hello");
        jsonObject.put("comModType",100);
        jsonObject.put("companyName","弘成科技发展有限公司");
        jsonObject.put("delayTimeForCloseDoor",500);
        jsonObject.put("displayModContent","{name}欢迎你");
        jsonObject.put("displayModType","100");
        jsonObject.put("identifyDistance","1");
        jsonObject.put("identifyScores","80");
        jsonObject.put("multiplayerDetection","1");
        jsonObject.put("recRank",2); //// Int，识别等级- 等 级1：不开启活体识别;等级2：开启单目活体识别;等级3：开启双目 活体识别(红外)，识别距离最远为1.5米
        jsonObject.put("recStrangerTimesThreshold",3);// Int，陌生人判定
        jsonObject.put("recStrangerType",2); // Int，陌生人开关（是否进行陌生人识别）
        jsonObject.put("ttsModContent","欢迎{name}");
        jsonObject.put("ttsModStrangerContent","陌生人");
        jsonObject.put("ttsModStrangerType",100); // Int，陌生人语音播报模式
        jsonObject.put("ttsModType",100);//Int，语音播报模式
        jsonObject.put("wg","#WG{id}#");//Int，语音播报模式


        Gson gson = new Gson();
        String jsonStr =  gson.toJson(jsonObject);

        String param = "pass="+MACHINE_PASSWORD+"&config="+jsonStr;
        String url = "http://192.168.1.11:8090/setConfig";
        String returnStr = null;
        JsonResult jsonResult = null;
        try {
            returnStr = ConnectMJUtil.connectFaceMachine(url,param,ConnectMJUtil.REQ_POST);

             jsonResult = gson.fromJson(returnStr,JsonResult.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;

    }

    public static JsonResult createFace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入人员编号:必需是已存在的人员编号");
        String id = scanner.next();
        System.out.println("照片路径:");
      //  JFileChooser jFileChooser = new JFileChooser();
      //  int res =  jFileChooser.showOpenDialog(null);
       // if (res==JFileChooser.)
     //   System.out.println(res);
     //   File file =  jFileChooser.getSelectedFile();
        String filePath = scanner.next();

        LinkedHashMap<String,Object> jsonObject = new LinkedHashMap<String, Object>();
        JsonResult jsonResult = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[2048];
            int len =-1;
            while((len = fis.read(buf))!=-1){
                bos.write(buf,0,len);
            }
            //转换照片为base64编码
             String base64Photo = Base64.getEncoder().encodeToString(bos.toByteArray());
            System.out.println(base64Photo);

            base64Photo = URLEncoder.encode(base64Photo);



            Gson gson = new Gson();

            String url = "http://"+MACHINE_IP+":8090/face/create";
            String param   =" pass="+MACHINE_PASSWORD+"&personId="+id+"&faceId="+id+"&imgBase64="+base64Photo;
            String returnStr = null;

            try {
                returnStr = ConnectMJUtil.connectFaceMachine(url,param,ConnectMJUtil.REQ_POST);

                jsonResult = gson.fromJson(returnStr,JsonResult.class);

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("路径不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }


        return jsonResult;

    }
}
