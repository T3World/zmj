package com.zzmj.service.impl;

import com.zzmj.mapper.ZZWorkfaceGeographyMapper;
import com.zzmj.pojo.entity.ZZWorkfaceGeography;
import com.zzmj.pojo.vo.PageObject;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZWorkfaceGeographyService;
import com.zzmj.util.CodeUtil;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.FormatDateUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZZWorkfaceGeographyServiceImpl implements ZZWorkfaceGeographyService {

    private static Logger logger = Logger.getLogger(ZZWorkfaceGeographyServiceImpl.class);

    @Autowired
    private ZZWorkfaceGeographyMapper zzWorkfaceGeographyMapper;

    /**
     * 添加矿井公司地理信息【实用】
     * @param zzWorkfaceGeography
     * @return
     */
    @Override
    public SysResult addZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography) {
        try {
            zzWorkfaceGeography.setId(CodeUtil.createUuid36());  //自动生成id
            zzWorkfaceGeography.setUpdateTime(FormatDateUtil.dataFormat(new Date()));  //自动生成时间
            zzWorkfaceGeography.setIsDel("0");  //设置isDel为0
            String zz_orgId = zzWorkfaceGeography.getOrgId();
            //根据orgId查询此条数据是否存在
            List<ZZWorkfaceGeography> zz_list = zzWorkfaceGeographyMapper.listByOrgId(zz_orgId);
            //如果结果集不为空，则说明有该条数据，不能进行添加操作
            if (zz_list.size() > 0 && zz_list.get(0).getIsDel().equals("0")) {
                return new SysResult(ErrorUtil.CODE2001, "添加矿井公司地理信息失败！", null);
            }
            int flag = zzWorkfaceGeographyMapper.insertSelective(zzWorkfaceGeography);
            if (flag > 0) {
                return new SysResult(ErrorUtil.CODE2000, "添加矿井公司地理信息成功！", zzWorkfaceGeography);
            } else {
                return new SysResult(ErrorUtil.CODE2001, "添加矿井公司地理信息失败！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("矿井公司地理信息添加失败，出现异常！", e);
            throw new DoSqlFailedException("矿井公司地理信息添加失败！");
        }
    }

    /**
     * 添加矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param sortCode
     * @return
     */
    @Override
    public SysResult addZZGroupsGeography(String groupId, String orgLongitude, String orgLatitude, String sortCode) {
        try {
            String id = CodeUtil.createUuid36();
            String updateTime = FormatDateUtil.dataFormat(new Date());
            String isDel = "0";
            //根据该条矿井集团信息是否存在
            List<ZZWorkfaceGeography> zz_list = zzWorkfaceGeographyMapper.listByOrgId(groupId);
            //如果结果集不为空，则说明有该条数据，不能进行添加操作
            if (zz_list.size() > 0 && zz_list.get(0).getIsDel().equals("0")) {
                return new SysResult(ErrorUtil.CODE2001, "添加矿井集团地理信息失败！", null);
            }
            int flag = zzWorkfaceGeographyMapper.insertSelective2(id, groupId, orgLongitude, orgLatitude, sortCode, updateTime, isDel);
            if (flag > 0) {
                return new SysResult(ErrorUtil.CODE2000, "矿井集团地理信息添加成功！", null);
            } else {
                return new SysResult(ErrorUtil.CODE2001, "矿井集团地理信息添加失败！", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("矿井集团地理信息添加失败，出现异常！", e);
            throw new DoSqlFailedException("矿井集团地理信息添加失败！");
        }
    }

    /**
     * 删除工作面地理信息，根据id更改isDel状态为1
     * @param id
     * @return
     */
    @Override
    public SysResult delZZWorkfaceGeography(String id) {
        try {
            int flag = zzWorkfaceGeographyMapper.deleteByPrimaryKey(id);
            if (flag < 1) {
                return new SysResult(ErrorUtil.CODE2001, "删除工作面地理信息失败", null);
            }
            return new SysResult(ErrorUtil.CODE2000, "删除工作面地理信息成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除工作面地理信息失败，出现异常", e);
            throw new DoSqlFailedException("删除工作面设备失败");
        }
    }

    /**
     * 更新工作面地理信息
     * @param zzWorkfaceGeography
     * @return
     */
    @Override
    public SysResult updateZZWorkfaceGeography(ZZWorkfaceGeography zzWorkfaceGeography) {
        try {
            zzWorkfaceGeography.setUpdateTime(FormatDateUtil.dataFormat(new Date()));
            int flag = zzWorkfaceGeographyMapper.updateByPrimaryKeySelective(zzWorkfaceGeography);
            if (flag > 0) {
                return new SysResult(ErrorUtil.CODE2000, "更改工作面地理信息成功", zzWorkfaceGeography);
            } else {
                return new SysResult(ErrorUtil.CODE2001, "更改工作面地理信息失败", zzWorkfaceGeography);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("工作面地理信息更改失败，出现异常", e);
            throw new DoSqlFailedException("工作面地理信息更改失败");
        }
    }

    /**
     * [查询表中所有内容](只是测试)
     * @return
     */
    @Override
    public List<ZZWorkfaceGeography> selectAll() {
        try {
            return zzWorkfaceGeographyMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询所有地理信息失败，出现异常");
            throw new DoSqlFailedException("查询所有地理信息失败");
        }
    }

    /**
     * 根据集团id查询该集团下所有矿井地理信息
     * @return
     */
    @Override
    public List<ZZWorkfaceGeography> selectByGroupId(String orgPId) {
        try {
            return zzWorkfaceGeographyMapper.selectByGroupId(orgPId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询所有矿井地理信息失败，出现异常");
            throw new DoSqlFailedException("查询所有矿井地理信息失败");
        }
    }

    /**
     * 查询所有集团下的矿井地理信息
     * @return
     */
    @Override
    public List<ZZWorkfaceGeography> selectAllByGroup() {
        try {
            return  zzWorkfaceGeographyMapper.selectAllByGroup();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询所有集团下的矿井地理信息失败，出现异常");
            throw new DoSqlFailedException("查询所有集团下的矿井地理信息失败");
        }
    }

    /**
     * 分页查询地理信息【实用】
     * @param groupId   集团id
     * @param keyword   关键字
     * @param pageNo    页数
     * @param pageSize  每页显示的数量
     * @return
     */
    @Override
    public SysResult listGeography(String groupId, String keyword, Integer pageNo, Integer pageSize) {
        try {
            //分页查询，先查询结果总数
            int count = this.countGeography(groupId, keyword);
            //为pageNo、pageSize赋默认值
            if (null == pageNo || pageNo == 0)
                pageNo = 1;
            if (null == pageSize || pageSize == 0)
                pageSize = 11;
            //封装到分页查询包装类中
            Integer rowNo = (pageNo - 1) * pageSize;
            List<ZZWorkfaceGeography> list = listGeographyByPage(groupId, keyword, rowNo, pageSize);
            return new SysResult(ErrorUtil.CODE2000, "分页查询成功", new PageObject<ZZWorkfaceGeography>(pageNo, pageSize, count, list));
        } catch (RuntimeException e) {
            logger.error("地理信息查询失败！", e);
            throw new DoSqlFailedException("地理信息查询失败，抛出异常", e);
        }
    }

    private List<ZZWorkfaceGeography> listGeographyByPage(String groupId, String keyword, Integer rowNo, Integer pageSize) {
        if (null == groupId || groupId.equals("")) {
            if (null == keyword || keyword.equals("")) {
                List<ZZWorkfaceGeography> list = zzWorkfaceGeographyMapper.listGeography(rowNo, pageSize);
                logger.info(String.valueOf(list.size()));
                return list;
            }
            return zzWorkfaceGeographyMapper.listGeographyByKeyword(keyword, rowNo, pageSize);
        } else {
            if (null == keyword || keyword.equals("")) {
                List<ZZWorkfaceGeography> list = zzWorkfaceGeographyMapper.listGeographyByGroupId(groupId, rowNo, pageSize);
                logger.info(String.valueOf(list.size()));
                return list;
            }
        }
        return zzWorkfaceGeographyMapper.listGeographyByGroupIdAndByKeyword(groupId, keyword, rowNo, pageSize);
    }

    //分页查询的总数
    private int countGeography(String groupId, String keyword) {
        if (null == groupId || groupId.equals("")) {
            if (null == keyword || keyword.equals("")) {
                //当groupId、关键字都为空时，查询表中所有个数
                return zzWorkfaceGeographyMapper.countGeography();
            }
            //当groupId为空、关键字不为空时，查询符合关键字的条数
            return zzWorkfaceGeographyMapper.countGeographyByKeyword(keyword);
        }
        //当groupId不为空、关键字为空时，查询符合groupId的条数
        if (null == keyword || keyword.equals("")) {
            return zzWorkfaceGeographyMapper.countGeographyByGroupId(groupId);
        }
        //当groupId、关键字都不为空时，查询符合双条件的数量
        return zzWorkfaceGeographyMapper.countGeographyByGroupIdAndByKeyword(groupId, keyword);
    }

    /**
     * 查询，不分页【实用】
     * @param groupId
     * @return
     */
    @Override
    public SysResult listGeographyNoPage(String groupId) {
        try {
            if (null == groupId || groupId.equals("")) {
                return new SysResult(ErrorUtil.CODE2001, "查询失败，没有groupId", null);
            }
            List<ZZWorkfaceGeography> list = zzWorkfaceGeographyMapper.listGeographyByGroupIdNoPage(groupId);
            if (list.size() > 0) {
                return new SysResult(ErrorUtil.CODE2000, "查询成功", list);
            } else {
                return new SysResult(ErrorUtil.CODE5000, "查询失败", null);
            }
        } catch (RuntimeException e) {
            logger.error("地理信息查询失败！", e);
            throw new DoSqlFailedException("地理信息查询失败，抛出异常", e);
        }
    }

    /**
     * 假删除，根据orgId更新IsDel的状态为1【实用】
     * @param orgId
     * @return
     */
    @Override
    public SysResult updateIsDel(String orgId) {
        try {
            int flag = zzWorkfaceGeographyMapper.updateIsDel(orgId);
            if (flag > 0) {
                return new SysResult(ErrorUtil.CODE2000, "删除工作面地理信息成功", null);
            } else {
                return new SysResult(ErrorUtil.CODE2001, "删除工作面地理信息失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除工作面地理信息失败，出现异常", e);
            throw new DoSqlFailedException("删除工作面地理信息失败");
        }
    }

    /**
     * 批量删除【实用】
     * @param ids
     * @return
     */
    @Override
    public SysResult batDelGeography(String ids) {
        //字符串转为数组
        String[] idss = ids.split(",");
        int count = 0;
        for (String id : idss) {
            int flag = zzWorkfaceGeographyMapper.updateIsDel(id);
            if (flag > 0) {
                count += flag;
            } else {
                break;
            }
        }
        if (count == idss.length) {
            return new SysResult(ErrorUtil.CODE2000, "批量删除工作面地理信息成功", null);
        } else {
            return new SysResult(ErrorUtil.CODE2001, "批量删除工作面地理信息失败", null);
        }
    }

    /**
     * 编辑矿井公司地理信息【实用】
     * @param orgName
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    @Override
    public SysResult updateZZWorkfaceGeographies(String orgName, String orgLongitude, String orgLatitude, String currentTime, String sortCode) {
        currentTime = FormatDateUtil.dataFormat(new Date());
        int flag = zzWorkfaceGeographyMapper.updateZZWorkfaceGeographies(orgName, orgLongitude, orgLatitude, currentTime, sortCode);
        if (flag > 0) {
            return new SysResult(ErrorUtil.CODE2000, "编辑矿井公司地理信息成功！", null);
        } else {
            return new SysResult(ErrorUtil.CODE2001, "编辑矿井公司地理信息失败！", null);
        }
    }

    /**
     * 编辑矿井集团地理信息【实用】
     * @param groupId
     * @param orgLongitude
     * @param orgLatitude
     * @param currentTime
     * @param sortCode
     * @return
     */
    @Override
    public SysResult updateZZGroupsGeographies(String groupId, String orgLongitude, String orgLatitude, String currentTime, String sortCode) {
        currentTime = FormatDateUtil.dataFormat(new Date());
        int flag = zzWorkfaceGeographyMapper.updateZZGroupsGeographies(groupId, orgLongitude, orgLatitude, currentTime, sortCode);
        if (flag > 0) {
            return new SysResult(ErrorUtil.CODE2000, "编辑矿井集团地理信息成功！", null);
        } else {
            return new SysResult(ErrorUtil.CODE2001, "编辑矿井集团地理信息失败！", null);
        }
    }

}