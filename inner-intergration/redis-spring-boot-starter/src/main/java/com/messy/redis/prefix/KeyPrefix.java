package com.messy.redis.prefix;

public interface KeyPrefix {
		
	public int expireSeconds();
	
	public String getPrefix();
	
}
