package cn.fves24.id.db.mapper;

import cn.fves24.id.entity.model.AccessCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccessCodeMapper {
    /**
     * 添加查询码
     * @param accessCode 查询码
     * @return 改变的行数
     */
    Integer saveAccessCode(AccessCode accessCode);

    /**
     * 批量增加查询码
     * @param accessCodes 查询码
     * @return 改变的行数
     */
    Integer saveAccessCodes(List<AccessCode> accessCodes);

    /**
     * 删除查询码
     * @param code 查询码
     * @return 改变的行数
     */
    Integer deleteAccessCode(String code);

    /**
     * 批量删除查询码
     * @param codes 查询码
     * @return 改变的行数
     */
    Integer deleteAccessCodes(List<String> codes);
    /**
     * 更新查询码
     * @param accessCode 查询码
     * @return 改变的行数
     */
    Integer update(AccessCode accessCode);
    /**
     * 增加次数
     * @param code 查询码
     * @param times 增加的次数（可以为负数，代表减少相应次数）
     * @return 增加状态
     */
    Integer addTimesByCode(@Param("code") String code,@Param("times") int times);

    /**
     * 减少次数
     * @param code 查询码
     * @return 改变的行数
     */
    Integer reduceTimesByCode(String code);

    /**
     * 剩余次数
     * @param code 查询码
     * @return 改变的行数
     */
    Integer getTimesByCode(String code);

    /**
     * 获取所有查询码
     * @return 所有查询码
     */
    List<AccessCode> getAllAccessCode();

    /**
     * 检测查询码是否存在
     * @param code 查询码
     * @return 查询码实体
     */
    AccessCode exist(String code);
}
