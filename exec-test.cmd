
# only load-balance
curl http://localhost:9082/load-id


# circuit-break and retry 
curl http://localhost:9081/circuit-id

x=1; while [ $x -le 60 ]; do curl http://localhost:9081/retry-circuit-id ; echo $(( x++ )) ; sleep 1 ; done
x=1; while [ $x -le 60 ]; do curl http://localhost:9081/circuit-id ; echo $(( x++ )) ; sleep 1 ; done

# only retry 
curl http://localhost:9081/retry-id


# FeignClient (load-balance) with circuit-break
curl http://localhost:9083/find-id

x=1; while [ $x -le 60 ]; do curl http://localhost:9083/find-id ; echo $(( x++ )) ; sleep 1 ; done

# Feign
curl http://localhost:9083/feign-id

