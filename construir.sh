#!/bin/bash
set -e

readonly work_dir=$(dirname $(perl -e "use Cwd 'abs_path'; print abs_path('$0')"))
readonly jar_file_name='leonlibj-0.0.1-SNAPSHOT.jar'

if time mvn clean install -DskipTests -T4 -DforkCount=2 -Dmaven.site.skip=true \
    -Dmaven.javadoc.skip=true -Dmaven.test.skip=true -Dmaven.compiler.useIncrementalCompilation=true; then
    echo 'Build done, running the application'
    java -jar "${work_dir}/target/${jar_file_name}"
fi

exit 0
