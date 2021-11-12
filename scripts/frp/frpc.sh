#!/usr/bin/env bash
path=$(cd `dirname $0`; pwd)

$path/frpc -c $path/frpc.ini