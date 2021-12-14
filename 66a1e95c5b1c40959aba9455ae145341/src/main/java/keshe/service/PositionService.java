package keshe.service;

import keshe.data.entity.TbPosition;
import keshe.web.PageResult;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface PositionService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbPosition> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 * 增加
	*/
	public void add(TbPosition position);


	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbPosition findOne(Integer id);


	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbPosition position, int pageNum, int pageSize);
	
}
