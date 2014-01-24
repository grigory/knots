package org.amse.yaroslavtsev.practice.knots.model;

/**
 *
 * ��������� ����� ����
 *
 * @author Yaroslavtsev Grigory
 *
 */


public interface IPoint extends IElement {

	/**
	 *
	 * ��������� ���������� ����� �� ��� X
	 *
	 * @return ���������� ���������� ���� �� ��� X
	 *
	 */

	public int getX();

	/**
	 *
	 * ��������� ���������� ����� �� ��� Y
	 *
	 * @return ���������� ���������� ����� �� ��� Y
	 *
	 */

	public int getY();

	/**
	 *
	 * ����������� ����� ���� � ����� � ������������ (x,y)
	 *
	 * @param x - ���������� ����� �� ��� X
	 * @param y - ���������� ����� �� ��� Y
	 *
	 */

	public void moveTo(int x, int y);
}
