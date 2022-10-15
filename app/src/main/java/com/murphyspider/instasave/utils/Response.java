package com.murphyspider.instasave.utils;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("id")
	private Integer id;

	@SerializedName("completed")
	private Boolean completed;

	@SerializedName("title")
	private String title;

	@SerializedName("userId")
	private Integer userId;
}