package com.calclab.hablar.dock.client;

/**
 * Configures the Dock container
 */
public class DockConfig {
    public final String leftType;
    public final int leftSize;
    public final String rightType;
    public final int rightSize;

    public DockConfig(String leftType, int leftSize, String rightType, int rightSize) {
	this.leftType = leftType;
	this.leftSize = leftSize;
	this.rightType = rightType;
	this.rightSize = rightSize;
    }
}
