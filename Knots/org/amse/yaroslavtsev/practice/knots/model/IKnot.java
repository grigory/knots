package org.amse.yaroslavtsev.practice.knots.model;

import java.util.*;

/**
 *
 * ��������� ��������� ����
 *
 * @author Yaroslavtsev Grigory
 *
 */

public interface IKnot {
	
	/**
  	 *
  	 * ��������� ������ ����� ����
  	 *
  	 * @return ������ ����� ����
  	 *
  	 */
	
	public List <IPoint> points();

	/**
	 *
	 * ���������� ����� � ����
	 *
	 * @param x - ���������� ����������� ����� �� ��� X
	 * @param y - ���������� ����������� ����� �� ��� Y
	 * @return ����������� �����
	 *
	 */
	
	
	public IPoint addPoint(int x, int y);
	
	/**
	 *
	 * �������� ����� �� ����
	 *
	 * @param point - ����������� �����
	 *
	 */
	
	public void removePoint(IPoint point);

	/**
  	 *
  	 * ��������� ������ ���� ����
  	 *
  	 * @return ������ ���� ����
  	 *
  	 */	
	
	public List <IEdge> edges();	
	
	/**
  	 *
  	 * ����������, ����� �� ������������ ����� � ������ �����
  	 *
  	 * @param point - �����, � ������� �� ����� ������������ �����
  	 * @return true ���� ����� ������������ ����� � ����� point, false ���� ����� ������� ������
  	 *
  	 */

	public boolean canAddEdge(IPoint point);

	/**
  	 *
  	 * ����������, �����  �� �������� ����� �� ����� ����� � ������
  	 *
  	 * @param source - ������ �����
  	 * @param target - ����� �����
  	 * @return true ���� ����� �������� ����� �� ����� source � ����� target, false ���� ����� �� ����� source � ����� target �������� ������
  	 *
  	 */
	
	public boolean canAddEdge(IPoint source, IPoint target);

	/**
	 *
	 * ���������� ����� � ����
	 *
	 * @param source - ��������� ����� �����
	 * @param target - �������� ����� �����
	 * @return ����������� ����� ��� null � ������, ���� ����� ����� ������� source � target �� ����� ���� ���������
	 *
	 */

	public IEdge addEdge(IPoint source, IPoint target);

	/**
	 *
	 * �������� ����� �� ����
	 *
	 * @param edge - ��������� �����
	 *
	 */

	public void removeEdge(IEdge edge);

	/**
  	 *
  	 * ��������� ������ ����������� ����
  	 *
  	 * @return ������ ����������� ����
  	 *
  	 */
	
	public List <IIntersection> intersections(); 

	/**
	 *
	 * ���������� ������������� ����
	 *
	 * @return true, ���� ���� �� ���� � ��� ��� ���������� ��������, false � ��������� ������	
	 *
	 */

	public boolean isComplete();
	
	/**
	 *
	 * ���������� ������ � ��������� ������� ����
	 *
	 */
	
	public void clear(); 

	/**
	 *
	 * ��������� ����� ������ � ��� �� ���������� ����, �� ��������� �� ����� �������
	 *
	 * @return ����� ��������� ����, ��������� �� ����� �������
	 *
	 */
	
	public IKnot getCopy();
}
