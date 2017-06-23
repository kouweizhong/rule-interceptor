import com.google.gson.Gson

//数据加工为 lua 脚本，插入到 redis
println "netuser redis script 准备"
//println head
//println body

//body = "20170621162925,113.225.23.151,test_10056368,1"
body = "20170621162925,113.225.23.152,test_10056368,2"
// eval "return redis.call('ZADD','KEYS[1]',ARGV[1],ARGV[2])" 1 keyset   123  u123



def split = body.split(",")



def type = split[3]
if (type.substring(0,1) == '1') {
    split[2]=split[2]+"@Start";
} else {
    split[2]=split[2]+"@End";
}

Gson gson = new Gson();

Map full= new HashMap();
full.put("script","return redis.call('ZADD',KEYS[2],KEYS[1],KEYS[3])");
full.put("args",new ArrayList());
full.put("keys",split);

String json = gson.toJson(full);
println json

Map m=gson.fromJson(json, HashMap.class);
println m


//StringBuffer sb = new StringBuffer("return redis.call('ZADD','");
//
//sb.append(split[1]).append("',").append(split[0]).append(",'").append(split[2]);
//if (type.substring(0,1) == '1') {
//    sb.append("@Start'");
//} else {
//    sb.append("@End'");
//}
//sb.append(")");
//println sb

def resultMap = [:]

resultMap["head"] = head
resultMap["body"] = json

return resultMap