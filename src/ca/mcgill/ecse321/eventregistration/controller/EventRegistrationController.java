package ca.mcgill.ecse321.eventregistration.controller;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

public class EventRegistrationController {
	
	public EventRegistrationController()
	{
		
	}
	
	public String createParticipant(String name)
	{
		if(name==null||name.trim().length()==0)
		{
		return "Participant name cannot be empty";
		}
		
		Participant p = new Participant(name);
		RegistrationManager rm = RegistrationManager.getInstance();
		rm.addParticipantant(p);
		PersistenceXStream.saveToXMLwithXStream(rm);
		return null;
	}
	
	public String createEvent(String name, Date date, Time startTime, Time endTime)
	{
		String error = "";
		if (name == null || name.trim().length() == 0)
			error = error + "Event name cannot be empty! ";
		if(date == null)
			error = error + "Event date cannot be empty! ";
		if(startTime == null)
			error = error + "Event start time cannot be empty! ";
		if(endTime == null)
			error = error + "Event end time cannot be empty! ";
		if(endTime != null && startTime != null && endTime.getTime()<startTime.getTime())
			error = error + "Event end time cannot be before event start time! ";
		error = error.trim();
		if(error.length()>0)
			return error;
		
		Event e = new Event(name,date,startTime,endTime);
		RegistrationManager rm = RegistrationManager.getInstance();
		rm.addEvent(e);
		PersistenceXStream.saveToXMLwithXStream(rm);
		return null;
	}
	
	public String register(Participant participant, Event event)
	{
		RegistrationManager rm = RegistrationManager.getInstance();
		
		String error = "";
		if (participant == null)
			error = error + "Participant needs to be selected for registration! ";
		else if(!rm.getParticipantants().contains(participant))
			error = error + "Participant does not exist! ";
		if(event == null)
			error = error + "Event needs to be selected for registration!";
		else if(!rm.getEvents().contains(event))
			error = error + "Event does not exist!";
		error = error.trim();
		if(error.length()>0)
			return error;
		
		Registration r = new Registration(participant,event);
		rm.addRegistration(r);
		PersistenceXStream.saveToXMLwithXStream(rm);
		return null;
	}

}
