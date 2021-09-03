
start "launch consul" exec-consul.cmd

start "import key-value" exec-kv.cmd

start "launch service pod-1" exec-svc1.cmd

start "launch service pod-2" exec-svc2.cmd

start "launch service client" exec-client.cmd

pause


curl http://localhost:8088/another-id

curl http://localhost:8088/another-id

curl http://localhost:8088/another-id

curl http://localhost:8088/another-id

pause
