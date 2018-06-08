package com.fuwenhao.interviewTest.ListDemo;

/**
 * create by fwh on 2018/5/30 下午2:29
 */
public class MylinkedList {
    int size =0;
    Node head =null;//设置头节点

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //添加
    public void add(Object value){
        Node newNode =new Node(value);
        if(head==null) {//第一次添加数据信息
            head =newNode;
        }else {
            Node temp =head;//当前节点
            while (temp.getNext()!=null){
                temp=temp.getNext();//当前节点数据向后移动
            }
            //循环结束，temp成为最后一个节点
            temp.setNext(newNode);
        }
        size++;//最后size增加。
    }
    //设置
    public void set(int index,Object value){
        Node temp =head;
        for (int i = 0; i < index; i++) {
            temp=temp.getNext();
        }
        //temp定位到索引的位置
        temp.setValue(value);
    }
    //获取
    public Object get(int index){
        Node temp =head;
        for (int i = 0; i < index; i++) {
            temp=temp.getNext();
        }
        //temp定位到索引的位置
        return temp.getValue();

    }
    //删除
    public void clear(){
        /**
         * 原理：将头数据切除掉，后面的数据就不会在获取到，自然不会在占用内存。
         */
        head=null;
        size=0;
    }
    //按索引坐标删除
    public void removeIndex(int index){
        if(index==0){//删除头元素
            head=head.getNext();
        }else {
            //找到删除元素的前一个元素
            Node temp =head;
            for (int i = 0; i < index; i++) {
                temp=temp.getNext();
            }
            //循环结束，temp表示删除的前一个元素。
            temp.setNext(temp.getNext().getNext());
        }
        size--;
    }

}
