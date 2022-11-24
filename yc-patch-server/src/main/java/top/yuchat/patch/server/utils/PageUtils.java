package top.yuchat.patch.server.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class PageUtils {

    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 每页记录数
     */
    private long pageSize;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 当前页数
     */
    private long currPage;
    /**
     * 列表数据
     */
    private List<?> list;

    public PageUtils() {
    }

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtils(List<?> list, long totalCount, long pageSize, long currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     *
     * @param list
     * @param totalCount
     * @param pageSize
     * @param currPage
     * @param totalPage
     */
    public PageUtils(List<?> list, long totalCount, long pageSize, long currPage, long totalPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = totalPage;
    }

    /**
     * 分页
     */
    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = page.getPages();
    }
}

