package com.em;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.api.ChatMessageAPI;
import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.ChatGroupBody;
import com.easemob.server.example.comm.body.ChatRoomBody;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.ModifyNicknameBody;
import com.easemob.server.example.comm.body.ResetPasswordBody;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xiaoyuan.common.util.DesUtils;

/**
 * 获取环信账号工具类
 * */
public class EMutil {
	private static final String NAME_KEY="username";
	private static final String PWD_KEY="password";

	public static String getEMuserName(String userAccount){
	  String emUserName="";
		try {
			emUserName=DesUtils.encrypt(NAME_KEY, userAccount);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return emUserName;
	}
	public static String getEMPassword(String pwd){
		 String emPassword=""; 
		try {
			emPassword= DesUtils.encrypt(PWD_KEY, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return emPassword;
	}
	
	public static String getUserAccount(String emUserName){
		String userAccount="";
		try {
			userAccount=DesUtils.decrypt(NAME_KEY, emUserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userAccount;
	}
	public static boolean createEMUser(String account,String password,String name){
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		String eMuserName = getEMuserName(account);
		String emPassword = getEMPassword(password);
		BodyWrapper userBody = new IMUserBody(eMuserName, emPassword, name);
		ResponseWrapper createNewIMUserResponse =(ResponseWrapper) user.createNewIMUserBatch(userBody);
		Integer responseStatus = createNewIMUserResponse.getResponseStatus();
		
	    if(responseStatus!=null&&responseStatus==200||responseStatus==400){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	public static boolean modifyNickName(String account,String nicName){
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		
		BodyWrapper userBody =new ModifyNicknameBody(nicName);
		String eMuserName = getEMuserName(account);
		ResponseWrapper modifyUserResponse =(ResponseWrapper) user.modifyIMUserNickNameWithAdminToken(eMuserName, userBody);
		Integer responseStatus = modifyUserResponse.getResponseStatus();
		if(responseStatus!=null&&responseStatus==200){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 创建聊天室
	 * @parameter 
	 * 聊天室名称，聊天室描述，聊天室最多人数，聊天室创建者，聊天室成员
	 * @return 聊天室ID
	 * */
	public static String createChatRoom(String roomName,String roomDesc,long maxUser,String ownerAccount,String [] members){
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
		String eMuserName = getEMuserName(ownerAccount);
		BodyWrapper body= new ChatRoomBody(roomName, roomDesc, maxUser, eMuserName, members);
		ResponseWrapper response =(ResponseWrapper) chatroom.createChatRoom(body);
		Integer status = response.getResponseStatus();
		if(status!=null&&status==200){
		Object responseBody = response.getResponseBody();
		ObjectNode note =(ObjectNode)responseBody;
		JsonNode jsonNode = note.get("data").get("id");
		return jsonNode.asText();
		}
		return null;
	}
	
	/**
	 * 创建群
	 * @return 群ID
	 * */
	public static String createChatGroup(String groupName,String desc,boolean isPublic,Long maxUsers,boolean approval,String owner,String[] members){
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		ChatGroupAPI chatGroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		BodyWrapper body = new ChatGroupBody(groupName, desc, isPublic, maxUsers, approval, owner, members);
		
		ResponseWrapper response = (ResponseWrapper) chatGroup.createChatGroup(body);
		Integer status = response.getResponseStatus();
		
		if(status!=null&&status==200){
			Object responseBody = response.getResponseBody();
			ObjectNode note =(ObjectNode)responseBody;
			JsonNode jsonNode = note.get("data").get("groupid");
			return jsonNode.asText();
		}else{
			return null;
		}
	}
	/**
	 * 获取好友列表
	 * */
	public static List<String> getFriends(String account){
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResponseWrapper response = (ResponseWrapper)user.getFriends(getEMuserName(account));
		Integer status = response.getResponseStatus();
		List<String> emUserNames = new ArrayList<String>();
		if(status!=null&&status==200){
			ObjectNode note =(ObjectNode)response.getResponseBody();
			JsonNode jsonNode = note.get("data");
			if(jsonNode.isArray()){
				for(JsonNode node:jsonNode){
					emUserNames.add(node.asText());
				}
			}
		}
		return emUserNames;
	}
	/**
	 * 获取好友列表
	 * */
	public static boolean resetPwd(String account,String password){
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ResetPasswordBody passwordBody = new ResetPasswordBody(getEMPassword(password));
		ResponseWrapper response = (ResponseWrapper) user.modifyIMUserPasswordWithAdminToken(getEMuserName(account), passwordBody);
		Integer status = response.getResponseStatus();
		if(status!=null&&status==200){
			return true;
		}
		return false;
	}
	/**
	 * 获取好友列表
	 * */
	public static ObjectNode getHistoryMsg(Long limit,String cursor,String query){
		
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
		query = URLEncoder.encode(query);
		ResponseWrapper exportChatMessages = (ResponseWrapper) chat.exportChatMessages(limit, cursor, query);
        Integer status = exportChatMessages.getResponseStatus();
        List<String> messages = exportChatMessages.getMessages();
        if(status!=null&&status==200){
        	 ObjectNode note =(ObjectNode)exportChatMessages.getResponseBody();
				return note;
		}else{
			return null;
		}
	}
	public static void main(String[] args) {
		String userAccount = getUserAccount("b53dd25f5fd74d27954ce96343e99fe8");
		System.out.println(userAccount);
	}
}
