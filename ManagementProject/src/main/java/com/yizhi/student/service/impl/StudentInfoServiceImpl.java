package com.yizhi.student.service.impl;

import com.yizhi.common.domain.LogDO;
import com.yizhi.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhi.student.dao.StudentInfoDao;
import com.yizhi.student.domain.StudentInfoDO;
import com.yizhi.student.service.StudentInfoService;



@Service
public class StudentInfoServiceImpl implements StudentInfoService {



	@Autowired
	private StudentInfoDao studentInfoDao;
	
	@Override
	public StudentInfoDO get(Integer id){
		System.out.println("======service层中传递过来的id参数是：" + id + "======");
		return studentInfoDao.get(id);
	}


	@Override
	public List<StudentInfoDO> list(Map<String, Object> map){
		int i = Integer.parseInt(String.valueOf(map.get("currPage")));
		int n = Integer.parseInt(String.valueOf(map.get("pageSize")));
		map.put("currPage",(i-1)*n);
		map.put("pageSize",n);
		return studentInfoDao.list(map);
	}

	//"===================================================================================="


	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoDO studentInfo){

		//获取添加的时间
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		studentInfo.setAddTime(date);
		//获取当前用户ID
		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		studentInfo.setAddUserid(Math.toIntExact(user.getUserId()));
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoDO studentInfo){
		//获取添加的时间
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		studentInfo.setEditTime(date);

		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
		studentInfo.setEditUserid(Math.toIntExact(user.getUserId()));
		return studentInfoDao.update(studentInfo);
	}
	
	@Override
	public int remove(Integer id){
		return studentInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return studentInfoDao.batchRemove(ids);
	}
	
}
