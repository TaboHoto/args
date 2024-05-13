/*
 */
package tabou.args;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.function.Consumer;
public class ArgsParam<M>{
    private char shortParam;
//    private Supplier<M> buildFactory;
    private M valueObject;
    private ThrowConsumer<M> setFactory;
    boolean isParam = true;
    String arg;
    public static void main(String[] args) throws Exception {
    }
    public ArgsParam(M value){
        this.valueObject = value;
    }
    public ArgsParam(Supplier<M> buildFactory){
//        this.buildFactory = buildFactory;
        this.valueObject = buildFactory.get();
    }
    void setAccept(){
        try{
            this.setFactory.accept(valueObject);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public ArgsParam<M> set(ThrowConsumer<M> set){
        this.setFactory = set;
        return this;
    }
    public M get(){
//        return this.buildFactory.get();
        return this.valueObject;
    }
}
