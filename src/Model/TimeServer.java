package model;

public interface TimeServer {
	  public long currentTime();
	  public void add(Agent thing);
	  public void run(int duration); 
	}
