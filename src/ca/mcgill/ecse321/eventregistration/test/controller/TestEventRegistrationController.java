package ca.mcgill.ecse321.eventregistration.test.controller;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.eventregistration.controller.EventRegistrationController;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

public class TestEventRegistrationController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("src\\ca\\mcgill\\ecse321\\eventregistration\\test\\persistence\\data.xml");
		PersistenceXStream.setAlias("event", Event.class);
		PersistenceXStream.setAlias("participant", Participant.class);
		PersistenceXStream.setAlias("registration", Registration.class);
		PersistenceXStream.setAlias("manager", RegistrationManager.class);	
	}

	@After
	public void tearDown() throws Exception {
		// clear all registrations
		RegistrationManager rm = RegistrationManager.getInstance();
		rm.delete();
	}

	@Test
	public void testCreateParticipant() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipantants().size());
		
		String name = "Oscar";
		
		EventRegistrationController erc = new EventRegistrationController();
		String error = erc.createParticipant(name);
		
		//check error
		assertNull(error);
		
		//check model in memory
		checkResultParticipant(name, rm);
		
		RegistrationManager rm2 = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultParticipant(name, rm2);
	}
	
	@Test
	public void testCreateParticipantNull() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipantants().size());
		
		String name = null;
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createParticipant(name);
		
		//check error
		assertEquals("Participant name cannot be empty", error);
		//check no change in memory
		assertEquals(0, rm.getParticipantants().size());
	}
	
	@Test
	public void testCreateParticipantSpaces() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipantants().size());
		
		String name = " ";
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createParticipant(name);
		
		//check error
		assertEquals("Participant name cannot be empty", error);
		//check no change in memory
		assertEquals(0, rm.getParticipantants().size());
	}
	
	@Test
	public void testCreateParticipantEmpty() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipantants().size());
		
		String name = "";
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createParticipant(name);
		
		//check error
		assertEquals("Participant name cannot be empty", error);
		//check no change in memory
		assertEquals(0, rm.getParticipantants().size());
	}
	
	@Test
	public void testCreateEvent() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getEvents().size());
		
		String name = "Football game";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER,16,9,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createEvent(name,eventDate,startTime,endTime);
		
		//check error
		assertNull(error);
		
		//check model in memory
		checkResultEvent(name,eventDate,startTime,endTime,rm);
		
		RegistrationManager rm2 = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//check file contents
		checkResultEvent(name,eventDate,startTime,endTime,rm2);
	}
	
	@Test
	public void testCreateEventNull() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getEvents().size());
		
		String name = null;
		Date eventDate = null;
		Time startTime = null;
		Time endTime = null;
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createEvent(name,eventDate,startTime,endTime);
		
		//check error
		assertEquals("Event name cannot be empty! Event date cannot be empty! Event start time cannot be empty! Event end time cannot be empty!",error);
				
		//check model in memory
		assertEquals(0,rm.getEvents().size());
	}
	
	@Test
	public void testCreateEventEmpty() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getEvents().size());
		
		String name = "";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER,16,9,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createEvent(name,eventDate,startTime,endTime);
		
		//check error
		assertEquals("Event name cannot be empty!",error);
						
		//check model in memory
		assertEquals(0,rm.getEvents().size());
	}
	
	@Test
	public void testCreateEventSpaces() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getEvents().size());
		
		String name = " ";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER,16,9,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createEvent(name,eventDate,startTime,endTime);
		
		//check error
		assertEquals("Event name cannot be empty!",error);
						
		//check model in memory
		assertEquals(0,rm.getEvents().size());
	}
	
	@Test
	public void testCreateEventEndTimeBeforeStartTime() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getEvents().size());
		
		String name = "Football game";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER,16,8,59,59);
		Time endTime = new Time(c.getTimeInMillis());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.createEvent(name,eventDate,startTime,endTime);
		
		//check error
		assertEquals("Event end time cannot be before event start time!",error);
								
		//check model in memory
		assertEquals(0,rm.getEvents().size());
	}
	
	@Test
	public void testRegister() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getRegistrations().size());
		
		String nameP = "Oscar";
		Participant participant = new Participant(nameP);
		rm.addParticipantant(participant);
		assertEquals(1,rm.getParticipantants().size());
		
		String nameE = "Football game";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER,16,9,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Event event = new Event(nameE,eventDate,startTime,endTime);
		rm.addEvent(event);
		assertEquals(1,rm.getEvents().size());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.register(participant,event);
		
		//check error
		assertNull(error);
		//check model in memory
		checkResultRegister(nameP,nameE,eventDate,startTime,endTime,rm);
		
		RegistrationManager rm2 = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultRegister(nameP,nameE,eventDate,startTime,endTime,rm2);

	}
	
	@Test
	public void testRegisterNull() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getRegistrations().size());
		
		Participant participant = null;
		assertEquals(0, rm.getParticipantants().size());
		
		Event event = null;
		assertEquals(0, rm.getEvents().size());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.register(participant,event);
		
		//check error
		assertEquals("Participant needs to be selected for registration! Event needs to be selected for registration!", error);
		
		//check model in memory
		assertEquals(0, rm.getRegistrations().size());
		assertEquals(0, rm.getParticipantants().size());
		assertEquals(0, rm.getEvents().size());
	}
	
	@Test
	public void testRegisterParticipantAndEventDoNotExist() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getRegistrations().size());
		
		String nameP = "Oscar";
		Participant participant = new Participant(nameP);
		assertEquals(0,rm.getParticipantants().size());
		
		String nameE = "Football game";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER,16,9,00,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER,16,9,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Event event = new Event(nameE,eventDate,startTime,endTime);
		assertEquals(0,rm.getEvents().size());
		
		EventRegistrationController erc = new EventRegistrationController(); 
		String error = erc.register(participant,event);
		
		//check error
		assertEquals("Participant does not exist! Event does not exist!", error);

		//check model in memory
		assertEquals(0, rm.getRegistrations().size());
		assertEquals(0, rm.getParticipantants().size());
		assertEquals(0, rm.getEvents().size());
	}
	
	private void checkResultRegister(String nameP, String nameE,
			Date eventDate, Time startTime, Time endTime,
			RegistrationManager rm2) {
		assertEquals(1, rm2.getParticipantants().size());
		assertEquals(nameP, rm2.getParticipantant(0).getName());
		assertEquals(1, rm2.getEvents().size());
		assertEquals(nameE, rm2.getEvent(0).getName());
		assertEquals(eventDate.toString(), rm2.getEvent(0).getEventDate().toString());
		assertEquals(startTime.toString(), rm2.getEvent(0).getStartTime().toString());		
		assertEquals(endTime.toString(), rm2.getEvent(0).getEndTime().toString());
		assertEquals(1, rm2.getRegistrations().size());
		assertEquals(rm2.getEvent(0), rm2.getRegistration(0).getEvent());
		assertEquals(rm2.getParticipantant(0),rm2.getRegistration(0).getParticipant());
	}

	private void checkResultEvent(String name, Date eventDate, Time startTime,
			Time endTime, RegistrationManager rm2) {
		assertEquals(0, rm2.getParticipantants().size());
		assertEquals(1, rm2.getEvents().size());
		assertEquals(name, rm2.getEvent(0).getName());
		assertEquals(eventDate.toString(), rm2.getEvent(0).getEventDate().toString());
		assertEquals(startTime.toString(), rm2.getEvent(0).getStartTime().toString());		
		assertEquals(endTime.toString(), rm2.getEvent(0).getEndTime().toString());
		assertEquals(0, rm2.getRegistrations().size());		
	}


	private void checkResultParticipant(String name, RegistrationManager rm2) {
		assertEquals(1, rm2.getParticipantants().size());
		assertEquals(name, rm2.getParticipantant(0).getName());
		assertEquals(0, rm2.getEvents().size());
		assertEquals(0, rm2.getRegistrations().size());
	}

}
