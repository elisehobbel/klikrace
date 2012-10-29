package com.elise.klikrace;

import java.util.Date;


public class Score {
	//this is a struct
	
	private RaceTrack race;
	private long time;
	private Sum som;
	private Date momentPlayed;
	
	public Score(RaceTrack race, Sum som, long time) {
		this.race = race;
		this.som = som;
		this.time = time;
		this.momentPlayed = new Date();
		
	}

	public RaceTrack getRace() {
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
