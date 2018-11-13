package com.ny.model;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author liuzh
 */
@ApiModel(value = "分页序列化视图")
public class PageSerializableView<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 总记录数
    @ApiModelProperty(value = "总记录数")
    protected long total;
    // 结果集
    @ApiModelProperty(value = "结果集")
    protected List<T> list;

    public PageSerializableView() {
    }

    public PageSerializableView(List<T> list) {
        this.list = list;
        if (list instanceof Page) {
            this.total = ((Page<T>) list).getTotal();
        } else {
            this.total = list.size();
        }
    }

    public static <T> PageSerializableView<T> of(List<T> list) {
        return new PageSerializableView<T>(list);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageSerializableView{" + "total=" + total + ", list=" + list + '}';
    }
}
