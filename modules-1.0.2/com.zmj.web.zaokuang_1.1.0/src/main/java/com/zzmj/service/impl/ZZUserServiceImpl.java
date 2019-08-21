package com.zzmj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzmj.mapper.ZZRoleuserMapper;
import com.zzmj.mapper.ZZUserMapper;
import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.entity.ZZOrgEntity;
import com.zzmj.pojo.entity.ZZRoleEntity;
import com.zzmj.pojo.entity.ZZRoleuserEntity;
import com.zzmj.pojo.entity.ZZRoleuserExample;
import com.zzmj.pojo.entity.ZZUserEntity;
import com.zzmj.pojo.entity.ZZUserExample;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZUserService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.MD5Util;
import com.zzmj.util.exception.DoSqlFailedException;

@Service("ZZUserService")
public class ZZUserServiceImpl implements ZZUserService {

	private static final Logger logger = LoggerFactory.getLogger(ZZUserServiceImpl.class);

	@Autowired
	private ZZUserMapper zzUserMapper;

	@Autowired
	private ZZRoleuserMapper roleUserMapper;

	@Autowired
	private ZZRoleUserServiceImpl roleUserServiceImpl;

	@Autowired
	private ZZRoleServiceImpl roleServiceImpl;

	@Autowired
	private ZZOrgServiceImpl orgServiceImpl;

	
	
	
	/**
	 * 修改启用状态
	 * @return 
	 */
	
