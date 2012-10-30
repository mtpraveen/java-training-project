package patterns.Events;

import patterns.models.Engine;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 7:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class EngineEvent extends EventObject {
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public EngineEvent(Object source, Engine engine) {
        super(source);
        this.engine = engine;
    }
}
