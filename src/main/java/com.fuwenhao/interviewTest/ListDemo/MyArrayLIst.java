package com.fuwenhao.interviewTest.ListDemo;

/**
 * create by fwh on 2018/5/30 上午11:49
 */
public class MyArrayLIst {
    Object[] objects = new Object[4];//定义只能放四个长度。
    int size = 0;//集合的大小

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //添加
    public void add(Object value) {
        //判断自增的集合大小是否已经超过来长度空间，如果大于了，则需要换个大的空间。
        if (size >= objects.length) {
            Object[] temp = new Object[objects.length * 2];//此处设置的新大空间为原空间的两倍。//也可以参见源码中的标准算法。
            //搬家放入大空间
            for (int i = 0; i < objects.length; i++) {
                temp[i] = objects[i];//小空间值放入大空间
            }
            objects = temp;//将大空间赋值给object，这样的话我们可以一直对objec操作，不影响下面一行代码。
        }
        objects[size] = value;//将添加的值放入数组中
        size++;
    }

    //设置值
    public void set(int index, Object value) throws Exception {
        //判断满足范围内才能放置数据
        if (index < 0 || index > size) {
            throw new Exception("超出范围了。。。");
        }
        objects[index] = value;
    }

    //获取值
    public Object get(int index) throws Exception {
        //逻辑上：判断满足范围内才能放置数据
        if (index < 0 || index > size) {
            throw new Exception("超出范围了。。。");
        }
        return objects[index];
    }

    //清除数据
    public void clear() {
        size = 0;
        //释放内存； 重新new一个数组，之前占用的内存会被GC回收掉。
        objects = new Object[4];
    }

    /**
     * /删除指定索引的数值
     * 原理：
     *  并没有真正的清除数据，而是索引后面的数据往前面移动，整体的size大小减小。
     */
    public void remoreIndex(int index) throws Exception {
        //只允许访问的数据范围
        if (index < 0 || index > size) {
            throw new Exception("超出范围了。。。");
        }
        //当前索引位置后面的数据开始往前面移动
        for (int i = index + 1; i < size; i++) {
            objects[i - 1] = objects[i];
        }
        size--;//整体结合大小减小。

    }
}
