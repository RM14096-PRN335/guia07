/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.AbstracInterface;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.TipoSeccionFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoSeccion;

/**
 *
 * @author ed
 */
@Named(value = "TipoSeccionBean")
@ViewScoped
public class TipoSeccionBean extends DefaultGenerator<TipoSeccion> implements Serializable{

    /**
     * Creates a new instance of RolBean
     */
    public TipoSeccionBean() {
    }

    @EJB
    private TipoSeccionFacadeLocal TipoSeccionFacade;

  


    /**
     *
     * @return
     */
    @Override
    public TipoSeccion crearNuevo() {

        this.registro = new TipoSeccion();

        return this.registro;
    }

    /**
     *
     * @return
     */
    @Override
    protected AbstracInterface<TipoSeccion> getFacade(){
        return this.TipoSeccionFacade;
    }
    
    

    /**
     *
     */
    public void onRowSelect() {

    }
    
    /**
     *
     * @return
     */
    public Integer seleccion (){
        return registro.getIdTipoSeccion();
    }


    
 

    /**
     *
     * @param rowKey
     * @return
     */
    @Override
    public TipoSeccion obtenerRowData(String rowKey) {
        try {
            List<TipoSeccion> registros = (List<TipoSeccion>) this.modelo.getWrappedData();
            if (registros != null && !registros.isEmpty()) {
               int buscado = Integer.parseInt(rowKey);
                for (TipoSeccion r : registros) {
                    if (r.getIdTipoSeccion().compareTo(buscado) != 0) {
                    } else {
                        return r;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override 
    public Object obtenerRowKey(TipoSeccion object) {
        if (object != null) {
            return object.getIdTipoSeccion();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public LazyDataModel<TipoSeccion> getModelo() {
        return modelo;
    }



}
