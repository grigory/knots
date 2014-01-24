package org.amse.yaroslavtsev.practice.knots.model;
           
/**
 *
 * ��������� ����� ����
 *
 * @author Yaroslavtsev Grigory
 *
 */


public interface IEdge extends IElement {
	
	/**
	 *
	 * ��������� ��������� ����� �����
	 *
	 * @return ��������� ����� ����� 
	 *
	 */	

	public IPoint getSource();
	
	/**
	 *
	 * ��������� �������� ����� �����
	 *
	 * @return �������� ����� �����
	 *
	 */
	
	public IPoint getTarget();

	/**
	 *
	 * ��������� ��������� ����� �� ��������������� - ��������� � �������� ����� �������� �������
	 *
	 */
	
	public void rotate();
}
