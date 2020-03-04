package com.llw.hospital.event;

/**
 * 建模事件 通过BroadcastUtil.boradCast()方法发布出来
 */
public class ModelAddEvent extends PersonEvent {
    public ModelAddEvent(Object source,Long personId) {
        super(source,personId);
    }

    @Override
    public String toString() {
        return "ModelAddEvent{} " + super.toString();
    }
}
