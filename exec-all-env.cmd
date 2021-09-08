
start "launch consul" exec-consul.cmd

start "import key-value" exec-kv.cmd

start "launch service pod-1" exec-svc1.cmd

start "launch service pod-2" exec-svc2.cmd


pause



