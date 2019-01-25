package com.tarena.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 生成metaurl和chunkurl
 * 在生成第一个队列即VideoCacheQueue之前创建出metaurl和chunkurl
 * 主义此metaurl和chunkurl不是cc中的是tes_server中的
 * @author Administrator
 *
 */
@Component("urlUtil")
public class UrlUtil {
	@Value("#{props.host}")
	private String host;
	@Value("#{props.port}")
	private String port;
	@Value("#{props.appname}")
	private String appname;
	
	private String metaurl;
	private String chunkurl;
	
	public String getMetaurl() {
		this.metaurl="http://"+host+":"+port+"/"+appname+"/video/uploadmeta";
		return metaurl;
	}
	public String getChunkurl() {
		this.chunkurl="http://"+host+":"+port+"/"+appname+"/video/chunk";
		return chunkurl;
	}
	
}
