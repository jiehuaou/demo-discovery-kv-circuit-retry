## install consul in Win10 cmd (with Administrator role)

choco install consul

## consul cmd start ##

consul agent -dev -node machine1

## consul put KV with python  ##
```
// pip install python-consul
import consul
c = consul.Consul()
c.kv.put("qqq", "hello world")
c.kv.put("www/abc", "wiki")
```

## consul cmd put KV  ##

consul kv put redis/config/desc "hello world"

consul kv put data1 "hello world"

## consul cmd import KV json ##
``` data.json
[
  {
    "Key": "app1/path/key",
    "Value": "hello"
  },
  {
    "Key": "app1/path/key2",
    "Value": "hello2"
  }
]
```

consul.exe kv import @data.json

## consul config folder and key/value ##

http://127.0.0.1:8500/ui/dc1/kv

    config/data-service-1
        foo : world

    config/data-service-1/aws
        user : xxx
        password : yyyy
        sample : zzz

consul kv put "config/data-service-1/foo" "world"

consul kv put "config/data-service-1/aws/user"      "abc"
consul kv put "config/data-service-1/aws/password"  "123456+"
consul kv put "config/data-service-1/aws/sample"    "dynamo-db"

## @RefreshScope ##

    help refresh the values in client when they are changed in consul.


## consul DiscoveryClient ##

    discovery the available services such as Netflix Eureka or Consul.

## launch service svc in discovery-service/target

java -jar -Dserver.port=8081  service-0.0.1-SNAPSHOT.jar --label=app1

java -jar -Dserver.port=8082  service-0.0.1-SNAPSHOT.jar --label=app2

java -jar -Dserver.port=8083  service-0.0.1-SNAPSHOT.jar --label=bad


## launch client svc in discovery-client/target

java -jar -Dserver.port=8088  client-0.0.1-SNAPSHOT.jar 

## launch circuit-retry-client svc in demo-circuit-retry/target

java -jar -Dserver.port=8089  client-0.0.1-SNAPSHOT.jar 


## test call from client to service

curl http://localhost:8088/another-id

 - /id:hello:app1
 - /id:hello:app2
 - /id:hello:bad
...

## test retry call from client to service

curl http://localhost:8089/retry-id

## test circuit-break-retry call from client to service

curl http://localhost:8089/circuit-id

