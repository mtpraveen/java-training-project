package patterns.controllers;

import patterns.Events.EngineEvent;
import patterns.Events.EventListeners.IEngineEventListener;
import patterns.Events.EventListeners.IGearEventListener;
import patterns.Events.GearEvent;
import patterns.controllers.interfaces.IGearController;
import patterns.models.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class EngineController implements IGearEventListener {
    private Engine engine;
    private IGearController gearController;

    private List<IEngineEventListener> eventListeners;

    public EngineController(Engine engine, IGearController gearController) {
        this.engine = engine;
        this.gearController = gearController;
        this.gearController.addEventListener(this);
        eventListeners = new ArrayList<IEngineEventListener>();
    }

    public int getPower(){
        return engine.getPower();
    }

    public void accelerate(){
        if(engine.getPower() < 5000){
            engine.setPower(engine.getPower() + 1250);
            fireAccelerate();
        }
        else gearController.increase();
    }

    public void decelerate(){
        if(engine.getPower() > 1250){
            engine.setPower(engine.getPower() - 1250);
            fireDecelerate();
        }
        else gearController.decrease();
    }

    public void stop(){
        engine.setPower(0);
        fireStop();
    }

    public void start(){
        engine.setPower(1250);
        fireStart();
    }

    //Events
    public void addEventListener(IEngineEventListener listener){
        if(!eventListeners.contains(listener))
            eventListeners.add(listener);
    }

    public void removeEventListener(IEngineEventListener listener){
        if(eventListeners.contains(listener))
            eventListeners.remove(listener);
    }

    private void fireAccelerate(){
        for(IEngineEventListener listener : eventListeners){
            listener.powerIncreased(new EngineEvent(this, engine));
        }
    }

    private void fireDecelerate(){
        for(IEngineEventListener listener : eventListeners){
            listener.powerDecreased(new EngineEvent(this, engine));
        }
    }

    private void fireStart(){
        for(IEngineEventListener listener : eventListeners){
            listener.powerStarted(new EngineEvent(this, engine));
        }
    }

    private void fireStop(){
        for(IEngineEventListener listener : eventListeners){
            listener.powerStopped(new EngineEvent(this, engine));
        }
    }

    @Override
    public void gearIncreased(GearEvent e) {
        engine.setPower(1250);
        fireDecelerate();
    }

    @Override
    public void gearDecreased(GearEvent e) {
        engine.setPower(5000);
        fireAccelerate();
    }

    @Override
    public void gearSetToBack(GearEvent e) {
        engine.setPower(1250);
    }
}
