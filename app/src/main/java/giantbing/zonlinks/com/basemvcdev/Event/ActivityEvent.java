package giantbing.zonlinks.com.basemvcdev.Event;

import java.util.Objects;

/**
 * Created by giant on 2017/9/18.
 */

public abstract class ActivityEvent {
    public abstract CodeEnum getCode();

    public enum CodeEnum{
        toPage(0,"asd");
        private int code;
        private Object[] objects;
        private CodeEnum(int code,Object... args) {
            this.code = code;
            this.objects = args;
        }

        public int getCode() {
            return this.code;
        }

        public Object[] getObjects() {
            return objects;
        }
    }
}
