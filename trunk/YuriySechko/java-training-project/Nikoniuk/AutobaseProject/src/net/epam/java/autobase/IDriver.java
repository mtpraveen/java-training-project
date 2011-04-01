package net.epam.java.autobase;

import java.util.ArrayList;

public interface IDriver {

    public Request createRepairRequest(int sid, Car car, String carState);

    public void markRaceCompleted(int sid, Request race, String carState);

    public ArrayList<Request> getRaceRequests(int sid);

    public ArrayList<Car> getAvailableCars(int sid);

    public Car getCarById(int sid, int id);

	Request getRaceRequestById(int sid, int id);

}
