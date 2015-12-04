FILE=$1
echo $1

curl -H "Content-Type: application/json" -X POST -d @$1 http://127.0.0.1:8080/ppd/collectd/data
