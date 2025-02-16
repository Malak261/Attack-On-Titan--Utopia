package game.engine.exceptions;

public class InsufficientResourcesException extends GameActionException{
    private static final String MSG="Not enough resources, resources provided = ";
    private int resourcesProvided;

    public int getResourcesProvided() {
        return resourcesProvided;
    }
    public void setResourcesProvided(int resourcesProvided) {
        this.resourcesProvided = resourcesProvided;
    }
    public InsufficientResourcesException(int rescourcesProvided){
        super(MSG+rescourcesProvided);
    }
    public InsufficientResourcesException(String message,int rescourcesProvided){
        super(message);
        this.resourcesProvided=rescourcesProvided;

    }




}
