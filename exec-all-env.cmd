
start "launch consul" exec-consul.cmd

start "import key-value" exec-kv.cmd

start "launch service svc-1" exec-svc1.cmd

start "launch service svc-2" exec-svc2.cmd

start "launch service svc-3" exec-svc3-bad.cmd

pause



