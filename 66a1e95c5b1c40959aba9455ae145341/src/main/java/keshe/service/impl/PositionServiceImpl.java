package keshe.service.impl;

import keshe.data.dao.TbPositionDAO;
import keshe.data.entity.TbPosition;
import keshe.service.PositionService;
import keshe.web.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class PositionServiceImpl implements PositionService {

	private TbPositionDAO positionMapper;

	@Autowired
	public PositionServiceImpl(TbPositionDAO tbPositionDAO) {
		this.positionMapper = tbPositionDAO;
	}

	/**
	 * 查询全部
	 */
	@Override
	public List<TbPosition> findAll() {
		return positionMapper.findAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		Page<TbPosition> page = positionMapper.findAll(pageable);
		return new PageResult(page.getTotalElements(), page.getContent());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbPosition position) {
		positionMapper.save(position);
	}

	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbPosition findOne(Integer id){
		return positionMapper.findById(id).get();
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			positionMapper.deleteById(id);
		}		
	}
	
	
	@Override
	public PageResult findPage(TbPosition position, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbPositionExample example=new TbPositionExample();
//		TbPositionExample.Criteria criteria = example.createCriteria();
//
//		if(position!=null){
//						if(position.getPassword()!=null && position.getPassword().length()>0){
//				criteria.andPasswordLike("%"+position.getPassword()+"%");
//			}
//			if(position.getLeibie()!=null && position.getLeibie().length()>0){
//				criteria.andLeibieLike("%"+position.getLeibie()+"%");
//			}
//
//		}

		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		Page<TbPosition> page = positionMapper.findAll(pageable);
		return new PageResult(page.getTotalElements(), page.getContent());
	}
	
}
