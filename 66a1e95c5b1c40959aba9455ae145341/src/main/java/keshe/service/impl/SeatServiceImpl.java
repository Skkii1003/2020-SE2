package keshe.service.impl;

import keshe.data.dao.TbSeatDAO;
import keshe.data.entity.TbPosition;
import keshe.data.entity.TbSeat;
import keshe.service.SeatService;
import keshe.web.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SeatServiceImpl implements SeatService {

	private TbSeatDAO seatMapper;

	@Autowired
	public SeatServiceImpl(TbSeatDAO tbSeatDAO) {
		this.seatMapper = tbSeatDAO;
	}
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSeat> findAll() {
		return seatMapper.findAll();
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		Page<TbSeat> page = seatMapper.findAll(pageable);
		return new PageResult(page.getTotalElements(), page.getContent());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbSeat seat) {
		seatMapper.save(seat);
	}

	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbSeat findOne(Integer id){
		return seatMapper.findById(id).get();
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			seatMapper.deleteById(id);
		}		
	}
	
	
	@Override
	public PageResult findPage(TbSeat seat, int pageNum, int pageSize) {
//		PageHelper.startPage(pageNum, pageSize);
//
//		TbSeatExample example=new TbSeatExample();
//		TbSeatExample.Criteria criteria = example.createCriteria();
//
//		if(seat!=null){
//
//		}

		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		Page<TbSeat> page = seatMapper.findAll(pageable);
		return new PageResult(page.getTotalElements(), page.getContent());
	}
	
}
