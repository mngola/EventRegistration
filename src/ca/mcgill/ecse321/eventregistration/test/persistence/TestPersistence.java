package ca.mcgill.ecse321.eventregistration.test.persistence;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		RegistrationManager rm = RegistrationManager.getInstance();
		
		//create participants
		Participant pa = new Participant("Martin");
		Participant pa2 = new Participant("Jennifer");
		
		//create event
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.SEPTEMBER,15,8,30,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2015, Calendar.SEPTEMBER,15,8,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		Event ev = new Event("Concert", eventDate, startTime, endTime);
		
		// register participants to event
		Registration re = new Registration(pa,ev);
		Registration re2 = new Registration(pa2,ev);
		
		//manage registrations
		rm.addRegistration(re);
		rm.addRegistration(re2);
		rm.addEvent(ev);
		rm.addParticipantant(pa);
		rm.addParticipantant(pa2);
	}

	@After
	public void tearDown() throws Exception {
		//clear all registrations
		RegistrationManager rm = RegistrationManager.getInstance();
		rm.delete();
	}

	@Test
	public void test() {
		//save model
		RegistrationManager rm = RegistrationManager.getInstance();
		PersistenceXStream.setFilename("src\\ca\\mcgill\\ecse321\\eventregistration\\test\\persistence\\data.xml");
		PersistenceXStream.setAlias("event", Event.class);
		PersistenceXStream.setAlias("participant", Participant.class);
		PersistenceXStream.setAlias("registration", Registration.class);
		PersistenceXStream.setAlias("manager", RegistrationManager.class);
		if (!PersistenceXStream.saveToXMLwithXStream(rm))
			fail("Could not save file");
		
		//clear the model memory
		rm.delete();
		assertEquals(0,rm.getParticipantants().size());
		assertEquals(0,rm.getEvents().size());
		assertEquals(0,rm.getRegistrations().size());
		
		// load model
		rm = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		if(rm==null)
			fail("Could not load file.");
		
		//check participants
		assertEquals(2,rm.getParticipantants().size());
		assertEquals("Martin",rm.getParticipantant(0).getName());
		assertEquals("Jennifer",rm.getParticipantant(1).getName());
		// check event
		assertEquals(1,rm.getEvents().size());
		assertEquals("Concert",rm.getEvent(0).getName());
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.SEPTEMBER,15,8,30,0);
		Date eventDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(2015, Calendar.SEPTEMBER,15,8,30,0);
		Time endTime = new Time(c.getTimeInMillis());
		assertEquals(eventDate.toString(), rm.getEvent(0).getEventDate().toString());
		assertEquals(startTime.toString(), rm.getEvent(0).getStartTime().toString());
		assertEquals(endTime.toString(), rm.getEvent(0).getEndTime().toString());
		// check registrations
		assertEquals(2, rm.getRegistrations().size());
		assertEquals(rm.getEvent(0), rm.getRegistration(0).getEvent());
		assertEquals(rm.getParticipantant(0), rm.getRegistration(0).getParticipant());
		assertEquals(rm.getEvent(0), rm.getRegistration(1).getEvent());
		assertEquals(rm.getParticipantant(1), rm.getRegistration(1).getParticipant());
	}

}
