/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;
/**
 * Two-Way List
 *
 * @author AED team
 * @version 1.0
 *
 * @param <E> Generic Element
 */
public interface TwoWayList<E> extends List<E> {
    /**
     * Returns a two-way iterator of the elements in the list.
     *
     * @return Two-Way Iterator of the elements in the list
     */
    TwoWayIterator<E> twoWayiterator();
}