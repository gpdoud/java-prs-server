package com.maxtrain.utility;

public class JsonResponse {
	public final static JsonResponse Ok = new JsonResponse("Ok", "The operation was successful!");
	public final static JsonResponse Fail = new JsonResponse("Fail", "The operation failed!");
	
	public String Result;
	public String Message;
	public Object Data;
	
	public JsonResponse(String Result, String Message, Object Data) {
		this.Result = Result;
		this.Message = Message;
		this.Data = Data;
	}
	public JsonResponse(String Result, String Message) {
		this(Result, Message, null);
	}

}
