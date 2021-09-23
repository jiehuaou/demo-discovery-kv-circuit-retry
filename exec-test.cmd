
# only Spring Cloud LoadBalancer
curl http://localhost:8088/load-id

x=1; while [ $x -le 600 ]; do curl http://localhost:8088/load-id ; echo $(( x++ )) ; sleep 1 ; done


# circuit-break and retry 
curl http://localhost:9081/circuit-id

x=1; while [ $x -le 60 ]; do curl http://localhost:9081/retry-circuit-id ; echo $(( x++ )) ; sleep 1 ; done
x=1; while [ $x -le 60 ]; do curl http://localhost:9081/circuit-id ; echo $(( x++ )) ; sleep 1 ; done

# only retry 
curl http://localhost:9081/retry-id


# FeignClient (load-balance) with circuit-break
curl http://localhost:9083/feign-id

x=1; while [ $x -le 60 ]; do curl http://localhost:9083/feign-id ; echo $(( x++ )) ; sleep 1 ; done


