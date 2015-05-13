package com.weixin.handler;

import com.weixin.message.ImageMessage;
import com.weixin.message.LinkMessage;
import com.weixin.message.LocationMessage;
import com.weixin.message.TextMessage;
import com.weixin.message.VideoMeaasge;
import com.weixin.message.VoiceMessage;

public interface IMessageListener {
	public abstract void onTextMessage(TextMessage textMessage);

	public abstract void onImageMessage(ImageMessage imageMessage);

	public abstract void onLinkMessage(LinkMessage linkMessage);

	public abstract void onLocationMessage(LocationMessage LocationMessage);

	public abstract void onVoiceMessage(VoiceMessage VoiceMessage);
	
	public abstract void onVideoMeaasge(VideoMeaasge videoMeaasge);

	public abstract void onErrorMsg(int i);
}
