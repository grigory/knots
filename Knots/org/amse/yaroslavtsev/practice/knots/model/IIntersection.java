package org.amse.yaroslavtsev.practice.knots.model;

/**
 *
 * Интерфейс пересечения рёбер узла
 *
 * @author Yaroslavtsev Grigory
 *
 */

public interface IIntersection extends IElement {
	
	/**
	 *
	 * Возвращает верхнее ребро пересечения
	 *
	 * @return Верхнее ребро пересечения	
	 *
	 */
	
	IEdge getUpper();
	
	/**
	 *
	 * Возвращает нижнее ребро пересечения
	 *
	 * @return Нижнее ребро пересечения	
	 *
	 */
	
	IEdge getLower();

	/**
	 *
	 * Изменение типа пересечения на противоположный - верхнее и нижнее ребро меняются местами
	 *
	 */

	void changeType();
}
