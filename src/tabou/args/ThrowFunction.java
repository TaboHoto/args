package tabou.args;
@FunctionalInterface public interface ThrowFunction<T,R>{
    R apply(T t)throws Exception;
}
