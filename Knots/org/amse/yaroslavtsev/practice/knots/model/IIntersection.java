package org.amse.yaroslavtsev.practice.knots.model;

/**
 *
 * ��������� ����������� ���� ����
 *
 * @author Yaroslavtsev Grigory
 *
 */

public interface IIntersection extends IElement {
	
	/**
	 *
	 * ���������� ������� ����� �����������
	 *
	 * @return ������� ����� �����������	
	 *
	 */
	
	IEdge getUpper();
	
	/**
	 *
	 * ���������� ������ ����� �����������
	 *
	 * @return ������ ����� �����������	
	 *
	 */
	
	IEdge getLower();

	/**
	 *
	 * ��������� ���� ����������� �� ��������������� - ������� � ������ ����� �������� �������
	 *
	 */

	void changeType();
}
