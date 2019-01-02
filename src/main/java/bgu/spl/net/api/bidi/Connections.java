package bgu.spl.net.api.bidi;

public interface Connections<T> {
    
    boolean send(int connectionId, T msg);

    void broadcast(T msg);

    void disconnect(int connectionId);
    
    boolean isLogedIn (int connectionId);
    
    boolean isInUserList (String userName);
    
    boolean insertToLogedIn (int connectionId, String userName);
    
    boolean insertToUserList (String userName);
    
    Costumer getCostumer (String userName);
    
    
}