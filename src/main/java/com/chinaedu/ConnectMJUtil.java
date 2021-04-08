package com.chinaedu;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectMJUtil {
	public static String  connectFaceMachine(String sUrl,String bodyJsonStr,String requestMethod) throws Exception{
		System.out.println("请求的URL:"+sUrl.trim() +" 参数值:"+bodyJsonStr);

		URL url = new URL(sUrl);
		HttpURLConnection resumeConnection = (HttpURLConnection) url.openConnection();
		//设置请求头
		//   resumeConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		resumeConnection.setRequestProperty("Accept", "application/json");
		//请求的方法
		if (requestMethod.equals(REQ_POST)){
			resumeConnection.setDoOutput(true);
		}
		resumeConnection.setRequestMethod(requestMethod);

		if (bodyJsonStr!=null){
			OutputStream os = resumeConnection.getOutputStream();
	       /* OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
	        osw.write(bodyJsonStr); */
	       /* osw.flush();
	        osw.close(); */
			os.write(bodyJsonStr.trim().getBytes("utf-8"));
			os.flush();
			os.close(); //不要忘记关闭OutputStream
		}


		InputStream urlStream = resumeConnection.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		int len = -1;
		while( (len = urlStream.read(buf))!=-1){
			bos.write(buf, 0, len);
		}

		String jsonStr =  new String(bos.toByteArray(),"utf-8");
		System.out.println("解码后的字符串:"+jsonStr);
		bos.close();
		urlStream.close();


		return jsonStr;
	}

	/**
	 * 连接到门禁系统，交互数据
	 * @param sUrl
	 * @param bodyJsonStr
	 * @return
	 * @throws Exception
	 */
	public static String REQ_POST="POST";
	public static String REQ_GET="GET";
	public static String  connectMJ(String sUrl,String bodyJsonStr,String requestMethod) throws Exception{
		URL url = new URL(sUrl);
		HttpURLConnection resumeConnection = (HttpURLConnection) url.openConnection();
		//设置请求头
		resumeConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		resumeConnection.setRequestProperty("Accept", "application/json");
		// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true, 默认情况下是false;

		// Post 请求不能使用缓存
		resumeConnection.setUseCaches(false);
		resumeConnection.setDoOutput(true);
		resumeConnection.setRequestMethod(requestMethod);



		if (bodyJsonStr!=null){
			OutputStream os = resumeConnection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
			osw.write(bodyJsonStr);
			osw.flush();
			osw.close();
			os.close(); //不要忘记关闭OutputStream
		}


		InputStream urlStream = resumeConnection.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = -1;
		while( (len = urlStream.read(buf))!=-1){
			bos.write(buf, 0, len);
		}
     /*   BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlStream));
        String ss = null;
        String total = "";
        while ((ss = bufferedReader.readLine()) != null) {
            total += ss;
        }*/
		String jsonStr =  new String(bos.toByteArray(),"utf-8");
		System.out.println("解码后的字符串:"+jsonStr);
		bos.close();
		urlStream.close();


		return jsonStr;
	}
}
