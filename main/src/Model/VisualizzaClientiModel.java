package Model;

import javafx.beans.property.ObjectProperty;

import java.util.*;

public class VisualizzaClientiModel extends Observable {

    private volatile List<Cliente> listaClienti = new ArrayList<>();
    private volatile Cliente clienteSelezionato = new Cliente();
    private Map<String,Integer> mapTipologiaBiglietti = new HashMap<>();

    public void setListaClientiView (List<Cliente> list)
    {
        listaClienti = list;
        setChanged();
        notifyObservers(listaClienti);
    }
    public List<Cliente> getListaClienti(){ return listaClienti; }

    public synchronized Cliente getClienteSelezionato() {
        return clienteSelezionato;
    }

    public synchronized  void setClienteSelezionato(Cliente clienteSelezionato) {
        this.clienteSelezionato = clienteSelezionato;
    }

    public Map<String, Integer> getMapTipologiaBiglietti() {
        return mapTipologiaBiglietti;
    }

    public void setMapTipologiaBiglietti(Map<String, Integer> mapTipologiaBiglietti) {
        this.mapTipologiaBiglietti = mapTipologiaBiglietti;
        setChanged();
        notifyObservers(mapTipologiaBiglietti);
    }
}
