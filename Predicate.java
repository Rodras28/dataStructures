/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import java.io.Serializable;

public interface Predicate<E> extends Serializable {
    /**
     *  Filter that an element needs to check
     * @param elem
     * @return
     */
    boolean check(E elem);
}
