package giantbing.zonlinks.com.basemvcdev.Http.Base;

/**
 * Created by giant on 2017/9/19.
 */

import io.reactivex.subscribers.DisposableSubscriber;

    public abstract class BaseSubscriber<T> extends DisposableSubscriber<T> {
        @Override
        public void onNext(T t) {
            handlerSuccess(t);
        }

        @Override
        public void onError(Throwable t) {
            //// TODO: 2017/9/19 处理失败
        }

        @Override
        public void onComplete() {

        }
        //请求成功返回结果
        public abstract void handlerSuccess(T t);
    }
