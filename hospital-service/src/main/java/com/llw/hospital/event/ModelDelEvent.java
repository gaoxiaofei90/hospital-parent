package com.llw.hospital.event;

/**
 * 模板删除事件 通过BroadcastUtil.boradCast()方法发布出来
 */
public class ModelDelEvent extends PersonEvent {
    public ModelDelEvent(Object source,Long personId) {
        super(source,personId);
    }

    @Override
    public String toString() {
        return "ModelDelEvent{} " + super.toString();
    }
}
