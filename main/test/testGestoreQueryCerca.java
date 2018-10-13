import DB.GestoreQueryCerca;
import Model.*;
import org.junit.*;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItems;


public class testGestoreQueryCerca {

    private GestoreQueryCerca gestoreQueryCerca;

    @Before public void setUp()  throws Exception {
        gestoreQueryCerca = new GestoreQueryCerca();
        assertNotNull(gestoreQueryCerca);
    }

    @After public void tearDown() throws  Exception {
        gestoreQueryCerca = null;
        assertNull(gestoreQueryCerca);
    }

    /**
       Primo caso:
       Username e password inseriti e presente nel DB
     */
    @Test public void testeseguiQueryRicercaImpiegatoConnesso1() {
        Impiegato testAdmin = new Impiegato("admin","admin","V",43);
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("admin", "admin");
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        assertEquals(testAdmin,testResult);
    }
    /**
       Secondo caso:
       Username &&  password non inseriti
     */
    @Test public void testeseguiQueryRicercaImpiegatoConnesso2() {
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("","");
        } catch ( SQLException e ) { e.printStackTrace(); }

        assertNull(testResult);
    }
    /**
        Terzo caso:
        Username e passwordo inseriti ma non presenti nel DB
     */
    @Test public void testeeseguiQueryRicercaImpiegatoConnesso3() {

        Impiegato testResult = null;
        try {
            // Queste credienziali non sono presenti nel DB
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("prova","prova");
            fail();// Se non lancia un eccezione il test fallisce, ( resultSet.next() deve lanciarla precisamente  )
        } catch ( SQLException e ) { assertTrue(true); }

    }

    @Test public void testeseguiQueryRicercaImpiegatoConnesso4() {
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("admin","");
        } catch ( SQLException e ) { e.printStackTrace(); }

        assertNull(testResult);
    }

    @Test public void testeseguiQueryRicercaImpiegatoConnesso5 () {
        Impiegato testResult = null;
        try {
            testResult = gestoreQueryCerca.eseguiQueryRicercaImpiegatoConnesso("", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertNull(testResult);
    }
    


    @Test public void  testeseguiQueryRicercaPartecipantiEvento1() {

        Set<String> partecipanti = new HashSet<String>();
        partecipanti.add("Calcutta");
        Evento testEvento = new Evento(LuogoEnum.PALAPARTENOPE,null,Float.valueOf(23.32f),TipologiaEnum.MUSICALE,"Calcutta Concerto Di",LocalDate.of(2019,01,26),
                GenereMusicaleEnum.POP.name(),partecipanti);
        testEvento.setIdEvento(224);

        Set<String> testPartecipanti = new HashSet<>();
        try {
            testPartecipanti = gestoreQueryCerca.eseguiQueryRicercaPartecipantiEvento(224);
        } catch ( SQLException e ) { }

        assertThat(testPartecipanti,is(partecipanti));
        assertTrue(testPartecipanti.contains("Calcutta"));
        assertThat(testPartecipanti.size(), is(1));
    }

    @Test public void testeseguiQueryRicercaPartecipantiEvento2() {
        Set<String> partecipanti = new HashSet<String>();
        partecipanti.add("Calcutta");
        Evento testEvento = new Evento(LuogoEnum.PALAPARTENOPE,null,Float.valueOf(23.32f),TipologiaEnum.MUSICALE,"Calcutta Concerto Di",LocalDate.of(2019,01,26),
                GenereMusicaleEnum.POP.name(),partecipanti);
        testEvento.setIdEvento(224);

        Set<String> testPartecipanti = new HashSet<>();
        try {

            testPartecipanti = gestoreQueryCerca.eseguiQueryRicercaPartecipantiEvento(111);

        } catch ( SQLException e ) { }

        assertFalse(testPartecipanti.contains("Calcutta"));
    }

    @Test public void testeseguiQueryRicercaArtistiEvento3() {
        Set<String> partecipanti = new HashSet<String>();
        partecipanti.add("Calcutta");
        Evento testEvento = new Evento(LuogoEnum.PALAPARTENOPE,null,Float.valueOf(23.32f),TipologiaEnum.MUSICALE,"Calcutta Concerto Di",LocalDate.of(2019,01,26),
                GenereMusicaleEnum.POP.name(),partecipanti);
        testEvento.setIdEvento(224);

        Set<String> testPartecipanti = new HashSet<>();
        try {
            testPartecipanti = gestoreQueryCerca.eseguiQueryRicercaPartecipantiEvento(1); // Non abbiamo nessun evento con id=1
            fail();
        } catch ( SQLException e ) { assertTrue(true);}
    }





}
