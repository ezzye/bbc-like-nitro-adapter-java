import com.sun.org.apache.xalan.internal.xsltc.compiler.XPathParser;

/**
 * Created by ellioe03 on 23/06/2016.
 */
public class NettyBaseRequestExchange {
    private ErrReporter errReporter;

    public NettyBaseRequestExchange() {
    }

    public void handleError(String partName, int httpCode, String errMsg) {
        this.errReporter.addError(partName);
        if(this.firstToSendError()) {
            this.returnError(httpCode, partName + ": " + errMsg);
        }
    }
}
