selection="{.items[?(.spec.containers[*].name==\"${1}\")].metadata.name}"
oc exec -ti $(oc get pods --output jsonpath=${selection}) -- bash -c 'mysql -h 127.0.0.1 -u ${MYSQL_USER} -D ${MYSQL_DATABASE} -p'
