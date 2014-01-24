package org.amse.yaroslavtsev.practice.knots.model;
           
/**
 *
 * Интерфейс ребра узла
 *
 * @author Yaroslavtsev Grigory
 *
 */


public interface IEdge extends IElement {
	
	/**
	 *
	 * Получение начальной точки ребра
	 *
	 * @return Начальная точка ребра 
	 *
	 */	

	public IPoint getSource();
	
	/**
	 *
	 * Получение конечной точки ребра
	 *
	 * @return Конечная точка ребра
	 *
	 */
	
	public IPoint getTarget();

	/**
	 *
	 * Изменение орентации ребра на противоположную - начальная и конечная точки меняются местами
	 *
	 */
	
	public void rotate();
}
