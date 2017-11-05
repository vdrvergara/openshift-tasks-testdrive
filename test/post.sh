selection="{.items[?(.metadata.name==\"${1}\")].spec.host}"
curl -i -v -u 'redhat:redhat1!' -H "Content-type: application/json" -H "Content-Length: 0" -X POST http://$(oc get routes --output jsonpath=${selection})/ws/tasks/"${2}"