	public SysResult updateIsUse(String userId,String isuse){
		if (null == userId || userId.equals("")) 
			return new SysResult(ErrorUtil.CODE2001, "用户id参数不能为空!", null);
		if (null == isuse || isuse.equals("")) 
			return new SysResult(ErrorUtil.CODE2001, "启用参数不能为空!", null);

		if ("1".equals(isuse)) {
			isuse = "0";
		} else {
			isuse = "1";
		}
		try {
			int flag=zzUserMapper.updateIsUse(userId,isuse);
			if (flag > 0) {
				return new SysResult(ErrorUtil.CODE2000, "启用状态修改成功", flag);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "启用状态修改失败", null);
			}
		} catch (Exception e) {
			logger.info("修改启用状态出现异常！！");
			e.printStackTrace();
			throw new DoSqlFailedException("修改启用状态出现异常！！！");
		}
	}
	
	/**
	 * 用户注册  判断用户(User_Account 是否存在)
	 */
	@Override
	public SysResult registUserEntity(ZZUserEntity zzUserEntity) {
		
		// 得到用户账号
		String useraAcount = zzUserEntity.getUserAccount();
		int flag = 1;
		// 判断用户账号是否存在
		try {
			flag = this.getifUserAccountExistTwo(useraAcount);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE2001, e.getMessage(), flag);
		}
		
		try {
			zzUserEntity.setId(CodeUtil.createUuid36());
			zzUserEntity.setUserId(CodeUtil.createUuid36());
			String pw = zzUserEntity.getUserPassword();
			zzUserEntity.setUserPassword(MD5Util.md5(pw));
			zzUserEntity.setCreatetime(new Date());
			zzUserEntity.setUpdatetime(new Date());
			zzUserEntity.setIsdel(0);
			zzUserEntity.setIsuse("0");
			//调用的是下面添加用户的方法中  zzUserMapper.insert() 同一个方法
			int n = zzUserMapper.insert(zzUserEntity);
			if (n > 0) {
				return new SysResult(ErrorUtil.CODE2000, "用户注册成功", zzUserEntity);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "用户注册失败", null);
			}
		} catch (Exception e) {
			logger.info("用户注册出现异常");
			e.printStackTrace();
			throw new DoSqlFailedException("用户注册出现异常");
		}
	}
	
	/**
	 * 添加用户的方法
	 *
	 * @param zzUserEntity 用户的实体类
	 * @return
	 */
	@Override
	public SysResult addZZUserEntity(ZZUserEntity userEntity) {
		// 得到用户名
		String account = userEntity.getUserAccount();
		int flag = 1;
		// 判断用户名是否存在
		try {
			flag = this.getifUserAccountExistTwo(account);
		} catch (DoSqlFailedException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE2001, e.getMessage(), flag);
		}
		SysResult result;
		try {
			result = new SysResult();
			ZZUserEntity zzUserEntity = new ZZUserEntity();
			zzUserEntity.setId(CodeUtil.createUuid36());
			zzUserEntity.setUserId(CodeUtil.createUuid36());
			zzUserEntity.setUserName(userEntity.getUserName());
			String pw = userEntity.getUserPassword();
			zzUserEntity.setUserPassword(MD5Util.md5(pw));
			zzUserEntity.setUserJob(userEntity.getUserJob()); // 职位
			zzUserEntity.setUserTel(userEntity.getUserTel()); // 电话
			zzUserEntity.setCreatetime(new Date());
			zzUserEntity.setUpdatetime(new Date());
			zzUserEntity.setUserAccount(account); // 账户名
			zzUserEntity.setOrgId(userEntity.getOrgId());
			zzUserEntity.setIsdel(0); // 0是未删除，1是删除
			zzUserEntity.setIsuse("0");
			int n = zzUserMapper.insert(zzUserEntity);
			if (n > 0) {
				return new SysResult(ErrorUtil.CODE2000, "用户添加成功", zzUserEntity);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "用户添加失败", null);
			}
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			throw new DoSqlFailedException("用户添加出现异常");
		}
	}

	/**
	 * 根据userId修改用户的方法
	 *
	 * @param zzUserEntity 修改之后的实体类
	 * @param userId       用户的Id
	 * @return
	 */
	@Override
	public SysResult updateZZUserEntity(ZZUserEntity userEntity, String userId) {
		try {
			userEntity.setCreatetime(new Date());
			// zzUserEntity.setOrgId(request.getParameter("orgId"));
			if (userEntity.getIsdel() == null) {
				userEntity.setIsdel(0); // 0是未删除，1是删除
			} else {
				userEntity.setIsdel(userEntity.getIsdel()); // 0是未删除，1是删除
			}
			ZZUserExample example = new ZZUserExample();
			example.createCriteria().andUserIdEqualTo(userId);
			int n = zzUserMapper.updateByExampleSelective(userEntity, example);
			if (n > 0) {
				return new SysResult(ErrorUtil.CODE2000, "修改成功!", userEntity);
			} else {
				return new SysResult(ErrorUtil.CODE2001, "用户修改失败", null);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("用户修改，出现异常" + e);
			throw new DoSqlFailedException("用户修改，出现异常");
		}
	}

	@Override
	public SysResult addUserRole(String userId, String roleIds) {
		int result = 0;
		try {
			int n = roleUserServiceImpl.delUserRole(userId); // 根据用户Id删除该用户下，所有的权限信息。
			if (n > 0 || n == 0) {
				String roleId = roleIds;
				String ids[] = roleId.split(",");
				for (int i = 0; i < ids.length; i++) {
					ZZRoleuserEntity zzRoleuserEntity = new ZZRoleuserEntity();
					zzRoleuserEntity.setId(CodeUtil.createUuid36());
					zzRoleuserEntity.setUserId(userId);
					zzRoleuserEntity.setRoleId(ids[i]);
					result = roleUserServiceImpl.addRoleUserEntity(zzRoleuserEntity);
				}
				return new SysResult(ErrorUtil.CODE2000, "权限赋予成功", result);
			} else {
				return new SysResult(ErrorUtil.CODE2000, "权限赋予失败", result);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("用户角色授权异常");
			throw new DoSqlFailedException("用户角色授权异常");
		}
	}

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	@Override
	public List<ZZUserEntity> listZZuser() {
		ZZUserExample example = new ZZUserExample();
		example.createCriteria().andIsdelNotEqualTo(1); // isDel 字段
		// 1是表示删除，所以查询的时候，要把1给过滤掉
		List<ZZUserEntity> list = zzUserMapper.selectByExample(example);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 根据userId 查找用户信息
	 *
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public SysResult getZZUserById(String userId) {
		try {
			ZZUserEntity userEntity = zzUserMapper.selectByUserId(userId);
			if (userEntity.toString().equals("") || userEntity == null) {
				return new SysResult(ErrorUtil.CODE2001, "查询失败", null);
			}
			return new SysResult(ErrorUtil.CODE2000, "查询成功", userEntity);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("根据用户Id查询用户，出现异常");
			throw new DoSqlFailedException("根据用户Id查询用户，出现异常");
		}

	}

	/**
	 * 根据用户id查找该用户所拥有的角色信息
	 *
	 * @param userId 用户id
	 */
	@Override
	public SysResult getUserRole(String userId) {

		try {
			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

			List<ZZRoleEntity> listRole = roleServiceImpl.listRoleEntity(); // 查询出来所有的角色列表

			List<ZZRoleuserEntity> listUserRole = roleUserServiceImpl.listUserRole(userId); // 根据user查找用户角色信息

			for (ZZRoleEntity roleEntity : listRole) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("roleName", roleEntity.getRoleName());
				map.put("roleId", roleEntity.getRoleId());
				map.put("roleDes", roleEntity.getRoleDes());
				map.put("checked", false);
				if (listUserRole != null) {
					for (ZZRoleuserEntity roleuserEntity : listUserRole) {
						if (roleEntity.getRoleId().equals(roleuserEntity.getRoleId())) {
							map.put("checked", true);
						}
					}
				}
				data.add(map);
			}
			return new SysResult(ErrorUtil.CODE2000, "获取该用户所对应角色成功", data);
		} catch (RuntimeException e) {
			logger.info("获取该用户所对应角色出现异常");
			e.printStackTrace();
			throw new DoSqlFailedException("获取该用户所对应角色出现异常");
		}
	}

	/**
	 * 查询总条数  分为四种情况
	 * @param orgId
	 * @param keyword
	 * @return
	 */
	public int getCount(String orgId, String keyword) {
		if(null==orgId || orgId.equals("")){
			if(null==keyword || keyword.equals("")){
				return zzUserMapper.allCount();	//orgId和keyword都为空的情况下
			}
			return zzUserMapper.getCountByKeyWord(keyword);	//orgId为空 keyword不为空的情况下
		}
			
			if(null==keyword || keyword.equals("")){
				return zzUserMapper.getCountByOrgId(orgId);	//orgId不为空 keyword为空的情况下
			}
			Integer count=zzUserMapper.getCountByOrgIdAndKeyWord(orgId,keyword);
			System.out.println(count+"**************");
			return count;
//		return zzUserMapper.getCountByOrgIdAndKeyWord(orgId,keyword);	//orgId keyword都不为空的情况下
		
	}

	/**
	 * @param orgId    集团Org_Id 或者矿井公司Org_Id
	 * @param keyword  关键字
	 * @param pageNo   第几页
	 * @param pageSize 每页显示的条数
	 * @return
	 */
	@Override
	public SysResult listUserPage(String orgId, String keyword, Integer pageNo, Integer pageSize) {
		
		try {
			// 分页查询先查询结果总数
			int count = this.getCount(orgId, keyword);
			System.out.println(count+"++++++++++++++++++_---------------");
			if (pageNo == null) {
				pageNo = 1;
			} else {
				pageNo = pageNo;
			}
			if (pageSize == null) {
				pageSize = 11;
			} else {
				pageSize = pageSize;
			}
			/*if (keyword == null || keyword.equals("")) {
				keyword = "%%";
			} else {
				keyword = "%" + keyword + "%";
			}*/
			
			// 封装到分页查询包装类中  每页第几条开始
			Integer rowNo = (pageNo - 1) * pageSize;
			List<ZZUserEntity> list = listUserByPage(orgId, keyword, rowNo, pageSize);
			for(ZZUserEntity zzUserEntity:list){		//打印查询的数据
				System.out.println(zzUserEntity);
			}
			return new SysResult(ErrorUtil.CODE2000, "获取成功",
					new PageObject<ZZUserEntity>(pageNo, pageSize, count, list));
		} catch (RuntimeException e) {
			logger.info("分页查询出现异常,请检查");
			e.printStackTrace();
			throw new DoSqlFailedException("分页查询出现异常,请检查");
		}

	}

	private List<ZZUserEntity> listUserByPage(String orgId, String keyword, Integer rowNo, Integer pageSize) {
	
		if(null==orgId || orgId.equals("")){
			if(null==keyword || keyword.equals("")){
				System.out.println(orgId+"   "+keyword+"-----");
				return zzUserMapper.listUserAll(rowNo, pageSize);	//orgId和keyword都为空的情况下
			}
			System.out.println(orgId+"   "+keyword+"++++++");
			return zzUserMapper.getlikeKeyWord(keyword, rowNo, pageSize);	//orgId为空 keyword不为空的情况下
		}else{
			
			if(null==keyword || keyword.equals("")){
				System.out.println(orgId+"   "+keyword+"*****");
				return zzUserMapper.listUserByOrgId(orgId, rowNo, pageSize);	//orgId不为空 keyword为空的情况下
			}
		}	
		System.out.println(orgId+"   "+keyword+"#######");
		return zzUserMapper.getListUserByOrgIdAndKeyWord(orgId,keyword,rowNo,pageSize);	//orgId keyword都不为空的情况下
	}

	@Override
	public String getOrgIdByUserId(String userId) {
		String orgId = this.zzUserMapper.selectOrgIdByUserId(userId);
		return orgId;
	}

	/**
	 * 根据用户userId 获取用户的角色，所对应的 所有模块资源
	 *
	 * @param userId 用户Id
	 * @return
	 */
	@Override
	public List<ZZUserEntity> getUserMoudel(String userId) {
		List<ZZUserEntity> result = zzUserMapper.getUserMoudel(userId);
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.zzmj.service.ZZUserService#getifUserAccountExist(java.lang.String)
	 * 用着不起作用
	 */
	@Override
	public int getifUserAccountExist(String account) throws DoSqlFailedException {
		ZZUserExample example = new ZZUserExample();
		example.createCriteria().andUserAccountEqualTo(account);
		example.createCriteria().andIsdelEqualTo(1);
		int flag = this.zzUserMapper.countByExample(example);
		if (flag > 0)
			throw new DoSqlFailedException("该用户名已存在!");
		return flag;
	}
	
	/**
	 * 新增、注册用户的时候  如果数据库中有重名用户且该用户如果iSDel为1，则可以新增。  
	 * 如果isDel为0，不可以新增
	 */
	@Override
	public int getifUserAccountExistTwo(String account){
		
		try {
			int flag = this.zzUserMapper.selectCountByAccount(account);
			if (flag >0 ) {
				throw new DoSqlFailedException("该用户名已存在!");
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("该用户名已存在!!", e);
			throw new DoSqlFailedException("该用户名已存在!!!");
		}
		
	}

	@Override
	public int delUser(String userId) throws DoSqlFailedException {
		ZZUserEntity zzUserEntity = new ZZUserEntity();
		zzUserEntity.setIsdel(1); // 0是未删除，1是删除
		SysResult flag1 = this.updateZZUserEntity(zzUserEntity, userId);
		if (flag1.getData() == null) {
			throw new DoSqlFailedException("用户删除失败");
		}

		ZZRoleuserExample example = new ZZRoleuserExample();
		example.createCriteria().andUserIdEqualTo(userId);
		int flag2 = this.roleUserMapper.deleteByExample(example);
		return flag2;
	}

	@Override
	public int getIfPasswordRight(String userId, String password) {
		ZZUserExample example = new ZZUserExample();
		example.createCriteria().andUserIdEqualTo(userId).andUserPasswordEqualTo(MD5Util.md5(password));
		int i = this.zzUserMapper.countByExample(example);
		return i;
	}
}
