package invoice.iceason.weixin.dao;

import invoice.iceason.debug.DebugLog;
import invoice.iceason.utility.ConstantStaticVariablesSet;
import invoice.iceason.weixin.entityjs.Page;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
@Repository("baseDao")
public class BaseDao<T>  {
	/**
	 * 不让继承
	 */
	protected Class<T> entityClass;

	public BaseDao() {
		super();
		entityClass = getGenericType(0);
	}
	@Resource
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取泛型中的T实例
	 * @Description: TODO 获得泛型类中T的实例
	 * @author
	 * @return 泛型类中T的实例
	 * @date 2013年11月9日 下午10:27:10
	 * @version V1.0
	 */
	private Class getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}
	/**
	 * 保存或修改
	 * @param entity
	 * @return
	 */
	public boolean saveOrUpdate(T entity) {
		Session session = getSession();
		try {
			 session.saveOrUpdate(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.saveOrUpdate()",e);
			return false;
		}
	}
	/**
	 * 立即保存或修改，大批量添加修改时调用，提高速度
	 * @param entity
	 * @return
	 */
	public boolean saveOrUpdateImmediately(T entity) {
		Session session = getSession();
		try {
			 session.saveOrUpdate(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.saveOrUpdate()",e);
			return false;
		} finally {
			session.flush();
			session.clear();
		}
	}
	/**
	 * 修改数据
	 * @param entity
	 * @return
	 */
	public boolean updateOrClear(T entity) {
		Session session = getSession();
		try {
			session.clear();
			session.update(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.updateOrClear()",e);
			return false;
		}
	}
	/**
	 * 修改数据
	 * @param entity
	 * @return
	 */
	public boolean update(T entity) {
		Session session = getSession();
		try {
			session.update(entity);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.update()",e);
			return false;
		}
	}
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public Integer save(T entity) {
		Session session = getSession();
		Integer i=0;
		try {
			i=(Integer)session.save(entity);
			return i;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.save()",e);
			return i;
		}
	}
	public Integer saves(T entity) throws HibernateException {
		Session session = getSession();
		Integer i=0;
		
			
			try {
				i=(Integer)session.save(entity);
				// session.saveOrUpdate(entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				DebugLog.logger.error("error in BaseDao.saves()",e);
			}
			return i;
	}

	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity) {
		Session session = getSession();
		try {
			if(entity != null){
				session.delete(entity);
			}
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.delete()",e);
			return false;
		}
	}
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @return
	 */
	public boolean deleteById(Serializable id) {	
		Session session = getSession();
		try {
			session.delete(session.get(entityClass, id));
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.deleteById()",e);
			return false;
		}
	}
	
	/**
	 * 根据条件删除数据，返回删除条数
	 */
	public boolean deleteBy(String propertyName, Object value){
		Session session = getSession();
		Query query = null;
		try {
			List<T> list = findBy(propertyName, value);
			if(list != null && list.size() > 0){
				for (T entity : list) {
					session.delete(entity);
				}
			}
			/*String sql = "DELETE " + table + " WHERE " + propertyName + " = " + value;
			count = session.createSQLQuery(sql).executeUpdate();*/
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.deleteBy()",e);
			throw new RuntimeException("没有得到全部对象！");
		}
	}
	
	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	public T get(Serializable id) {
		Session session = getSession();
		T t = null;
		try {
			t = (T) session.get(entityClass, id);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.get()", e);
		}
		return t;
	}
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<T> getAll() {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName());
			query.setCacheable(true);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll()", e);
			throw new RuntimeException("没有得到全部对象！");
		}
	}
	public List<T> getAllU() {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName());
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll()", e);
			throw new RuntimeException("没有得到全部对象！");
		}
	}
	/**
	 * 获取所有数据并根据字段排序
	 * @return
	 */
	public List<T> getAllAndOrderBy(String orderByPropertyName) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName() + " order by " + orderByPropertyName);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAllAndOrderBy()", e);
			throw new RuntimeException("没有得到全部对象！");
		}
	}
	/**
	 * 根据字段值获取数据
	 * @param propertyName 字段名称
	 * @param value 字段值
	 * @return
	 */
	public List<T> findBy(String propertyName, Object value) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + propertyName + "=?");
			query.setCacheable(true);
			query.setParameter(0, value);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findBy()", e);
			return null;
		}
	}
	/**
	 * 根据字段值获取唯一结果数据
	 * @param propertyName 字段名
	 * @param value 字段值
	 * @return
	 */
	public T findUniqueBy(String propertyName, Object value) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + propertyName + "=?");
			query.setCacheable(true);
			query.setParameter(0, value);
			return (T) query.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findUniqueBy()", e);
			return null;
		}
	}
	/**
	 * 根据id集合获取所有id对应的数据
	 * @param ids 所有id的集合
	 * @return 所有id对应的数据集合
	 */
	public List<T> findById(List<Serializable> ids) {
		Session session = getSession();
		Query query = null;
		String sql = ("from " + entityClass.getSimpleName() + " where ID IN " + ids)
				.replace("[", "(").replace("]", ")");
		try {
			query = session.createQuery(sql);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findById()", e);
			throw new RuntimeException("没有得到相等的对象！");
		}
	}
	/**
	 * 根据分页得到数据
	 * @param page
	 * @return
	 */
	public Page<T> getAll(Page<T> page) {
		Session session = getSession();
		
		try {
			// 分页列表数据
			Query query = session
					.createQuery(" from " + this.entityClass.getSimpleName() +" order by id desc")
					.setFirstResult(page.getFirst())
					.setMaxResults(page.getPageSize());
			//query.setCacheable(true);
			List<T> list=query.list();
			page.setResult(list);
			
			// 总的记录数
			query = session.createSQLQuery(" select count(*) from "
					+ this.entityClass.getSimpleName());
			Long totalCount = (Long) query.uniqueResult();
			page.setTotalCount(new Integer(totalCount.toString()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll() 1",e);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll() 2",e);
			throw new RuntimeException("没有得到相等的对象！"+e);
		}
		return page;
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
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + name + "=? and "+name2+"=?");
			query.setCacheable(true);
			query.setParameter(0, value);
			query.setParameter(1, value2);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findBy()", e);
			return new ArrayList<T>();
		}
	}
	/**
	 * 根据两个字段获取数据
	 * @param name 第一个字段名称
	 * @param value 第一个字段值
	 * @param name2  第二个字段名称
	 * @param value2 第二个字段值
	 * @return
	 */
	public T findByT(String name, Object value,String name2, Object value2) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + name + "=? and "+name2+"=?");
			query.setCacheable(true);
			query.setParameter(0, value);
			query.setParameter(1, value2);
			return (T)query.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findByT() " , e);
			return null;
		}
	}
	/**
	 * 根据三个字段值得到数据
	 * @param name 第一个字段名称
	 * @param value 第一个字段值
	 * @param name2  第二个字段名称
	 * @param value2 第二个字段值
	 * @param name3 第三个字段名称
	 * @param value3 第三个字段值
	 * @return
	 */
	public T findBy(String name, Object value,String name2, Object value2,String name3,Object value3) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + name + "=? and "+name2+"=? and "+name3+"=?");
			query.setCacheable(true);
			query.setParameter(0, value);
			query.setParameter(1, value2);
			query.setParameter(2, value3);
			return (T)query.uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findBy()" , e);
			return null;
		}
	}
	/**
	 * 根据三个字段值得到数据
	 * @param name 第一个字段名称
	 * @param value 第一个字段值
	 * @param name2  第二个字段名称
	 * @param value2 第二个字段值
	 * @param name3 第三个字段名称
	 * @param value3 第三个字段值
	 * @return
	 */
	public List<T> findListBy(String name, Object value,String name2, Object value2,String name3,Object value3) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + name + "=? and "+name2+"=? and "+name3+"=?");
			query.setCacheable(true);
			query.setParameter(0, value);
			query.setParameter(1, value2);
			query.setParameter(2, value3);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findListBy()", e);
			throw new RuntimeException("没有得到相等的对象！");
		}
	}
	/**
	 * 根据SQL查询 数据库的  任意表 获得object
	 * @author LRF	 2015-7-23  lrf
	 * @param sql
	 * @return
	 */
	public Object getObjectList(String sql){
		Session session = getSession();
		Query query = null;
		try {
			query = session.createSQLQuery(sql).addScalar("id",StandardBasicTypes.INTEGER).addScalar("value",StandardBasicTypes.STRING);
			
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getObjectList()", e);
			throw new RuntimeException("没有得到相等的对象！");
		}
	}
	
	
	/**
	 * 根据SQL查询 数据库的  任意表 获得object
	 * @author LRF	 2015-7-23  lrf
	 * @param sql
	 * @return
	 */
	public List<Object[]> getObjectListBySql(String sql){
		Session session = getSession();
		Query query = null;
		try {
			query = session.createSQLQuery(sql);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getObjectListBySql()", e);
			throw new RuntimeException("没有得到相等的对象！");
		}
	}
	public int getMaxId(){
		Session session = getSession();
		Query query = null;
		try {
			String sql = "select MAX(id) from " + entityClass.getSimpleName();
			query = session.createQuery(sql);
			String id = query.uniqueResult().toString();
			if(id!=null&&!id.equals("")){
				return Integer.parseInt(id) + 1;
			}
			return 1;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getMaxId()", e);
			return 0;
		}
	}
	/**
	 * 根据字段值获取数据 根据字段名排序
	 * @param propertyName 字段名称
	 * @param value 字段值
	 * @return
	 */
	public List<T> findByOrderBy(String propertyName, Object value, String orderByPropertyName) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + propertyName + "=? order by "+ orderByPropertyName);
			query.setCacheable(true);
			query.setParameter(0, value);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findByOrderBy()", e);
			throw new RuntimeException("没有得到相等的对象！");
		}
	}
	/**
	 * 保存集合
	 * @param entity
	 * @return
	 */
	public boolean saveList(List<T> list) {
		try {
			for (T t : list) {
				saveOrUpdate(t);
			}
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.saveList()", e);
			return false;
		}
	}
	
	/**
	 * 根据两个字段获取数据
	 * @param name 第一个字段名称
	 * @param value 第一个字段值
	 * @param name2  第二个字段名称
	 * @param value2 第二个字段值
	 * @return
	 */
	public List<T> findByTime(String name, Object value,String name2, Object value2) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + name + "=? and "+name2+">?");
			query.setCacheable(true);
			query.setParameter(0, value);
			query.setParameter(1, value2);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findByTime()", e);
			return null;
		}
	}
	
	/**
	 * 根据字段值获取相似的数据
	 * @param propertyName 字段名称
	 * @param value 字段值
	 * @return
	 */
	public List<T> findByLike(String propertyName, Object value) {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + propertyName + " like '%"+value+"%'");
			query.setCacheable(true);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findBy()", e);
			throw new RuntimeException("没有得到相等的对象！");
		}
	}
	
	public List<T> findByCondition(String sqlCondition) {
		Session session = getSession();
		Query query = null;
		String sql = ("from " + entityClass.getSimpleName() + sqlCondition);
		try {
			query = session.createQuery(sql);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findByCondition()", e);
			return null;
		}
	}
	
	/**
	 * 根据条件删除数据，返回删除条数
	 */
	public boolean deleteBy(String name, Object value, String name2, Object value2){
		Session session = getSession();
		Query query = null;
		try {
			List<T> list = findBy(name, value, name2, value2);
			if(list != null && list.size() > 0){
				for (T entity : list) {
					session.delete(entity);
				}
			}
			/*String sql = "DELETE " + table + " WHERE " + propertyName + " = " + value;
			count = session.createSQLQuery(sql).executeUpdate();*/
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.deleteBy()",e);
			throw new RuntimeException("删除"+name+"="+value+","+name2+"="+value2+"的"+entityClass.getSimpleName()+"数据出错");
		}
	}
	
	/**
	 * 根据分页得到数据
	 * @param page
	 * @return
	 */
	public Page<T> getAll(Page<T> page,String sql) {
		Session session = getSession();
		
		try {
			// 分页列表数据
			Query query = session
					.createQuery(" from " + this.entityClass.getSimpleName() +" "+sql+" order by PLU asc")
					.setFirstResult(page.getFirst())
					.setMaxResults(page.getPageSize());
			//query.setCacheable(true);desc
			List<T> list=query.list();
			page.setResult(list);
			
			// 总的记录数
			query = session.createQuery("select count(*) from " + this.entityClass.getSimpleName()+" "+sql);
			Long totalCount = (Long) query.uniqueResult();
			page.setTotalCount(new Integer(totalCount.toString()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll() 1",e);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll() 2",e);
			throw new RuntimeException("没有得到相等的对象！"+e);
		}
		return page;
	}
	
	public List<T> getListByTimePeriod(String name, Date startTime, Date endTime){
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName()
					+ " where " + name + ">? and "+name+"<?");
			query.setCacheable(true);
			query.setParameter(0, startTime);
			query.setParameter(1, endTime);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.findByTime()", e);
			return null;
		}
	}
	
	public boolean saveManyData(List<T> objList){
		Session session = getSession();
		try {
			for (int i = 0; i < objList.size(); i++) {
				session.save(objList.get(i));
				/*if(i%100==0){   //每一千条刷新并写入数据库  
	                session.flush();  
	                session.clear();  
	            }*/
			}
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.saveManyData()", e);
			return false;
		}
	}
	
	/**
	 * 将SQL执行到数据库
	 * @author LRF	 2015-7-23  lrf
	 * @param sql
	 * @return
	 */
	public boolean executeSQL(String sql){
		Session session = getSession();
		Query query = null;
		try {
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getObjectListBySql()", e);
			return false;
		}
	}
	
	/**
	 * 获取所有数据,不缓存
	 * @return
	 */
	public List<T> getAllNoCache() {
		Session session = getSession();
		Query query = null;
		try {
			query = session.createQuery(" from " + entityClass.getSimpleName());
			query.setCacheable(false);
			return query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DebugLog.logger.error("error in BaseDao.getAll()", e);
			throw new RuntimeException("没有得到全部对象！");
		}
	}
	
}
