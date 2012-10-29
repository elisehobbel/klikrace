package com.elise.klikrace;

import java.util.ArrayList;
import java.util.HashSet;

public class RaceTrack {

	private ArrayList<Sum> sommen;
	private HashSet<RaceScore> raceScores; // TODO has no order, so no arraylsit
	
	public RaceTrack(ArrayList<Sum> sommen) {
		this.sommen = sommen;
		this.raceScores = new HashSet<RaceScore>();
	}

	public ArrayList<Sum> getSommen() {
		return sommen;
	}

	public HashSet<RaceScore> getRaceScores() {
		return raceScores;	
	}

	public RaceScore getLastRaceOf(Runner runner) {
		
		long highestTimeStamp = 0;
		RaceScore raceScoreValue = null;
		
		for(RaceScore raceScore: raceScores){
			long currentTimeStamp = raceScore.getTimeStamp();
			if(raceScore.getRunner() == runner &&  currentTimeStamp > highestTimeStamp){
				highestTimeStamp = currentTimeStamp;
				raceScoreValue = raceScore;
			}
		}
		
		if(highestTimeStamp == 0){
			throw new RuntimeException("no timestamp or runner found");
		}
		
		return raceScoreValue;
		
	}
	
}
