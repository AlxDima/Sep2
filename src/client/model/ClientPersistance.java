package client.model;

import Data.User;
import client.model.Client;

public interface ClientPersistance {

   public User[] load();
   
   public boolean save(User user);
   
   public boolean save(User[] user);
   
   public boolean removeUser( User user);
   
}
