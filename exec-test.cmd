
# only load-balance
curl http://localhost:9082/load-id


# circuit-break and retry 
curl http://localhost:9081/circuit-id

# only retry 
curl http://localhost:9081/retry-id


# FeignClient (load-balance)
curl http://localhost:9083/find-id

# Feign
curl http://localhost:9083/feign-id

