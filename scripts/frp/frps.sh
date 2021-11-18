#!/usr/bin/env bash
path=$(cd `dirname $0`; pwd)

$path/frps -c $path/frps.ini
