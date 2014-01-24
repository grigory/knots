package org.amse.yaroslavtsev.practice.knots.model;

/**
 *
 * »нтерфейс точки узла
 *
 * @author Yaroslavtsev Grigory
 *
 */


public interface IPoint extends IElement {

	/**
	 *
	 * ѕолучение координаты точки по оси X
	 *
	 * @return ¬озвращает координату узла по оси X
	 *
	 */

	public int getX();

	/**
	 *
	 * ѕолучение координаты точки по оси Y
	 *
	 * @return ¬озвращает координату точки по оси Y
	 *
	 */

	public int getY();

	/**
	 *
	 * ѕеремещение точки узла в точку с координатами (x,y)
	 *
	 * @param x - координата точки по оси X
	 * @param y - координата точки по оси Y
	 *
	 */

	public void moveTo(int x, int y);
}
