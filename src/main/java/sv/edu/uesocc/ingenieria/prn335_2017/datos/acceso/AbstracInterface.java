/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.util.List;

/**
 *
 * @author ed
 */
public interface AbstracInterface<T>  {
    
    boolean create(T Entity);

    boolean edit(T Entity);

    boolean remove(T Entity);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int desde, int hasta);

   
    int count();
}
