/*
 */
package tabou.args;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicBoolean;
public class ArgsOpt{
    private Map<Character,ArgsParam> paramMap = new HashMap<>();
    public static void main(String[] args){
        var argsOpt = new ArgsOpt();
        argsOpt.b('c').set(b -> System.out.println("引数:" + b.get()));
        System.out.println(argsOpt.parse(args));
    }
    public List<String> parse(String[] args){
        int argi = 0;
        for (; argi < args.length; argi++) {
            char[] chars = args[argi].toCharArray();
            if (chars[0] != '-') {
                break;
            }
            if (chars.length != 2) {
                System.err.println("不正な引数:" + args[argi]);
//                usage();
            }
            char c = chars[1];
            ArgsParam param = this.paramMap.get(c);
            if(param == null){
             System.err.println("不明な引数:" + c);
//             usage();
             System.exit(1);
            }
            if(param.isParam){
             param.arg = args[++argi];
            }
            param.setAccept();
        }
        return Stream.of(args).skip(argi)
            .collect(Collectors.toList());
    }
    public ArgsParam<AtomicBoolean> b(char shortParam){
        ArgsParam<AtomicBoolean> param =
            new ArgsParam<AtomicBoolean>(AtomicBoolean::new);
        param.isParam = false;
        this.paramMap.put(shortParam,param);
        return param;
    }
 public ArgsParam<StringBuilder> s(char shortParam){
  var param = new ArgsParam<StringBuilder>(StringBuilder::new);
   param.set(b -> b.append(param.arg));
   this.paramMap.put(shortParam,param);
  return param;
 }
}
