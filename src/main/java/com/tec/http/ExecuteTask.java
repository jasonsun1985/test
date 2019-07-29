package com.tec.http;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

import com.alibaba.fastjson.JSONObject;

public class ExecuteTask extends RecursiveTask<Void> {

	@Override
	protected Void compute() {
		String str = "{remoteTest1:100,remoteTest2:600}";
		try {
			HttpClient.httpPost("http://172.17.161.179:8080/api/v1/zJvEwHzumy57SXcxCzWB/telemetry", JSONObject.parseObject(str));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
