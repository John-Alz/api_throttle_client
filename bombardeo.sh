#!/bin/bash

URL="http://localhost:8080/api/test/path"
BODY='{"name":"John","lastName":"Doe"}'
TOTAL=100
CONCURRENTES=10

echo "Disparando $TOTAL requests con $CONCURRENTES en paralelo..."

for i in $(seq 1 $TOTAL); do
  curl -s -o /dev/null -w "Request $i -> HTTP %{http_code}\n" \
    -X POST "$URL" \
    -H "Content-Type: application/json" \
    -d "$BODY" &

  if (( i % CONCURRENTES == 0 )); then
    wait
  fi
done

wait
echo "Bombardeo terminado."
