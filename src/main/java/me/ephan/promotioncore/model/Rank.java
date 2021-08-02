package me.ephan.promotioncore.model;

public class Rank {

	private final int order;

	private final String name;

	private double price;

	private long time;

	private int playerKills;

	private int mobKills;

	public Rank(final int order, final String name) {
		this.order = order;
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public long getTime() {
		return time;
	}

	public void setTime(final long time) {
		this.time = time;
	}

	public int getPlayerKills() {
		return playerKills;
	}

	public void setPlayerKills(final int playerKills) {
		this.playerKills = playerKills;
	}

	public int getMobKills() {
		return mobKills;
	}

	public void setMobKills(final int mobKills) {
		this.mobKills = mobKills;
	}
}
