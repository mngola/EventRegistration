/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.eventregistration.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 23 "../../../../../EventRegistration.ump"
// line 47 "../../../../../EventRegistration.ump"
@SuppressWarnings("unused")
public class RegistrationManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static RegistrationManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RegistrationManager Associations
  private List<Registration> registrations;
  private List<Participant> participantants;
  private List<Event> events;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private RegistrationManager()
  {
    registrations = new ArrayList<Registration>();
    participantants = new ArrayList<Participant>();
    events = new ArrayList<Event>();
  }

  public static RegistrationManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new RegistrationManager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Registration getRegistration(int index)
  {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations()
  {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations()
  {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registrations.indexOf(aRegistration);
    return index;
  }

  public Participant getParticipantant(int index)
  {
    Participant aParticipantant = participantants.get(index);
    return aParticipantant;
  }

  public List<Participant> getParticipantants()
  {
    List<Participant> newParticipantants = Collections.unmodifiableList(participantants);
    return newParticipantants;
  }

  public int numberOfParticipantants()
  {
    int number = participantants.size();
    return number;
  }

  public boolean hasParticipantants()
  {
    boolean has = participantants.size() > 0;
    return has;
  }

  public int indexOfParticipantant(Participant aParticipantant)
  {
    int index = participantants.indexOf(aParticipantant);
    return index;
  }

  public Event getEvent(int index)
  {
    Event aEvent = events.get(index);
    return aEvent;
  }

  public List<Event> getEvents()
  {
    List<Event> newEvents = Collections.unmodifiableList(events);
    return newEvents;
  }

  public int numberOfEvents()
  {
    int number = events.size();
    return number;
  }

  public boolean hasEvents()
  {
    boolean has = events.size() > 0;
    return has;
  }

  public int indexOfEvent(Event aEvent)
  {
    int index = events.indexOf(aEvent);
    return index;
  }

  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    registrations.add(aRegistration);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    if (registrations.contains(aRegistration))
    {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRegistrationAt(Registration aRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addRegistration(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registrations.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfParticipantants()
  {
    return 0;
  }

  public boolean addParticipantant(Participant aParticipantant)
  {
    boolean wasAdded = false;
    if (participantants.contains(aParticipantant)) { return false; }
    participantants.add(aParticipantant);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeParticipantant(Participant aParticipantant)
  {
    boolean wasRemoved = false;
    if (participantants.contains(aParticipantant))
    {
      participantants.remove(aParticipantant);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addParticipantantAt(Participant aParticipantant, int index)
  {  
    boolean wasAdded = false;
    if(addParticipantant(aParticipantant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParticipantants()) { index = numberOfParticipantants() - 1; }
      participantants.remove(aParticipantant);
      participantants.add(index, aParticipantant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveParticipantantAt(Participant aParticipantant, int index)
  {
    boolean wasAdded = false;
    if(participantants.contains(aParticipantant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParticipantants()) { index = numberOfParticipantants() - 1; }
      participantants.remove(aParticipantant);
      participantants.add(index, aParticipantant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addParticipantantAt(aParticipantant, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEvents()
  {
    return 0;
  }

  public boolean addEvent(Event aEvent)
  {
    boolean wasAdded = false;
    if (events.contains(aEvent)) { return false; }
    events.add(aEvent);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEvent(Event aEvent)
  {
    boolean wasRemoved = false;
    if (events.contains(aEvent))
    {
      events.remove(aEvent);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEventAt(Event aEvent, int index)
  {  
    boolean wasAdded = false;
    if(addEvent(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEventAt(Event aEvent, int index)
  {
    boolean wasAdded = false;
    if(events.contains(aEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEvents()) { index = numberOfEvents() - 1; }
      events.remove(aEvent);
      events.add(index, aEvent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEventAt(aEvent, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    registrations.clear();
    participantants.clear();
    events.clear();
  }

}