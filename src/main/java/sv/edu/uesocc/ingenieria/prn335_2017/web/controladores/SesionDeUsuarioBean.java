/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author carlos
 */
@Named
@SessionScoped
public class SesionDeUsuarioBean implements Serializable{
    
    private String codigoIdioma="es";
    private static Map<String,Object> paises;
    
    static{
    paises=new LinkedHashMap<>();
    paises.put("Español", Locale.forLanguageTag("es"));
    paises.put("English",Locale.US);
    }

    public  Map<String, Object> getPaises() {
        return paises;
    }

    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }
    
    public void CambioDeIdiomaHandler(ValueChangeEvent vce){
        if(vce.getNewValue()!=null){
            try {
                String nuevoCodigo=vce.getNewValue().toString();
                for (Map.Entry<String, Object> entrySet : paises.entrySet()) {
                   
                    Locale value = (Locale) entrySet.getValue();
                    if(value.toString().compareTo(nuevoCodigo)==0){
                        FacesContext.getCurrentInstance().getViewRoot().setLocale(value);
                        return;
                        
                    }
                    
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,ex.getMessage(), ex);
            }
  
            
        }
    }
    /**
     *
     * @param clave
     * @return
     */
    public String getMensaje(final String clave){
        try {
            ResourceBundle propiedades = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"PropiedadesGenerales");
            if(propiedades!=null && propiedades.containsKey(clave)){
                return propiedades.getString(clave);
            }
        } catch (Exception ex) {
             Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(),ex);
        }
     return null;   
}
    
}
