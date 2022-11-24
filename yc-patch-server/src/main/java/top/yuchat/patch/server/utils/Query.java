package top.yuchat.patch.server.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import top.yuchat.patch.server.constant.CommonConstant;
import top.yuchat.patch.server.xss.SQLFilter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query <T> extends LinkedHashMap<String, Object> {

    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if (params.get(CommonConstant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(CommonConstant.PAGE));
        }
        if (params.get(CommonConstant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(CommonConstant.LIMIT));
        }

        //分页对象
        Page<T> page = new Page<T>(curPage, limit);

        //分页参数
        params.put(CommonConstant.PAGE, page);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String) params.get(CommonConstant.ORDER_FIELD));
        String order = (String) params.get(CommonConstant.ORDER);


        //前端字段排序
        if (StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)) {
            if (CommonConstant.ASC.equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            } else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if (StringUtils.isBlank(defaultOrderField)) {
            return page;
        }

        //默认排序
        if (isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        } else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
