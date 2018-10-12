package DB;


import Model.Cliente;
import Model.Evento;
import Model.LuogoEnum;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public interface ClientiDAO {


    public Collection<Cliente> cercaCliente(String username) throws Exception;
    public void eliminaCliente(Cliente c) throws Exception;
    public Map<String,Integer> cercaBigliettiPerTipologia(Cliente c) throws Exception;

}
