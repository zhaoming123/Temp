package com.weixin.handler;

import com.weixin.message.ImageMessage;
import com.weixin.message.LinkMessage;
import com.weixin.message.LocationMessage;
import com.weixin.message.TextMessage;
import com.weixin.message.VideoMeaasge;
import com.weixin.message.VoiceMessage;

public class MessageListener implements IMessageListener {
	public void onTextMessage(TextMessage textMessage){ }

	public void onImageMessage(ImageMessage imageMessage){ }

	public void onLinkMessage(LinkMessage linkMessage){ }

	public void onLocationMessage(LocationMessage locationMessage){ }

	public void onVoiceMessage(VoiceMessage voiceMessage){ }

	public void onVideoMeaasge(VideoMeaasge videoMeaasge){ }
	
	public void onErrorMsg(int i){ }
}
