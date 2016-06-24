import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ellioe03 on 23/06/2016.
 */
public class ErrReporter implements Runnable {
    private static final int MAX_ERR_COUNT = 10;
    private static final int MAX_ERR_MSG_LENGTH = 100;
    private static final Logger LOG = LoggerFactory.getLogger(ErrReporter.class);
    private final String ispyer;
    private final String ispyPrefix;
    private volatile Multiset<String> errs;

    public ErrReporter(String ispyer, String ispyPrefix, Multiset<String> errs) {
        this.ispyer = ispyer;
        this.ispyPrefix = ispyPrefix;
        this.errs = ConcurrentHashMultiset.create();
    }

    public void adderror(String partName, String errMsg){
        this.adderror(String.format("%s: %s",partName, errMsg), errMsg);
    }

    @Override
    public void run() {

    }
}
