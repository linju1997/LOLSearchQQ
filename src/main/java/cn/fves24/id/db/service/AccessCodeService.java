package cn.fves24.id.db.service;

import cn.fves24.id.db.mapper.AccessCodeMapper;
import cn.fves24.id.entity.model.AccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 查询码 Service
 *
 * @author Administrator
 */
@Service
public class AccessCodeService {

    private AccessCodeMapper accessCodeMapper;

    @Autowired
    public AccessCodeService(AccessCodeMapper accessCodeMapper) {
        this.accessCodeMapper = accessCodeMapper;
    }

    /**
     * 查询所有查询码
     * @return 所有查询码
     */
    public List<AccessCode> findAccessCodes() {
        return accessCodeMapper.getAllAccessCode();
    }

    /**
     * 查询剩余次数
     * @param code 查询码
     * @return -1 代表查询码不存在，为整数代表剩余次数
     */
    public int remainTimes(String code) {
        Integer timesByCode = accessCodeMapper.getTimesByCode(code);
        if (timesByCode == null) {
            return -1;
        }
        return timesByCode;
    }

    /**
     * 查询码是否存在
     * @param code 查询码
     * @return true 代表存在，false 代表不存在
     */
    public boolean existsByCode(String code) {
        AccessCode exist = accessCodeMapper.exist(code);
        return exist != null;
    }

    /**
     * 添加查询码
     * @param accessCode 查询码
     * @return 添加状态
     */
    public boolean add(AccessCode accessCode) {
        accessCode.setModify(new Date());
        Integer save = accessCodeMapper.saveAccessCode(accessCode);
        return save == 1;
    }

    /**
     * 批量添加查询码
     * @param accessCodes 查询码
     * @return 成功插入的数量
     */
    public int add(List<AccessCode> accessCodes) {
        for (AccessCode accessCode : accessCodes) {
            accessCode.setModify(new Date());
        }
        return accessCodeMapper.saveAccessCodes(accessCodes);
    }

    /**
     * 删除一个查询码
     * @param code 查询码
     * @return true 删除成功，false 删除失败
     */
    public boolean delete(String code) {
        Integer delete = accessCodeMapper.deleteAccessCode(code);
        return delete == 1;
    }

    /**
     * 批量删除查询码
     * @param codes 查询码
     * @return 删除的条数
     */
    public int delete(List<String> codes) {
        return accessCodeMapper.deleteAccessCodes(codes);
    }

    /**
     * 更新查询码
     * @param accessCode 查询码
     * @return 更新状态
     */
    public boolean update(AccessCode accessCode) {
        Integer update = accessCodeMapper.update(accessCode);
        return update == 1;
    }

    /**
     * 减少次数
     * @param code 查询码
     * @return true 代表减少次数成功，false 代表减少次数失败
     */
    public boolean reduceTimes(String code) {
        Integer integer = accessCodeMapper.reduceTimesByCode(code);
        return integer == 1;
    }

    /**
     * 添加次数
     * @param code 查询码
     * @param times 待添加的次数
     * @return 添加状态
     */
    public boolean addTimes(String code, int times) {
        Integer addTimesByCode = accessCodeMapper.addTimesByCode(code, times);
        return addTimesByCode == 1;
    }
}
