package org.diylc.common;

public enum HorizontalAlignment {
	LEFT, CENTER, RIGHT;
	
	public String toString() {
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
}
