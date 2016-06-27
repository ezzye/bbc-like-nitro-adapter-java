<<<<<<< HEAD
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


    public void addError(String partName, String errMsg){
        this.addError(String.format("%s: %s", new Object[]{partName, StringHelper.abbreviate(errMsg,100)}));
    }

    private void addError(String m){
        if(this.errs.elementSet().size() < 10 || this.errs.contains(m)){
            this.errs.add(m);
        }
    }

    private Multiset<String> snapshot(){
        Multiset snapshot = this.errs;
        this.errs = ConcurrentHashMultiset.create();
        return snapshot;
    }

    @Override
    public void run() {
        ImmutableMultiset snapshot = Multisets.copyHighestCountFirst(this.snapshot());
        if(snapshot.size() >= 1){
            StringBuilder out = new StringBuilder();
            Builder toIspy = ImmutableMap.builder();
            int i = 0;
            Iterator var5 = snapshot.entrySet().iterator();

            while(var5.hasNext()){
                Entry e = (Entry)var5.next();
                if(out.length() > 0){
                    out.append(" ");
                }

                out.append(String.format("err%s{%s}*%s", new Object[]{Integer.valueOf(i), e.getElement(), Integer.valueOf(e.getCount())}));
                ++i;
                if(i >= 10) {
                    break;
                }
            }

            LOG.info(out.toString());
        }

    }
}
