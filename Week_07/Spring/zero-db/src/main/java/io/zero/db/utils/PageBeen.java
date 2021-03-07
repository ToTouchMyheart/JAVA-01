package io.zero.db.utils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageBeen {
    /** 当前页码 */
    private int pageNo;
    /** 每页显示数量 */
    private int pageSize;
    /** 记录条数 */
    private int recordCount;

    private int pageCount;
    private int start;

    public PageBeen(int pageNo, int pageSize, int recordSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.recordCount = recordSize;

        if (recordSize == 0) {
            pageCount = 1;
        } else if (recordSize % pageSize == 0) {
            pageCount = recordSize / pageSize;
        } else {
            pageCount = recordSize / pageSize + 1;
        }
        if (pageNo > pageCount) {
            this.pageNo = pageCount;
        }
        start = (this.pageNo - 1) * pageSize;
    }
}
