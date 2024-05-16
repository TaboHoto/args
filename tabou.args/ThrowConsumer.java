package tabou.args;
@FunctionalInterface public interface ThrowConsumer<T>{
    void accept(T t)throws Exception;
}
