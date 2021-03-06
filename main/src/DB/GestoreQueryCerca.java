
package DB;

import Model.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class GestoreQueryCerca {


    public List<Cliente> eseguiQueryRicercaClienti(String username) throws SQLException {

        ArrayList<Cliente> listaclienti = new ArrayList<>();
        ResultSet resultSet = null;
        String selectSql = null;
        String queryWhere = null;


        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = null;
        selectSql = "SELECT * FROM CLIENTE NATURAL JOIN PERSONA";
        //{0}
        if (!username.equals("")) {
            queryWhere = " WHERE USERNAME LIKE '%"+username+"%' ";
            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            resultSet = preparedStatement.executeQuery();
        } else{
            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();}
        //{1}


        while (resultSet.next()) {
            String cf = (resultSet.getString(1));//CODICEfISCALE
            int id = (resultSet.getInt(2));//ID
            String username1 = (resultSet.getString(3));//USERNAME
            String password =(resultSet.getString(4));//PASSWORD
            String email = (resultSet.getString(5));//EMAIL
            String telefono =(resultSet.getString(6));//NUMERO DI TELEFONO
            Float spesaTot = (resultSet.getFloat(7));//SPESA TOTALE
            Float spesaCarta = (resultSet.getFloat(8));//SPESA CARTA
            int n_bigietti = (resultSet.getInt(9));//NUMERO BIGLIETTI
            String nome = (resultSet.getString(10));//NOME
            String cognome = (resultSet.getString(11));//COGNOME
            LocalDate dataNascita = (resultSet.getDate(12).toLocalDate());//DATA NASCITA


            Cliente rigaCliente = new Cliente(cf,id,username1,password,email,telefono,spesaTot,spesaCarta,n_bigietti,nome,cognome,dataNascita);
            listaclienti.add(rigaCliente);

        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return listaclienti;

    }

    public List<Cliente> eseguiQueryRicercaClienti() throws SQLException{
        List<Cliente> listaClienti = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = null;
        String selectSql = " SELECT * FROM CLIENTE NATURAL JOIN PERSONA ";
        preparedStatement = connection.prepareStatement(selectSql);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String cf = (resultSet.getString(1));//CODICEfISCALE
            int id = (resultSet.getInt(2));//ID
            String username1 = (resultSet.getString(3));//USERNAME
            String password =(resultSet.getString(4));//PASSWORD
            String email = (resultSet.getString(5));//EMAIL
            String telefono =(resultSet.getString(6));//NUMERO DI TELEFONO
            Float spesaTot = (resultSet.getFloat(7));//SPESA TOTALE
            Float spesaCarta = (resultSet.getFloat(8));//SPESA CARTA
            int n_bigietti = (resultSet.getInt(9));//NUMERO BIGLIETTI
            String nome = (resultSet.getString(10));//NOME
            String cognome = (resultSet.getString(11));//COGNOME
            LocalDate dataNascita = (resultSet.getDate(12).toLocalDate());//DATA NASCITA




        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return listaClienti;
    }

    public List<Evento> eseguiQueryRicercaEventi() throws SQLException {

        String sql = " SELECT * FROM EVENTO ";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Evento> listaEventi = new ArrayList<>();

        connection = UtilityDB.getConnessioneDB();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            LuogoEnum luogoEvento = (LuogoEnum.valueOf(resultSet.getString("LUOGO")));
            LocalDate date = (resultSet.getDate("DATA").toLocalDate());
            String nome = (resultSet.getString("NOME"));
            LuogoEnum luogoEnum = (LuogoEnum.valueOf(resultSet.getString("LUOGO")));
            Float prezzo = (resultSet.getFloat("PREZZO"));
            String descrizione = (resultSet.getString("DESCRIZIONE"));
            TipologiaEnum tipologia = (TipologiaEnum.valueOf(resultSet.getString("TIPOLOGIA")));
            String genere = (resultSet.getString("GENERE"));
            int id = (resultSet.getInt("ID"));
            int biglietti = (resultSet.getInt("BIGLIETTI_VENDUTI"));
            Set<String> partecipantiEvento = new HashSet<>();
            partecipantiEvento = eseguiQueryRicercaPartecipantiEvento(id);
            Evento rigaEvento = new Evento(luogoEvento, descrizione, prezzo, tipologia, nome, date, genere, partecipantiEvento,biglietti);
            rigaEvento.setIdEvento(id);
            listaEventi.add(rigaEvento);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return listaEventi;
    }

    public Evento eseguiQueryRicercaEventiId(int idEvento) throws SQLException {


        if ( idEvento <= 0) return null;

        String sql = " SELECT * FROM EVENTO WHERE ID = ? ";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Evento evento = null;


        connection = UtilityDB.getConnessioneDB();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,idEvento);
        resultSet = preparedStatement.executeQuery();

        resultSet.next();

            LuogoEnum luogoEvento = (LuogoEnum.valueOf(resultSet.getString("LUOGO")));
            LocalDate date = (resultSet.getDate("DATA").toLocalDate());
            String nome = (resultSet.getString("NOME"));
            LuogoEnum luogoEnum = (LuogoEnum.valueOf(resultSet.getString("LUOGO")));
            Float prezzo = (resultSet.getFloat("PREZZO"));
            String descrizione = (resultSet.getString("DESCRIZIONE"));
            TipologiaEnum tipologia = (TipologiaEnum.valueOf(resultSet.getString("TIPOLOGIA")));
            String genere = (resultSet.getString("GENERE"));
            int id = (resultSet.getInt("ID"));
            int biglietti = (resultSet.getInt("BIGLIETTI_VENDUTI"));
            Set<String> partecipantiEvento = new HashSet<>();
            partecipantiEvento = eseguiQueryRicercaPartecipantiEvento(id);
            evento = new Evento(luogoEvento, descrizione, prezzo, tipologia, nome, date, genere, partecipantiEvento,biglietti);
            evento.setIdEvento(id);
            evento.setBigliettiVenduti(biglietti);


        resultSet.close();
        preparedStatement.close();
        connection.close();
        return evento;
    }

    public Set<String> eseguiQueryRicercaPartecipantiEvento(int id) throws SQLException {


        Set<String> setPartecipanti = new HashSet<>();
        String query = " SELECT PARTECIPANTE FROM PARTECIPANTI_EVENTO WHERE ID_EVENTO = ? ";
        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

       // if (!resultSet.isBeforeFirst()) throw new SQLException();

        while (resultSet.next()) setPartecipanti.add(resultSet.getString(1));

        UtilityDB.closeResultSet(resultSet);
        UtilityDB.closeConnection(connection);
        UtilityDB.closeDB(preparedStatement);

        return setPartecipanti;

    }

    public List<Evento> eseguiQueryRicercaEventi(String nomeEvento, LuogoEnum luogoEvento, LocalDate dataEvento) throws SQLException {

        List<Evento> listaEventi = new ArrayList<>();
        ResultSet resultSet = null;
        String luogo = null;
        if (luogoEvento != null) {
            luogo = luogoEvento.name();
        }
        String selectSql = null;
        String queryWhere = null;


        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = null;
        // Create tutti i risultati in una tabella
        selectSql = "SELECT * FROM EVENTO";
        //{1,2,3}
        if ((!nomeEvento.equals("")) && !(luogo == null) && !(dataEvento == null)) {
            queryWhere = " WHERE NOME LIKE '%"+nomeEvento+"%' AND LUOGO=? AND DATA=? ";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, luogo);
            preparedStatement.setDate(2, Date.valueOf(dataEvento));
            resultSet = preparedStatement.executeQuery();

        }
        //{1,2}
        else if ((!nomeEvento.equals("")) && (!(luogo == null)) && (dataEvento == null)) {

            queryWhere = " WHERE NOME LIKE '%"+nomeEvento+"%' AND LUOGO=? ";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, luogo);
            resultSet = preparedStatement.executeQuery();
        }
        //{1,3}
        else if ((!nomeEvento.equals("")) && ((luogo == null)) && !(dataEvento == null)) {
            queryWhere = " WHERE NOME LIKE '%"+nomeEvento+"%' AND DATA=? ";
            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setDate(1, Date.valueOf(dataEvento));
            resultSet = preparedStatement.executeQuery();
        }

        //{2,3}
        else if ((nomeEvento.equals("")) && (!(luogo == null)) && !(dataEvento == null)) {
            queryWhere = " WHERE LUOGO = ? AND DATA = ? ";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, luogo);
            preparedStatement.setDate(2, Date.valueOf(dataEvento));
            resultSet = preparedStatement.executeQuery();
        }
        //{1}
        else if ((!nomeEvento.equals("")) && ((luogo == null)) && (dataEvento == null)) {
            queryWhere = " WHERE NOME LIKE '%"+nomeEvento+"%' ";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            resultSet = preparedStatement.executeQuery();

        }
        //{2}
        else if ((nomeEvento.equals("")) && (!(luogo == null)) && (dataEvento == null)) {
            queryWhere = " WHERE LUOGO = ?";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, luogo);
            resultSet = preparedStatement.executeQuery();
        }
        //{3}
        else if ((nomeEvento.equals("")) && ((luogo == null)) && !(dataEvento == null)) {
            queryWhere = " WHERE DATA = ?";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setDate(1, Date.valueOf(dataEvento));
            resultSet = preparedStatement.executeQuery();
        } else {
            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();
        }



        while (resultSet.next()) {

            LocalDate date = (resultSet.getDate("DATA").toLocalDate());
            String nome = (resultSet.getString("NOME"));
            LuogoEnum luogoEnum = (LuogoEnum.valueOf(resultSet.getString("LUOGO")));
            Float prezzo = (resultSet.getFloat("PREZZO"));
            String descrizione = (resultSet.getString("DESCRIZIONE"));
            TipologiaEnum tipologia = (TipologiaEnum.valueOf(resultSet.getString("TIPOLOGIA")));
            String genere = (resultSet.getString("GENERE"));
            int id = (resultSet.getInt("ID"));
            int nbiglietti=(resultSet.getInt("BIGLIETTI_VENDUTI"));
            Set<String> partecipantiEvento = new HashSet<>();
            partecipantiEvento = eseguiQueryRicercaPartecipantiEvento(id);
            Evento rigaEvento = new Evento(luogoEnum, descrizione, prezzo, tipologia, nome, date, genere, partecipantiEvento,nbiglietti);
            rigaEvento.setIdEvento(id);
            listaEventi.add(rigaEvento);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return listaEventi;
    }

    public Impiegato eseguiQueryRicercaImpiegatoConnesso(String username, String password) throws SQLException{

        if( username == null || password == null || username.equals("") || password.equals("")) return null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = " SELECT * FROM IMPIEGATO WHERE USERNAME = ? AND PASSWORD = ? ";

        preparedStatement = UtilityDB.getConnessioneDB().prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        resultSet = preparedStatement.executeQuery();
        Impiegato imp = null;

        resultSet.next();
        String usernameI = (resultSet.getString("USERNAME"));
        String passwordI = (resultSet.getString("PASSWORD"));
        String amministratore = (resultSet.getString("ADMIN"));
        int id = (resultSet.getInt("ID"));
        imp = new Impiegato(usernameI,passwordI,amministratore,id);

        return imp;

    }

    public List<Evento> eseguiQueryRicercaEventiArtista(String Artista)  throws SQLException {

        String query = " SELECT ID_EVENTO FROM PARTECIPANTI_EVENTO WHERE PARTECIPANTE = ? ";
        PreparedStatement preparedStatement = UtilityDB.getConnessioneDB().prepareStatement(query);
        preparedStatement.setString(1,Artista);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Evento> listaEvento = new ArrayList<>();

        while(resultSet.next())
            listaEvento.add(eseguiQueryRicercaEventiId(resultSet.getInt(1)));

        UtilityDB.closeDB(preparedStatement);
        return listaEvento;
    }

    public Map<String,Integer> eseguiQueryRicercaEventiCitta(String artista) throws SQLException{
        Map<String,Integer> map = new HashMap<>();
        String query =
                " SELECT CITTA , COUNT(CITTA) " +
                " FROM EVENTO E JOIN partecipanti_evento PE on ID = ID_Evento " +
                " WHERE PE.Partecipante = ? " +
                " GROUP BY CITTA ";

        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,artista);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
            map.put(resultSet.getString("CITTA"),resultSet.getInt("COUNT(CITTA)"));

        UtilityDB.closeResultSet(resultSet);
        UtilityDB.closeDB(preparedStatement);
        UtilityDB.closeConnection(connection);

        return map;



    }

    public List<Addetto> eseguiQueryRicercaAddetto(String nome, String cognome, LocalDate datanascita) throws SQLException {

        List<Addetto> listaAddetti = new ArrayList<>();
        ResultSet resultSet = null;
        String selectSql = null;
        String queryWhere = null;


        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = null;
        selectSql = "SELECT * FROM VIEWADDETTI ";
        //{1,2,3}
        if (!nome.equals("") && !cognome.equals("") && !(datanascita == null)) {
            queryWhere = " WHERE NOME LIKE ? AND COGNOME LIKE ? AND DATA_NASCITA LIKE ?";
            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
            preparedStatement.setDate(3, Date.valueOf(datanascita));
            resultSet = preparedStatement.executeQuery();
        }
        //{1,2}
        else if (!nome.equals("") && !cognome.equals("") && (datanascita == null)) {
            queryWhere = " WHERE NOME LIKE ? AND COGNOME LIKE ? ";
            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
            resultSet = preparedStatement.executeQuery();
        }
        //{1,3}
        else if (!nome.equals("") && cognome.equals("") && !(datanascita == null)) {
            queryWhere = " WHERE NOME LIKE ? AND DATA_NASCITA LIKE ?";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, nome);
            preparedStatement.setDate(2, Date.valueOf(datanascita));
            resultSet = preparedStatement.executeQuery();
        }
        //{2,3}
        else if (nome.equals("") && !cognome.equals("") && !(datanascita == null)) {
            queryWhere = " WHERE COGNOME LIKE ? AND DATA_NASCITA LIKE ?";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, cognome);
            preparedStatement.setDate(2, Date.valueOf(datanascita));
            resultSet = preparedStatement.executeQuery();
        }
        //{1}
        else if (!nome.equals("") && cognome.equals("") && (datanascita == null)) {
            queryWhere = " WHERE NOME LIKE ? ";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();
        }
        //{2}
        else if (nome.equals("") && !cognome.equals("") && (datanascita == null)) {
            queryWhere = " WHERE COGNOME LIKE ?";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setString(1, cognome);
            resultSet = preparedStatement.executeQuery();
        }
        //{3}
        else if (nome.equals("") && cognome.equals("") && !(datanascita == null)) {

            queryWhere = " WHERE  DATA_NASCITA LIKE ?";

            preparedStatement = connection.prepareStatement(selectSql + queryWhere);
            preparedStatement.setDate(1, Date.valueOf(datanascita));
            resultSet = preparedStatement.executeQuery();
        }
        else {

            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();
        }


        while (resultSet.next()) {
            String nome1 = (resultSet.getString("NOME"));
            String cognome1 = (resultSet.getString("COGNOME"));
            LocalDate dataNascita = (resultSet.getDate("DATA_NASCITA").toLocalDate());
            String CF = (resultSet.getString("CODICE_FISCALE"));
            LocalDate dataAssunzione = (resultSet.getDate("DATA_ASSUNZIONE").toLocalDate());
            Float stipendio = (resultSet.getFloat("STIPENDIO"));
            String telefono = (resultSet.getString("TELEFONO"));
            String iban = (resultSet.getString("IBAN"));
            String email = (resultSet.getString("EMAIL"));
            int id = (resultSet.getInt("ID"));


            Addetto rigaAddetto = new Addetto(nome1, cognome1, dataNascita, CF, dataAssunzione, stipendio, telefono, iban, email, id);

            listaAddetti.add(rigaAddetto);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return listaAddetti;


    }

    public Map<String,Integer> eseguiQueryRicercaBigliettiPerTipologia(Cliente c ) {

        Map<String,Integer> map = new HashMap<>();
        Connection connection = UtilityDB.getConnessioneDB();
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet = null;
        String query = " SELECT TIPOLOGIA, COUNT(TIPOLOGIA) FROM EVENTO E JOIN BIGLIETTI_VENDUTI B ON E.ID = B.IDEVENTO WHERE B.IDCLIENTI = ? GROUP BY TIPOLOGIA ";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,c.getId());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                map.put(resultSet.getString("TIPOLOGIA"),resultSet.getInt("COUNT(TIPOLOGIA)"));

        } catch ( Exception e ) { e.printStackTrace(); }

    return map;
    }

}
