package org.amse.yaroslavtsev.practice.knots.model;

import java.util.*;

/**
 *
 * Интерфейс диаграммы узла
 *
 * @author Yaroslavtsev Grigory
 *
 */

public interface IKnot {
	
	/**
  	 *
  	 * Получение списка точек узла
  	 *
  	 * @return Список точек узла
  	 *
  	 */
	
	public List <IPoint> points();

	/**
	 *
	 * Добавление точки к узлу
	 *
	 * @param x - Координата добавляемой точки по оси X
	 * @param y - Координата добавляемой точки по оси Y
	 * @return Добавленная точка
	 *
	 */
	
	
	public IPoint addPoint(int x, int y);
	
	/**
	 *
	 * Удаление точки из узла
	 *
	 * @param point - Добавляемая точка
	 *
	 */
	
	public void removePoint(IPoint point);

	/**
  	 *
  	 * Получение списка рёбер узла
  	 *
  	 * @return Список рёбер узла
  	 *
  	 */	
	
	public List <IEdge> edges();	
	
	/**
  	 *
  	 * Определяет, можно ли присоединить ребро к данной точке
  	 *
  	 * @param point - Точка, к которой мы хотим присоединить ребро
  	 * @return true если можно присоединить ребро к точке point, false если этого сделать нельзя
  	 *
  	 */

	public boolean canAddEdge(IPoint point);

	/**
  	 *
  	 * Определяет, можно  ли провести ребро из одной точки в другую
  	 *
  	 * @param source - Начало ребра
  	 * @param target - Конец ребра
  	 * @return true если можно провести ребро из точки source в точку target, false если ребро из точки source в точку target провести нельзя
  	 *
  	 */
	
	public boolean canAddEdge(IPoint source, IPoint target);

	/**
	 *
	 * Добавление ребра к узлу
	 *
	 * @param source - Начальная точка ребра
	 * @param target - Конечная точка ребра
	 * @return Добавленное ребро или null в случае, если ребро между точками source и target не может быть добавлено
	 *
	 */

	public IEdge addEdge(IPoint source, IPoint target);

	/**
	 *
	 * Удаление ребра из узла
	 *
	 * @param edge - Удаляемое ребро
	 *
	 */

	public void removeEdge(IEdge edge);

	/**
  	 *
  	 * Получение списка пересечений узла
  	 *
  	 * @return Список пересечений узла
  	 *
  	 */
	
	public List <IIntersection> intersections(); 

	/**
	 *
	 * Возвращает завершённость узла
	 *
	 * @return true, если узел не пуст и все его компоненты замкнуты, false в противном случае	
	 *
	 */

	public boolean isComplete();
	
	/**
	 *
	 * Приведение модели в состояние пустого узла
	 *
	 */
	
	public void clear(); 

	/**
	 *
	 * Получение копии модели с той же структурой узла, но состоящей из новых обектов
	 *
	 * @return Копия структуры узла, состоящая из новых обектов
	 *
	 */
	
	public IKnot getCopy();
}
