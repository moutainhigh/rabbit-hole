#!/usr/bin/env bash
path=$(cd `dirname $0`; pwd)

$path/frpc -c $path/frpc.ini

# frpc tcp --server_addr=127.0.0.1 --server_port=7000 --token=xxx --local_ip=127.0.0.1 --local_port=11
# ./frpc tcp --server_addr=127.0.0.1:7000 --local_port=11111 --token=xxx
# ./frpc tcp --server_addr=127.0.0.1:7000 --local_port=11111
