//#当它的值为true 的时候，过滤掉匹配到当前正则表达式的一行
//#当它的值为false的时候，就接受匹配到正则表达式的一行
println "netuser filter"
//println head
//println body
body = "20170621162925,113.225.23.151,test_10056368,1"
//body = "20170621162925,113.225.23.152,test_10056368,3"

def split = body.split(",")

println split.size()

if(split.size()<4){
    //数据不合格过滤
    return false;
}

def type = split[3]

println type.getClass()
println type.substring(0,1) == '1'
//println type.toInteger() == 1

if (type.substring(0,1) == '1' || type.substring(0,1) == '2') {
    return true;  //不过滤
} else {
    return false; //过滤
}
