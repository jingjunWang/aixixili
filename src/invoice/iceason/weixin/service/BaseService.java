package invoice.iceason.weixin.service;

import invoice.iceason.weixin.dao.BaseDao;
import invoice.iceason.weixin.entityjs.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class BaseService<T> {

	private BaseDao<T> baseDao;

	public BaseService(BaseDao<T> baseDao) {
		super();
		this.baseDao = baseDao;
	}

	public BaseService() {
		super();
	}

	public boolean saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		return baseDao.saveOrUpdate(entity);
	}
	public boolean saveOrUpdateImmediately(T entity) {
		// TODO Auto-generated method stub
		return baseDao.saveOrUpdateImmediately(entity);
	}
	public int save(T entity) {
		// TODO Auto-generated method stub
		return baseDao.save(entity);
	}

	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		return baseDao.delete(entity);
	}

	public boolean deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.deleteById(id);
	}

	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(id);
	}

	public List<T> getAll() {
		// TODO Auto-generated method stub
		return baseDao.getAll();
	}

	public List<T> findBy(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.findBy(propertyName, value);
	}

	public T findUniqueBy(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.findUniqueBy(propertyName, value);
	}

	public List<T> findById(List<Serializable> ids) {
		// TODO Auto-generated method stub
		return baseDao.findById(ids);
	}

	public Page<T> getAll(Page<T> page) {
		// TODO Auto-generated method stub
		return baseDao.getAll(page);
	}
	/**
	 * 根据两个字段获取数据
	 * @param name 第一个字段名称
	 * @param value 第一个字段值
	 * @param name2  第二个字段名称
	 * @param value2 第二个字段值
	 * @return
	 */
	public List<T> findBy(String name, Object value,String name2, Object value2) {
			return baseDao.findBy( name,  value, name2,  value2);
	}

	public boolean deleteBy(String propertyName, Object value){
		return baseDao.deleteBy(propertyName, value);
	}
	/**
	 * 根据SQL查询 数据库的 任意表 获得object
	 * 
	 * @author LRF 2015-7-23 lrf
	 * @param sql
	 * @return
	 */
	public List<Object[]> getObjectListBySql(String sql) {
		return baseDao.getObjectListBySql(sql);
	}
	/**
	 * 根据SQL查询 数据库的 任意表 获得添加时应输入的ID
	 * 
	 * @author 
	 * @param 
	 * @return
	 */
	public int getMaxId(){
		return baseDao.getMaxId();
	}
	
	public T findByT(String name, Object value,String name2, Object value2) {
		// TODO Auto-generated method stub
		return baseDao.findByT(name, value, name2, value2);
	}
	public List<T> findByOrderBy(String propertyName, Object value, String orderByPropertyName) {
		// TODO Auto-generated method stub
		return baseDao.findByOrderBy(propertyName, value, orderByPropertyName);
	}
	public List<T> findListBy(String name, Object value,String name2, Object value2, String name3, Object value3){
		return baseDao.findListBy(name, value, name2, value2, name3, value3);
	}
	public boolean saveList(List<T> list){
		return baseDao.saveList(list);
	}
	public List<T> findByCondition(String sqlCondition) {
		return baseDao.findByCondition(sqlCondition);
	}
	public List<T> getListByTimePeriod(String name, Date startTime, Date endTime){
		return baseDao.getListByTimePeriod(name, startTime, endTime);
	}
	public boolean saveManyData(List<T> objList){
		return baseDao.saveManyData(objList);
	}
}
