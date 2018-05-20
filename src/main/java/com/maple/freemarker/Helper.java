package com.maple.freemarker;

import java.util.Random;

public class Helper {

	private Random random = new Random();

	public String getSerialVersionUID() {
		StringBuilder builder = new StringBuilder();
		builder.append(random.nextInt(7) + 1);
		for (int i = 0; i < 18; i++) {
			builder.append(random.nextInt(10));
		}
		builder.append("L");
		return builder.toString();
	}
}
