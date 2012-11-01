package com.elise.klikrace;

import java.util.Date;


public class Score {
	
	private Race race;
	private long time;
	private Sum som;
	private Date momentPlayed;
	
	public Score(Race race, Sum som, long time) {
		this.race = race;
		this.som = som;
		this.time = time;
		this.momentPlayed = new Date();
		
	}

	public Race getRace() {
		return race;
	}

	public long getTime() {
		return time;
	}

	public Sum getSom() {
		return som;
	}

	public Date getMomentPlayed() {
		return momentPlayed;
	}
	
	
	
	

	
}
