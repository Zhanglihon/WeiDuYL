package zhang.bw.com.common.core;


import zhang.bw.com.common.core.exception.ApiException;

/**
 * @author dingtao
 * @date 2018/12/30 10:30
 * qq:1940870847
 */
public interface DataCall<T> {

    void success(T data, Object... args);
    void fail(ApiException data, Object... args);

}
