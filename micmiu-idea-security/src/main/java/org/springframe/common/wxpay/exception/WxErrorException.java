package org.springframe.common.wxpay.exception;

/**
 * @author: HeYixuan
 * @create: 2017-06-02 10:52
 */
public class WxErrorException extends Exception {

    private static final long serialVersionUID = -6357149550353160810L;

    private WxError error;

    public WxErrorException(WxError error) {
        super(error.toString());
        this.error = error;
    }

    public WxErrorException(WxError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public WxError getError() {
        return this.error;
    }
}
