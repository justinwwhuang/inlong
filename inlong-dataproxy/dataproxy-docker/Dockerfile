#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
FROM openjdk:8-jdk
RUN apt-get update \
    && apt-get install -y net-tools vim \
    && rm -rf /var/lib/apt/lists/*
# add tarball from target output
ARG DATAPROXY_TARBALL
ADD ${DATAPROXY_TARBALL} /opt/inlong-dataproxy
EXPOSE 46801 46802
ENV MANAGER_OPENAPI_IP=127.0.0.1
ENV MANAGER_OPENAPI_PORT=8083
# specify the name of the network card in the container to obtain the local IP
ENV ETH_NAME=eth0
# pulsar or tubemq or kafka
ENV MQ_TYPE=pulsar
ENV CLUSTER_TAG=default_cluster
ENV CLUSTER_NAME=default_dataproxy
ENV CLUSTER_IN_CHARGES=admin
ENV DATAPROXY_JVM_HEAP_OPTS="-XX:+UseContainerSupport -XX:InitialRAMPercentage=40.0 -XX:MaxRAMPercentage=80.0 -XX:-UseAdaptiveSizePolicy"
WORKDIR /opt/inlong-dataproxy
ADD dataproxy-docker.sh bin/
RUN chmod +x bin/dataproxy-docker.sh
CMD ["bin/dataproxy-docker.sh"]
