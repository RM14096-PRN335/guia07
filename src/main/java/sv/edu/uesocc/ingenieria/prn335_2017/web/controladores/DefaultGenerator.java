package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.AbstracInterface;



/**
 *
 * @author ed
 * @param <T>
 */
public abstract class DefaultGenerator<T> implements Serializable {
   
    protected LazyDataModel<T> modelo;
    
    protected T registro;
 
    private boolean mostrandoDetalle= false; 
    private boolean btnGuardar = false;
    private boolean btnModEli = false;
    private boolean seleccions =false;
    
     /**
     *
     */
    @PostConstruct
    protected void inicializar() {
        this.inicializarModelo();
        this.mostrandoDetalle = false;
        this.inicializarListas();
    }
    
    /**
     *
     * @return
     */
    public abstract T crearNuevo();

    /**
     *
     */
    protected void inicializarListas() {
        
    }
    
     /**
     *
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return
     */
    public List<T> cargarDatos(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List salida = null;
        try {
            if (getFacade() != null) {
                salida = getFacade().findRange(first, pageSize);
                if (this.modelo != null) {
                    this.modelo.setRowCount(getFacade().count());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if (salida == null) {
                salida = new ArrayList();
            }
        }
        return salida;
    }
    
    protected abstract AbstracInterface<T> getFacade();
    
    /**
     *
     * @param rowKey
     * @return
     */
    public abstract T obtenerRowData(String rowKey);


    /**
     *
     * @param Object
     * @return
     */
    public abstract Object obtenerRowKey(T Object);

    
    /**
     *
     */
    protected void inicializarModelo() {
        try {
            this.modelo = new LazyDataModel<T>() {
                @Override
                public T getRowData(String rowKey) {
                    return obtenerRowData(rowKey);
                }

                @Override
                public Object getRowKey(T object) {
                    return obtenerRowKey(object);
                }
                @Override
                public List <T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
                    return cargarDatos(first,pageSize, sortField, sortOrder, filters);
                }
            };

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
    
    
    /**
     *
     * @param se
     */
    public void cambiarSeleccion(SelectEvent se) {
        if (se.getObject() != null) {
            try {
                this.registro = (T) se.getObject();
                this.mostrandoDetalle = true;
                this.btnModEli = true;
                this.btnGuardar = false;
               
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                this.registro= null;
            }
        }
    }
    
    /**
     *
     * @param ae
     */
    public void btnNuevoHandler(ActionEvent ae) {
       
        this.mostrandoDetalle = true;
        this.btnGuardar=true;
        this.btnModEli=false;
         /*this.crearNuevo();*/
         }
    
    /**
     *
     * @param ae
     */
    public void btnCancelarHandler(ActionEvent ae) {
        this.registro = null;
        this.mostrandoDetalle = false;
        this.btnModEli=false;
        this.btnGuardar=false;
    }


    /**
     *
     * @param ae
     */
    public void btnGuardarHandler(ActionEvent ae) {
        boolean resultado = false;
        try {
            if (this.registro != null && this.getFacade() != null) {
                resultado = this.getFacade().create(this.registro);
                if (resultado) {
                     this.mostrandoDetalle = false;
                   this.btnGuardar=false;
                   this.btnModEli=false;
                }
                }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     *
     * @param ae
     */
    public void btnEliminarHandler(ActionEvent ae) {
        try {
            if (this.registro != null && this.getFacade() != null) {
                boolean resultado = this.getFacade().remove(registro);
                this.registro = null;
                this.btnGuardar=false;
                this.btnModEli=false;
                this.mostrandoDetalle=false;
            } 
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


    /**
     *
     * @param ae
     */
    public void btnModificarHandler(ActionEvent ae) {
        try {
            if (this.registro != null && this.getFacade() != null) {
                boolean resultado = false;
                if (this.getFacade().edit(registro)) {
                    resultado = true; 
                    this.btnGuardar=false;
                    this.btnModEli=false;
                    this.mostrandoDetalle=false;
                }
                }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    /**
     *
     * @param modelo
     */
    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    
     /**
     *
     * @return
     */
    public T getRegistro() {
        return registro;
    }

    /**
     *
     * @param registro
     */
    public void setRegistro(T registro) {
        this.registro = registro;
    }

    /**
     *
     * @return
     */
    public boolean isMostrandoDetalle() {
        return mostrandoDetalle;
    }
    

    /**
     *
     * @param mostrandoDetalle
     */
    public void setMostrandoDetalle(boolean mostrandoDetalle) {
        this.mostrandoDetalle = mostrandoDetalle;
    }


    public boolean isSeleccions() {
        return seleccions;
    }

    public void setSeleccions(boolean seleccions) {
        this.seleccions = seleccions;
    }

    public boolean isBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(boolean btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public boolean isBtnModEli() {
        return btnModEli;
    }

    public void setBtnModEli(boolean btnModEli) {
        this.btnModEli = btnModEli;
    }

    
    


}