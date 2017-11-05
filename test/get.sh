selection="{.items[?(.metadata.name==\"${1}\")].spec.host}"
curl -i -v -u 'testdrive:r3dh4t1!' -H "Accept: application/json" -X GET http://$(oc get routes --output jsonpath=${selection})/ws/tasks/"${2}"
