package io.zero.db.utils;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pagination<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /** Page总数 */
    private final int pageCount;
    /** 当前Page No. */
    private final int pageNo;
    /** 每页显示数量 */
    private final int pageSize;
    /** 符合条件的记录总数 */
    private final long recordCount;
    /** 查询数据列表 */
    private final List<T> data;
}
