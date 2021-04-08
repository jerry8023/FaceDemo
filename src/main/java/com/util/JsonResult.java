package com.util;

import java.util.List;

public class JsonResult {

	private String status;
	private String  msg;
	private String  dataId;//在门禁系统中的编号
	private Object data;

	private int result;//  接口是否调通，1成功，0失败
	private boolean success;//  操作是否成功，成功为true，失败为false
	private String code ;//String(16) 返回码，对照表见附录说明


	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	@Override
	public String toString() {
		return "JsonResult [status=" + status + ", msg=" + msg + ", dataId="
				+ dataId + ", data=" + data + ", result=" + result
				+ ", success=" + success + ", code=" + code + "]\r\n";
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}



}
